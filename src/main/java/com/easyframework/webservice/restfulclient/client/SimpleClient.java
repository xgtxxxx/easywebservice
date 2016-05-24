package com.easyframework.webservice.restfulclient.client;

import com.easyframework.webservice.restfulclient.Client;
import com.easyframework.webservice.restfulclient.Handler;
import com.easyframework.webservice.restfulclient.Request;
import com.easyframework.webservice.restfulclient.adapters.responseadapter.ResponseAdapter;
import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;
import com.easyframework.webservice.restfulclient.handler.annotationhandler.HandlerFactory;
import com.easyframework.webservice.restfulclient.handler.annotationhandler.RequestHandlerChainFactory;
import com.easyframework.webservice.restfulclient.model.ParameterPair;
import com.easyframework.webservice.restfulclient.model.PostContentType;
import com.easyframework.webservice.restfulclient.model.RequestInfo;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;
import com.easyframework.webservice.restfulclient.utils.JsonUtils;
import com.easyframework.webservice.restfulclient.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public abstract class SimpleClient implements Client {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleClient.class);

    private List<ParameterPair> headers;

    private String globalHost;

    private String globalCtx;

    private HandlerFactory handlerFactory;

    private Object lock = new Object();

    public <T> T doRequest(Request request, ResponseAdapter<T> adapter) throws EasyWebserviceException {
        try{
            if(StringUtils.isEmpty(request.getHost())){
                request.setHost(globalHost);
            }
            if(StringUtils.isEmpty(request.getCtx())){
                request.setCtx(globalCtx);
            }
            final RequestInfo info = getHandler().handle(request);
            if(headers!=null){
                info.addHeaders(headers);
            }
            writeLog(info);
            return adapter.convertTo(doRequest(info));
        }catch (Exception e){
            throw new EasyWebserviceException(e);
        }
    }

    private Handler getHandler(){
        if(handlerFactory==null){
            handlerFactory = new RequestHandlerChainFactory();
        }
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
            final StringBuffer fd = new StringBuffer();
            if(info.getApplicationType().equals(PostContentType.JSON)){
                fd.append(JsonUtils.toJson(info.getFormData()));
            }else{
                fd.append("{");
                for(int i=0; i<info.getFormData().size(); i++){
                    if(i>0){
                        fd.append(";");
                    }
                    final ParameterPair header = info.getFormData().get(i);
                    fd.append(header.getKey()).append(":").append(header.getValue());
                }
                fd.append("}");
            }
            LOGGER.info("Request body : {}",fd);
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
