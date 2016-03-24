package com.easyframework.webservice.restfulclient;

public class GetRequest extends Request {

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
}
