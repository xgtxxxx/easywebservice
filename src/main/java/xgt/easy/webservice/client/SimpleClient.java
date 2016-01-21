package xgt.easy.webservice.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xgt.easy.webservice.Adapter;
import xgt.easy.webservice.Client;
import xgt.easy.webservice.Handler;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.exception.EasyWebserviceException;
import xgt.easy.webservice.handler.HandlerFactory;
import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;
import xgt.easy.webservice.utils.StringUtils;

import java.util.List;

public abstract class SimpleClient implements Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClient.class);

    private List<ParameterPair> headers;

    private String globalHost;

    private String globalCtx;

    private HandlerFactory handlerFactory;

    private Object lock = new Object();

    public <T> T doRequest(Request request, Adapter<T> adapter) throws EasyWebserviceException {
        try{
            if(StringUtils.isEmpty(request.getHost())){
                request.setHost(globalHost);
            }
            if(StringUtils.isEmpty(request.getCtx())){
                request.setCtx(globalCtx);
            }
            final RequestInfo info = getHandler().handle(request);
            writeLog(info);
            if(headers!=null){
                info.addHeaders(headers);
            }
            return adapter.convertTo(doRequest(info));
        }catch (Exception e){
            throw new EasyWebserviceException(e);
        }
    }

    private Handler getHandler(){
        return handlerFactory.build();
    }

    private void writeLog(final RequestInfo info) throws EasyWebserviceException {
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
            final StringBuffer fd = new StringBuffer("{");
            for(int i=0; i<info.getFormData().size(); i++){
                if(i>0){
                    fd.append(";");
                }
                final ParameterPair header = info.getFormData().get(i);
                fd.append(header.getKey()).append(":").append(header.getValue());
            }
            fd.append("}");
            LOGGER.info("Request form data : {}",fd);
        }
    }

    private ResponseInfo doRequest(final RequestInfo info) throws EasyWebserviceException {
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

    public abstract ResponseInfo doGet(final RequestInfo info) throws EasyWebserviceException ;

    public abstract ResponseInfo doPost(final RequestInfo info) throws EasyWebserviceException ;

    public ResponseInfo doPut(final RequestInfo info) throws EasyWebserviceException {
        throw new EasyWebserviceException("Method not support!");
    }

    public ResponseInfo doDelete(final RequestInfo info){
        throw new EasyWebserviceException("Method not support!");
    }

    public List<ParameterPair> getHeaders() {
        return headers;
    }

    public void setHeaders(List<ParameterPair> headers) {
        this.headers = headers;
    }

    public HandlerFactory getHandlerFactory() {
        return handlerFactory;
    }

    public void setHandlerFactory(HandlerFactory handlerFactory) {
        this.handlerFactory = handlerFactory;
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
