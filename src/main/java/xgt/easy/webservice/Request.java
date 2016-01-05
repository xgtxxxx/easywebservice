package xgt.easy.webservice;

import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.model.RequestInfo;

import java.io.UnsupportedEncodingException;

/**
 * Created by xgt on 2015/12/30.
 */
public abstract class Request {
    public final RequestInfo build(final Handler handler) throws IllegalAccessException, UnsupportedEncodingException {
        return (RequestInfo)handler.handle(this);
    }

    public abstract HttpMethod getHttpMethod();
}
