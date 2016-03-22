package xgt.easy.webservice.hotel.request;

import xgt.easy.webservice.GetRequest;
import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.Rename;
import xgt.easy.webservice.annotation.Skip;

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
