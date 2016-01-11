package xgt.easy.webservice;

import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.RequestInfo;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public abstract class Request {
    private List<ParameterPair> headers;

    public final RequestInfo build(final Handler handler) throws IllegalAccessException, UnsupportedEncodingException {
        return (RequestInfo)handler.handle(this);
    }

    public abstract HttpMethod getHttpMethod();

    public void addHeader(final String key,final String value){
        if(headers==null){
            headers = new ArrayList<ParameterPair>();
        }
        headers.add(new ParameterPair(key,value));
    }

    public List<ParameterPair> getHeaders() {
        return headers;
    }

    public void setHeaders(List<ParameterPair> headers) {
        this.headers = headers;
    }
}
