package com.easyframework.webservice.restfulclient.adapters.requestadapter;

import com.easyframework.webservice.restfulclient.Adapter;
import com.easyframework.webservice.restfulclient.handler.PostBodyHandler;
import com.easyframework.webservice.restfulclient.httpclient.handler.FormDataEntityHandler;
import com.easyframework.webservice.restfulclient.httpclient.handler.JsonEntityHandler;
import com.easyframework.webservice.restfulclient.model.PostContentType;
import com.easyframework.webservice.restfulclient.model.RequestInfo;

import java.util.HashMap;
import java.util.Map;

public abstract class PostBodyAdapter<T> implements Adapter<RequestInfo,T> {

    private Map<String,PostBodyHandler<T>> handlers = new HashMap<String, PostBodyHandler<T>>();

    public T convertTo(RequestInfo from) {
        return getHandler(from.getApplicationType()).convertTo(from.getFormData());
    }

    private PostBodyHandler<T> getHandler(final String key){
        PostBodyHandler<T> handler = handlers.get(key);
        if(handler==null){
            handler = getDefaultHandler(key);
            handlers.put(key,handler);
        }
        return handler;
    }

    public abstract PostBodyHandler<T> getDefaultHandler(final String key);

    public void addHandler(final String key, final PostBodyHandler<T> handler){
        handlers.put(key, handler);
    }

    public Map<String, PostBodyHandler<T>> getHandlers() {
        return handlers;
    }

    public void setHandlers(Map<String, PostBodyHandler<T>> handlers) {
        this.handlers = handlers;
    }
}
