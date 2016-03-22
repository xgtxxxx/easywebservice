package xgt.easy.webservice.httpclient.EntityAdapter;

import org.apache.http.HttpEntity;
import xgt.easy.webservice.httpclient.handler.FormDataEntityHandler;
import xgt.easy.webservice.httpclient.handler.RequestInfoHandler;
import xgt.easy.webservice.model.PostContentType;
import xgt.easy.webservice.model.RequestInfo;

import java.util.HashMap;
import java.util.Map;

public class HttpSimpleEntityAdapter extends EntityAdapter {

    private Map<String,RequestInfoHandler> handlers = new HashMap<String, RequestInfoHandler>();

    public HttpEntity convertTo(RequestInfo from) {
        return getHandler(from.getApplicationType()).convertTo(from.getFormData());
    }

    private RequestInfoHandler getHandler(final String key){
        RequestInfoHandler handler = handlers.get(key);
        if(handler==null){
            handler = new FormDataEntityHandler();
            handlers.put(PostContentType.FORM_DATA,handler);
        }
        return handler;
    }

    public void addHandler(final String key, final RequestInfoHandler handler){
        handlers.put(key, handler);
    }

    public Map<String, RequestInfoHandler> getHandlers() {
        return handlers;
    }

    public void setHandlers(Map<String, RequestInfoHandler> handlers) {
        this.handlers = handlers;
    }
}