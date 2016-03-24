package com.easyframework.webservice.restfulclient;

import com.easyframework.webservice.restfulclient.model.PostContentType;
import com.easyframework.webservice.restfulclient.annotation.Ignore;

public class PostRequest extends Request {

    @Ignore
    private String applicationType = PostContentType.FORM_DATA;

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
