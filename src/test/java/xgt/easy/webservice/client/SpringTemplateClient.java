package xgt.easy.webservice.client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import xgt.easy.webservice.digital.Authentication;
import xgt.easy.webservice.exception.EasyWebserviceException;
import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;

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
