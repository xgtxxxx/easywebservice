package xgt.easy.webservice.hotel.request;

import xgt.easy.webservice.PostRequest;
import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.Rename;

import java.util.Map;

public class BookPolicyRequest extends PostRequest{
    @Path
    private String method;
    private Map search;
    @Rename("package")
    private Map packages;
    private Map config;
}
