package xgt.easy.webservice.hotel.request;

import xgt.easy.webservice.GetRequest;
import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.Skip;

public class CommonRequest extends GetRequest {
    @Path
    private String method;
    @Path
    @Skip
    private String param;
}
