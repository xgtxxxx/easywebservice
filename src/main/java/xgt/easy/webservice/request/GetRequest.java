package xgt.easy.webservice.request;

import xgt.easy.webservice.HttpMethod;
import xgt.easy.webservice.annotation.Encode;
import xgt.easy.webservice.annotation.Filter;
import xgt.easy.webservice.annotation.SupperAvailable;

@SupperAvailable
@Encode
@Filter
public class GetRequest extends SimpleRequest{
    private Integer start;
    private String term;

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
