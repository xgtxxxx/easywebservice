package com.easyframework.webservice.restfulclient.handler.annotationhandler;

import com.easyframework.webservice.restfulclient.Handler;

import java.util.HashMap;
import java.util.Map;

public class RequestHandlerChainFactory extends HandlerFactory{

    public static final String SKIP_HANDLER = "skip_handler";
    public static final String FILTER_HANDLER = "filter_handler";
    public static final String ENCODE_HANDLER = "encode_handler";
    public static final String RENAME_HANDLER = "rename_handler";

    private Map<String,Handler> handlers = new HashMap<String,Handler>();

    private Handler first;

    private Object lock = new Object();

    public Handler build() {
        synchronized (lock){
            if(first==null){
                buildChain();
            }
        }
        return first;
    }

    private void buildChain(){
        first = new RequestHandler();
        Handler next = buildNext(first, handlers.get(SKIP_HANDLER)==null?new SkipHandler():handlers.get(SKIP_HANDLER));
        next = buildNext(next, handlers.get(FILTER_HANDLER));
        next = buildNext(next,handlers.get(ENCODE_HANDLER)==null?new EncodeHandler():handlers.get(ENCODE_HANDLER));
        next = buildNext(next,handlers.get(FILTER_HANDLER));
        buildNext(next,handlers.get(RENAME_HANDLER)==null?new RenameHandler():handlers.get(RENAME_HANDLER));
    }

    private Handler buildNext(Handler parent, Handler next){
        if(next!=null){
            parent.setNext(next);
            parent = next;
        }
        return parent;
    }

    public void setHandlers(Map<String, Handler> handlers) {
        this.handlers = handlers;
    }

    public void addHandler(final String key, final Handler handler){
        this.handlers.put(key,handler);
    }
}
