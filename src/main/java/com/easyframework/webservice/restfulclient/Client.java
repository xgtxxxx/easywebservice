package com.easyframework.webservice.restfulclient;

import javax.xml.ws.WebServiceException;

public interface Client {
    public <T> T doRequest(final Request request,final ResponseAdapter<T> adapter) throws WebServiceException;
}
