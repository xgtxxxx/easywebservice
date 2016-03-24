package com.easyframework.webservice.restfulclient;

import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;
import com.easyframework.webservice.restfulclient.model.FieldInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Handler{
    private static final Logger LOG = LoggerFactory.getLogger(Handler.class);
    private Handler next;
    public abstract <T> T handle(final Object request) throws EasyWebserviceException;

    protected FieldInfo fireNext(final Object request) throws EasyWebserviceException {
        if(next!=null){
            return next.handle(request);
        }else{
            return (FieldInfo)request;
        }
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    protected Logger getLogger(){
        return LOG;
    }
}
