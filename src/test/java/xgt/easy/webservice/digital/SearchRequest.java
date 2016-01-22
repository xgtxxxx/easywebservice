package xgt.easy.webservice.digital;

import xgt.easy.webservice.GetRequest;
import xgt.easy.webservice.annotation.Encode;
import xgt.easy.webservice.annotation.Filter;
import xgt.easy.webservice.model.Action;

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
