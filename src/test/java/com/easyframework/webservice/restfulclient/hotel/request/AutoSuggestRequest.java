package com.easyframework.webservice.restfulclient.hotel.request;

import com.easyframework.webservice.restfulclient.GetRequest;
import com.easyframework.webservice.restfulclient.annotation.Path;
import com.easyframework.webservice.restfulclient.annotation.Rename;
import com.easyframework.webservice.restfulclient.annotation.Skip;

public class AutoSuggestRequest extends GetRequest {
    @Path
    private String method = "autosuggest";
    private String term;
    @Rename("types[]")
    private String types;
    @Skip
    private Integer limit;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
