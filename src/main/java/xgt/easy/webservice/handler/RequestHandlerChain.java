package xgt.easy.webservice.handler;

import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import xgt.easy.webservice.model.FormPair;
import xgt.easy.webservice.model.RequestInfo;

import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.*;

public class RequestHandlerChain extends Handler implements InitializingBean {
    private static final Logger LOG = LoggerFactory.getLogger(RequestHandlerChain.class);
    private final String SKIP_HANDLER = "skip_handler";
    private final String AFTER_ENCODE_FILTER_HANDLER = "after_encode_filter_handler";
    private final String BEFORE_ENCODE_FILTER_HANDLER = "before_encode_filter_handler";
    private final String ENCODE_HANDLER = "encode_handler";
    private final String RENAME_HANDLER = "rename_handler";

    private Map<String,Handler> handlers = new HashMap<String,Handler>();

    public void afterPropertiesSet() throws Exception {
        buildChain();
    }

    public void buildChain(){
        Handler next = handlers.get(SKIP_HANDLER)==null?new SkipHandler():handlers.get(SKIP_HANDLER);
        Handler parent = buildNext(this, next);
        parent = buildNext(parent, handlers.get(BEFORE_ENCODE_FILTER_HANDLER));
        next = handlers.get(ENCODE_HANDLER)==null?new EncodeHandler():handlers.get(ENCODE_HANDLER);
        parent = buildNext(parent,next);
        parent = buildNext(parent,handlers.get(AFTER_ENCODE_FILTER_HANDLER));
        parent = buildNext(parent,handlers.get(RENAME_HANDLER));
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
        return requestInfo;
    }
    private String buildPath(final List<FieldInfo> infos){
        Collections.sort(infos,new Sortable());
        final StringBuffer sb = new StringBuffer();
        for(final FieldInfo info:infos){
            if(info.isSkip()){
                continue;
            }
            sb.append('/').append(info.getValue());
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

    private List<FormPair> buildFormData(final List<FieldInfo> infos){
        final List<FormPair> form = new ArrayList<FormPair>();
        for (final FieldInfo info:infos){
            if(info.isSkip()){
                continue;
            }
            form.add(new FormPair(info.getKey(),info.getValue()));
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
            info.setFieldType(getFieldType((Request)obj,field));
            infos.add(info);
        }
        return infos;
    }

    private FieldType getFieldType(final Request request,final Field field){
        if(field.getAnnotation(Path.class)!=null){
            return FieldType.PATH;
        }else if(field.getAnnotation(UrlParameter.class)!=null||request.getHttpMethod()== HttpMethod.GET){
            return FieldType.PARAMETER;
        }else{
            return FieldType.FORM_DATA;
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
