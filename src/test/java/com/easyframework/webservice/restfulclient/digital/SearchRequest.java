package com.easyframework.webservice.restfulclient.digital;

import com.easyframework.webservice.restfulclient.annotation.Filter;
import com.easyframework.webservice.restfulclient.model.Action;
import com.easyframework.webservice.restfulclient.GetRequest;
import com.easyframework.webservice.restfulclient.annotation.Encode;

@Encode
public class SearchRequest extends GetRequest{
    @Filter(Action.AFTER_ENCODE)
    private String term;
    private String country = "us";

    public void setTerm(String term) {
        this.term = term;
    }

    private String tenantId = "testtenantId";
}
