package xgt.easy.webservice;

import javax.xml.ws.WebServiceException;

public interface Client {
    public <T> T doRequest(final Request request,final Adapter<T> adapter) throws WebServiceException;
}
