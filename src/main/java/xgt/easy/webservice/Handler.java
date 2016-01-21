package xgt.easy.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xgt.easy.webservice.exception.EasyWebserviceException;
import xgt.easy.webservice.model.FieldInfo;
import xgt.easy.webservice.model.RequestInfo;

import javax.xml.ws.WebServiceException;
import java.io.UnsupportedEncodingException;

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
