package xgt.easy.webservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import xgt.easy.webservice.Adapter;
import xgt.easy.webservice.Client;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.exception.EasyWebserviceException;
import xgt.easy.webservice.handler.RequestHandlerChain;
import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;
import xgt.easy.webservice.utils.StringUtils;

import java.io.IOException;
import java.util.List;

public abstract class SimpleClient implements Client ,InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClient.class);

    private RequestHandlerChain handlerChain;

    private List<ParameterPair> headers;

    private String globalHost;

    private String globalCtx;

    public void afterPropertiesSet() throws Exception {
        if(handlerChain==null){
            handlerChain = new RequestHandlerChain();
            handlerChain.buildChain();
        }
    }

    public <T> T doRequest(Request request, Adapter<T> adapter) throws EasyWebserviceException {
        try{
            if(StringUtils.isEmpty(request.getHost())){
                request.setHost(globalHost);
            }
            if(StringUtils.isEmpty(request.getCtx())){
                request.setCtx(globalCtx);
            }
            final RequestInfo info = this.handlerChain.handle(request);
            writeLog(info);
            if(headers!=null){
                info.addHeaders(headers);
            }
            return adapter.convertTo(doRequest(info));
        }catch (Exception e){
            throw new EasyWebserviceException(e);
        }
    }

    private void writeLog(final RequestInfo info){
        if(LOGGER.isInfoEnabled()){
            LOGGER.info("Request HttpMethod : {}",info.getHttpMethod());
            final List<ParameterPair> headers = info.getHeaders();
            final StringBuffer hs = new StringBuffer("{");
            for(int i=0; i<headers.size(); i++){
                if(i>0){
                    hs.append(";");
                }
                final ParameterPair header = headers.get(i);
                hs.append(header.getKey()).append(":").append(header.getValue());
            }
            hs.append("}");
            LOGGER.info("Request Headers : {}",hs.toString());
            LOGGER.info("Request Url : {}",info.getRequestUrl());
            LOGGER.info("Request form data : {}",info.getFormData());
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

    public RequestHandlerChain getHandlerChain() {
        return handlerChain;
    }

    public void setHandlerChain(RequestHandlerChain handlerChain) {
        this.handlerChain = handlerChain;
    }

    public String getGlobalHost() {
        return globalHost;
    }

    public void setGlobalHost(String globalHost) {
        this.globalHost = globalHost;
    }

    public String getGlobalCtx() {
        return globalCtx;
    }

    public void setGlobalCtx(String globalCtx) {
        this.globalCtx = globalCtx;
    }
}
