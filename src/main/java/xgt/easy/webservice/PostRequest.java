package xgt.easy.webservice;

import org.apache.http.entity.ContentType;
import xgt.easy.webservice.annotation.Ignore;
import xgt.easy.webservice.model.PostContentType;

public class PostRequest extends Request {

    @Ignore
    private String applicationType = PostContentType.FORM_DATA;

    public String getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(String applicationType) {
        this.applicationType = applicationType;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
