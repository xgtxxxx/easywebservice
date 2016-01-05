package xgt.easy.webservice.model;

import xgt.easy.webservice.HttpMethod;

import java.util.List;
import java.util.Map;

public class RequestInfo {
    private HttpMethod httpMethod;

    private String requestUrl;

    private List<FormPair> formData;

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public List<FormPair> getFormData() {
        return formData;
    }

    public void setFormData(List<FormPair> formData) {
        this.formData = formData;
    }
}
