package xgt.easy.webservice.model;

import xgt.easy.webservice.HttpMethod;

import java.util.Map;

public class RequestInfo {
    private HttpMethod httpMethod;

    private String requestUrl;

    private Map<String,Object> formData;

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

    public Map<String, Object> getFormData() {
        return formData;
    }

    public void setFormData(Map<String, Object> formData) {
        this.formData = formData;
    }
}
