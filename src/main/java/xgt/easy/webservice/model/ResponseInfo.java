package xgt.easy.webservice.model;

import java.util.ArrayList;
import java.util.List;

public class ResponseInfo {
    private int code;
    private List<ParameterPair> headers;
    private Object body;

    public void addHeader(final String key, final String value){
        if(headers==null){
            headers = new ArrayList<ParameterPair>();
        }
        headers.add(new ParameterPair(key,value));
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ParameterPair> getHeaders() {
        return headers;
    }

    public void setHeaders(List<ParameterPair> headers) {
        this.headers = headers;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
