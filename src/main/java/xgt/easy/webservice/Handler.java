package xgt.easy.webservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

public abstract class Handler {
    private static final Logger LOG = LoggerFactory.getLogger(Handler.class);
    private Handler next;
    public abstract Object handle(final Object request) throws IllegalAccessException, UnsupportedEncodingException;

    protected Object fireNext(final Object request) throws IllegalAccessException, UnsupportedEncodingException {
        if(next!=null){
            return next.handle(request);
        }else{
            return request;
        }
    }

    public void setNext(Handler next) {
        this.next = next;
    }

    protected Logger getLogger(){
        return LOG;
    }
}
