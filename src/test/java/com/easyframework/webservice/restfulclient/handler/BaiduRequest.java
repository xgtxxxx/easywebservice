package com.easyframework.webservice.restfulclient.handler;

import com.easyframework.webservice.restfulclient.HttpMethod;
import com.easyframework.webservice.restfulclient.Request;
import com.easyframework.webservice.restfulclient.annotation.SupperAvailable;

@SupperAvailable
public class BaiduRequest extends Request {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
    @Override
    public String getHost() {
        return "http://www.baidu.com";
    }
}
