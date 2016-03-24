package com.easyframework.webservice.restfulclient.handler;

import com.easyframework.webservice.restfulclient.annotation.*;
import com.easyframework.webservice.restfulclient.model.*;
import com.easyframework.webservice.restfulclient.Handler;
import com.easyframework.webservice.restfulclient.HttpMethod;
import com.easyframework.webservice.restfulclient.PostRequest;
import com.easyframework.webservice.restfulclient.Request;
import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;
import com.easyframework.webservice.restfulclient.utils.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class RequestHandler extends Handler {
    public RequestInfo handle(final Object request) throws EasyWebserviceException {
        final List<FieldInfo> infos = getFieldInfos(request);
        final List<FieldInfo> paths = new ArrayList<FieldInfo>();
        final List<FieldInfo> parameters = new ArrayList<FieldInfo>();
        final List<FieldInfo> formDatas = new ArrayList<FieldInfo>();
        for(FieldInfo info:infos){
            switch(info.getFieldType()){
                case PATH:paths.add(info);break;
                case PARAMETER:parameters.add(info);break;
                case FORM_DATA:formDatas.add(info);break;
            }
            fireNext(info);
        }
        final RequestInfo requestInfo = new RequestInfo();
        requestInfo.setFormData(buildFormData(formDatas));
        requestInfo.setHttpMethod(((Request)request).getHttpMethod());
        requestInfo.setRequestUrl(buildUrl((Request) request, paths, parameters));
        requestInfo.setHeaders(((Request) request).getHeaders());
        if(request instanceof PostRequest){
            final String type = ((PostRequest) request).getApplicationType();
            requestInfo.setApplicationType(StringUtils.isEmpty(type)? PostContentType.FORM_DATA:type);
        }
        return requestInfo;
    }

    private String buildUrl(final Request request,final List<FieldInfo> paths,final List<FieldInfo> parameters){
        return request.getHost()+request.getCtx()+buildPath(paths)+buildUrlParameter(parameters);
    }

    private String buildPath(final List<FieldInfo> infos){
        Collections.sort(infos,new Sortable());
        final StringBuffer sb = new StringBuffer();
        for(final FieldInfo info:infos){
            if(info.isSkip()){
                if(getLogger().isInfoEnabled()){
                    getLogger().info("Field : {} is skiped",info.getField().getName());
                }
                continue;
            }
            sb.append(info.getValue());
        }
        return sb.toString();
    }

    private String buildUrlParameter(final List<FieldInfo> infos){
        Collections.sort(infos,new Sortable());
        final StringBuffer sb = new StringBuffer();
        int index = 0;
        for(final FieldInfo info:infos){
            if(info.isSkip()){
                if(getLogger().isInfoEnabled()){
                    getLogger().info("Field : {} is skiped",info.getField().getName());
                }
                continue;
            }
            if(index==0){
                sb.append('?');
            }else{
                sb.append('&');
            }
            sb.append(info.getKey()).append('=').append(info.getValue());
            index++;
        }
        return sb.toString();
    }

    private List<ParameterPair> buildFormData(final List<FieldInfo> infos){
        final List<ParameterPair> form = new ArrayList<ParameterPair>();
        for (final FieldInfo info : infos) {
            if (info.isSkip()){
                if(getLogger().isInfoEnabled()){
                    getLogger().info("Field : {} is skiped",info.getField().getName());
                }
                continue;
            }
            form.add(new ParameterPair(info.getKey(),info.getValue()));
        }
        return form;
    }

    private List<FieldInfo> getFieldInfos(final Object obj) throws EasyWebserviceException {
        final List<Field> fields = new ArrayList<Field>();
        Class clazz = obj.getClass();
        final Annotation available = clazz.getAnnotation(SupperAvailable.class);
        while (clazz!=null){
            fields.addAll(0, Arrays.asList(clazz.getDeclaredFields()));
            if(available!=null){
                clazz = clazz.getSuperclass();
            }else{
                clazz = null;
            }
        }

        final List<FieldInfo> infos = new ArrayList<FieldInfo>();
        for (final Field field:fields){
            if(!isIgnore(field)){
                field.setAccessible(true);
                final FieldInfo info = new FieldInfo();
                info.setField(field);
                info.setKey(field.getName());
                try {
                    info.setValue(field.get(obj));
                } catch (IllegalAccessException e) {
                    throw new EasyWebserviceException(e);
                }
                final Order order = field.getAnnotation(Order.class);
                if(order!=null){
                    info.setOrder(order.value());
                }
                setFieldType(info,(Request)obj);
                infos.add(info);
            }else{
                if(getLogger().isInfoEnabled()){
                    getLogger().info("Field : {} is ignored",field.getName());
                }
            }
        }
        return infos;
    }

    private boolean isIgnore(final Field field) {
        final Ignore ignore = field.getAnnotation(Ignore.class);
        return ignore!=null;
    }

    private void setFieldType(final FieldInfo info,final Request request){
        Field field = info.getField();
        Path path = field.getAnnotation(Path.class);
        if(field.getAnnotation(Path.class)!=null){
            info.setFieldType(FieldType.PATH);
            String append = path.append();
            if(StringUtils.isNotEmpty(append)&&StringUtils.isNotEmpty(info.getValue())){
                info.setValue(append+StringUtils.toString(info.getValue()));
            }
        }else if(field.getAnnotation(UrlParameter.class)!=null||request.getHttpMethod()== HttpMethod.GET){
            info.setFieldType(FieldType.PARAMETER);
        }else{
            info.setFieldType(FieldType.FORM_DATA);
        }
    }

    class Sortable implements Comparator<FieldInfo>{
        public int compare(FieldInfo o1, FieldInfo o2) {
            return o1.getOrder()-o2.getOrder();
        }
    }
}
