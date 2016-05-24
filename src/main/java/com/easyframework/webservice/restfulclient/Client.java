package com.easyframework.webservice.restfulclient;

import com.easyframework.webservice.restfulclient.adapters.responseadapter.ResponseAdapter;

import javax.xml.ws.WebServiceException;

public interface Client {
    public <T> T doRequest(final Request request,final ResponseAdapter<T> adapter) throws WebServiceException;
}
