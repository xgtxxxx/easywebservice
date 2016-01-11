package xgt.easy.webservice.client;

import org.springframework.beans.factory.InitializingBean;
import xgt.easy.webservice.Adapter;
import xgt.easy.webservice.Client;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.exception.EasyWebserviceException;
import xgt.easy.webservice.handler.RequestHandlerChain;
import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;

import java.io.IOException;
import java.util.List;

public abstract class SimpleClient implements Client ,InitializingBean {
    private RequestHandlerChain handlerChain;

    private List<ParameterPair> headers;

    public void afterPropertiesSet() throws Exception {
        if(handlerChain==null){
            handlerChain = new RequestHandlerChain();
            handlerChain.buildChain();
        }
    }

    public <T> T doRequest(Request request, Adapter<T> adapter) throws EasyWebserviceException {
        try{
            final RequestInfo info = this.handlerChain.handle(request);
            if(headers!=null){
                info.addHeaders(headers);
            }
            return adapter.convertTo(doRequest(info));
        }catch (Exception e){
            throw new EasyWebserviceException(e);
        }
    }

    private ResponseInfo doRequest(final RequestInfo info) throws IOException {
        ResponseInfo result = null;
        switch (info.getHttpMethod()){
            case GET:
                result = doGet(info);
                break;
            case POST:
                result = doPost(info);
                break;
            case PUT:
                result = doPut(info);
                break;
            case DELETE:
                result = doDelete(info);
                break;
        }
        return result;
    }

    public abstract ResponseInfo doGet(final RequestInfo info) throws IOException;

    public abstract ResponseInfo doPost(final RequestInfo info);

    public abstract ResponseInfo doPut(final RequestInfo info);

    public abstract ResponseInfo doDelete(final RequestInfo info);

    public List<ParameterPair> getHeaders() {
        return headers;
    }

    public void setHeaders(List<ParameterPair> headers) {
        this.headers = headers;
    }

}
