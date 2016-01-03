package xgt.easy.webservice.handler;

import xgt.easy.webservice.Handler;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.RequestInfo;

import java.util.List;

public class RequestHandlerChain implements Handler {
    private List<Handler> handlers;

    private Handler next;

    private void buildChain(){

    }

    public <T> T handle(Object request) {
        return null;
    }

    public List<Handler> getHandlers() {
        return handlers;
    }

    public void setHandlers(List<Handler> handlers) {
        this.handlers = handlers;
    }

    public Handler getNext() {
        return next;
    }

    public void setNext(Handler next) {
        this.next = next;
    }
}
