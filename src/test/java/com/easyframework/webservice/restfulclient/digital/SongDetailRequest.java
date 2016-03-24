package com.easyframework.webservice.restfulclient.digital;

import com.easyframework.webservice.restfulclient.annotation.Path;
import com.easyframework.webservice.restfulclient.GetRequest;
import com.easyframework.webservice.restfulclient.annotation.SupperAvailable;

@SupperAvailable
public class SongDetailRequest extends GetRequest {
    @Path
    private String cdin;
    @Path
    private String country = "us";
    private String hashedCustomerId = "CustomerId";
    private String tenantId = "tenantId123456";

    public String getCdin() {
        return cdin;
    }

    public void setCdin(String cdin) {
        this.cdin = cdin;
    }
}
