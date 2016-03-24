package com.easyframework.webservice.restfulclient.httpclient.client;

import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;
import com.easyframework.webservice.restfulclient.model.RequestInfo;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;

public class HttpSimpleClient extends HttpAbstractClient {

    @Override
    public ResponseInfo doGet(RequestInfo info) throws EasyWebserviceException {
        return this.execute(createGet(info));
    }

    @Override
    public ResponseInfo doPost(RequestInfo info) throws EasyWebserviceException {
        return this.execute(createPost(info));
    }

}
