package xgt.easy.webservice;

import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.SupperAvailable;

/**
 * Created by xgt on 2015/12/30.
 */
public abstract class Request {
    private String testPrivate = "private";

    @Path
    protected String testProtected = "protected";

    public String testPublic = "public";

    public final RequestInfo build(final Handler handler){
        return handler.handle(this);
    }
}
