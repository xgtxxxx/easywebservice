package com.easyframework.webservice.restfulclient.hotel.request;

import com.easyframework.webservice.restfulclient.PostRequest;
import com.easyframework.webservice.restfulclient.annotation.Path;
import com.easyframework.webservice.restfulclient.annotation.Rename;
import com.easyframework.webservice.restfulclient.annotation.Skip;

import java.util.Map;

@Skip
public class PreBookRequest extends PostRequest{
    @Path
    private String method;
    private Map guest;
    @Rename("booking_policy_id")
    private String policyId;
}
