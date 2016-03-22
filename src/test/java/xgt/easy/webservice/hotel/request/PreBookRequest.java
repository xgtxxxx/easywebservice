package xgt.easy.webservice.hotel.request;

import xgt.easy.webservice.PostRequest;
import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.Rename;
import xgt.easy.webservice.annotation.Skip;

import java.util.Map;

@Skip
public class PreBookRequest extends PostRequest{
    @Path
    private String method;
    private Map guest;
    @Rename("booking_policy_id")
    private String policyId;
}
