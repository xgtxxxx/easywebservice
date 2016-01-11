package xgt.easy.webservice.request;

import xgt.easy.webservice.HttpMethod;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.annotation.*;

import java.lang.annotation.Annotation;
import java.lang.annotation.Inherited;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class SimpleRequest extends Request {
    @Path(append = "")
    @Order(-1)
    @Skip
    private String host;
    @Path
    @Order(0)
    @Skip
    private String ctx;

    public String getCtx() {
        return ctx;
    }

    public void setCtx(String ctx) {
        this.ctx = ctx;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }
}
