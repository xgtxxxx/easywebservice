package com.easyframework.webservice.restfulclient.hotel.request;

import com.easyframework.webservice.restfulclient.GetRequest;
import com.easyframework.webservice.restfulclient.annotation.Path;
import com.easyframework.webservice.restfulclient.annotation.Skip;

public class CommonRequest extends GetRequest {
    @Path
    private String method;
    @Path
    @Skip
    private String param;
}
