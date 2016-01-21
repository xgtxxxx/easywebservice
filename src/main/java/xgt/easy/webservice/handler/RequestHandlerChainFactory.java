package xgt.easy.webservice.handler;

import xgt.easy.webservice.Handler;

import java.util.HashMap;
import java.util.Map;

public class RequestHandlerChainFactory extends HandlerFactory{

    public static final String SKIP_HANDLER = "skip_handler";
    public static final String FILTER_HANDLER = "filter_handler";
    public static final String ENCODE_HANDLER = "encode_handler";
    public static final String RENAME_HANDLER = "rename_handler";

    private Map<String,xgt.easy.webservice.Handler> handlers = new HashMap<String,xgt.easy.webservice.Handler>();

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
        xgt.easy.webservice.Handler next = buildNext(first, handlers.get(SKIP_HANDLER)==null?new SkipHandler():handlers.get(SKIP_HANDLER));
        next = buildNext(next, handlers.get(FILTER_HANDLER));
        next = buildNext(next,handlers.get(ENCODE_HANDLER)==null?new EncodeHandler():handlers.get(ENCODE_HANDLER));
        next = buildNext(next,handlers.get(FILTER_HANDLER));
        buildNext(next,handlers.get(RENAME_HANDLER)==null?new RenameHandler():handlers.get(RENAME_HANDLER));
    }

    private xgt.easy.webservice.Handler buildNext(xgt.easy.webservice.Handler parent, xgt.easy.webservice.Handler next){
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
