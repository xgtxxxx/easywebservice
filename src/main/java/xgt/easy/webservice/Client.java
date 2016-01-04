package xgt.easy.webservice;

/**
 * Created by xgt on 2015/12/30.
 */
public interface Client {
    public <T> T doRequest(final Request request,final Adapter<Object,T> adapter) throws IllegalAccessException;
}
