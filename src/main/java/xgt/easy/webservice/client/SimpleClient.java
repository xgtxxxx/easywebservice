package xgt.easy.webservice.client;

import xgt.easy.webservice.Adapter;
import xgt.easy.webservice.Client;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.handler.RequestHandlerChain;

public class SimpleClient implements Client {

    private RequestHandlerChain handlerChain;

    public <T> T doRequest(Request request, Adapter<Object, T> adapter) throws IllegalAccessException {
        final RequestInfo info = this.handlerChain.handle(request);
        //call request client
        return adapter.convertTo(null);
    }
}
