package xgt.easy.webservice.handler;

import org.springframework.beans.factory.InitializingBean;
import xgt.easy.webservice.Handler;
import xgt.easy.webservice.HttpMethod;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.annotation.Order;
import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.SupperAvailable;
import xgt.easy.webservice.annotation.UrlParameter;
import xgt.easy.webservice.model.FieldInfo;
import xgt.easy.webservice.model.FieldType;
import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.utils.StringUtils;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class RequestHandlerChain extends Handler implements InitializingBean {
    public static final String SKIP_HANDLER = "skip_handler";
    public static final String FILTER_HANDLER = "filter_handler";
    public static final String ENCODE_HANDLER = "encode_handler";
    public static final String RENAME_HANDLER = "rename_handler";

    private Map<String,Handler> handlers = new HashMap<String,Handler>();

    public void afterPropertiesSet() throws Exception {
        buildChain();
    }

    public void buildChain(){
        Handler next = buildNext(this, handlers.get(SKIP_HANDLER)==null?new SkipHandler():handlers.get(SKIP_HANDLER));
        next = buildNext(next, handlers.get(FILTER_HANDLER));
        next = buildNext(next,handlers.get(ENCODE_HANDLER)==null?new EncodeHandler():handlers.get(ENCODE_HANDLER));
        next = buildNext(next,handlers.get(FILTER_HANDLER));
        buildNext(next,handlers.get(RENAME_HANDLER)==null?new RenameHandler():handlers.get(RENAME_HANDLER));
    }

    public void addHandler(final String key, final Handler handler){
        this.handlers.put(key,handler);
    }

    private Handler buildNext(Handler parent,Handler next){
        if(next!=null){
            parent.setNext(next);
            parent = next;
        }
        return parent;
    }

    public RequestInfo handle(final Object request) throws IllegalAccessException, UnsupportedEncodingException {
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
        requestInfo.setRequestUrl(buildPath(paths)+buildUrlParameter(parameters));
        requestInfo.setHeaders(((Request) request).getHeaders());
        return requestInfo;
    }
    private String buildPath(final List<FieldInfo> infos){
        Collections.sort(infos,new Sortable());
        final StringBuffer sb = new StringBuffer();
        for(final FieldInfo info:infos){
            if(info.isSkip()){
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
        for (final FieldInfo info:infos){
            if(info.isSkip()){
                continue;
            }
            form.add(new ParameterPair(info.getKey(),info.getValue()));
        }
        return form;
    }

    private List<FieldInfo> getFieldInfos(final Object obj) throws IllegalAccessException {
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
            field.setAccessible(true);
            final FieldInfo info = new FieldInfo();
            info.setField(field);
            info.setKey(field.getName());
            info.setValue(field.get(obj));
            final Order order = field.getAnnotation(Order.class);
            if(order!=null){
                info.setOrder(order.value());
            }
            setFieldType(info,(Request)obj);
            infos.add(info);
        }
        return infos;
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

    public Map<String, Handler> getHandlers() {
        return handlers;
    }

    public void setHandlers(Map<String, Handler> handlers) {
        this.handlers = handlers;
    }

    class Sortable implements Comparator<FieldInfo>{
        public int compare(FieldInfo o1, FieldInfo o2) {
            return o1.getOrder()-o2.getOrder();
        }
    }
}
