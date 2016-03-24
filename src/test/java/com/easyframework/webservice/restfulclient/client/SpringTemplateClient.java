package com.easyframework.webservice.restfulclient.client;

import com.easyframework.webservice.restfulclient.digital.Authentication;
import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;
import com.easyframework.webservice.restfulclient.model.RequestInfo;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import com.easyframework.webservice.restfulclient.model.ParameterPair;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;

import java.util.List;

public class SpringTemplateClient extends SimpleClient {
    @Override
    public ResponseInfo doGet(RequestInfo info) throws EasyWebserviceException {
        RestTemplate restClient = new RestTemplate();
        ResponseInfo response = new ResponseInfo();

        final HttpHeaders headers = new HttpHeaders();
        final List<ParameterPair> items = new Authentication().getAuthenticationHeaders();
        for (final ParameterPair item : items) {
            headers.add(item.getKey(), item.getStringValue());
        }
        final HttpEntity<String> entity = new HttpEntity<String>(headers);


        response.setBody(restClient.exchange(info.getRequestUrl(), org.springframework.http.HttpMethod.GET, entity, String.class));
        return response;
    }

    @Override
    public ResponseInfo doPost(RequestInfo info) throws EasyWebserviceException {
        return null;
    }
}
