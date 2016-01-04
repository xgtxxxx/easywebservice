package xgt.easy.webservice;

import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.model.RequestInfo;

/**
 * Created by xgt on 2015/12/30.
 */
public abstract class Request {
    public final RequestInfo build(final Handler handler){
        return (RequestInfo)handler.handle(this);
    }

    public abstract HttpMethod getHttpMethod();
}
