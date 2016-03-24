package com.easyframework.webservice.restfulclient.hotel.request;

import com.easyframework.webservice.restfulclient.PostRequest;
import com.easyframework.webservice.restfulclient.annotation.Path;
import com.easyframework.webservice.restfulclient.annotation.Rename;

import java.util.Map;

public class BookPolicyRequest extends PostRequest{
    @Path
    private String method;
    private Map search;
    @Rename("package")
    private Map packages;
    private Map config;
}
