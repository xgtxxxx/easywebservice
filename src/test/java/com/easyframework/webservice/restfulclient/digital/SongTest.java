package com.easyframework.webservice.restfulclient.digital;

import com.easyframework.webservice.restfulclient.Client;
import com.easyframework.webservice.restfulclient.ResponseAdapter;
import com.easyframework.webservice.restfulclient.utils.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-song-test.xml"})
public class SongTest {
    @Autowired
    private Client client;

    @Test
    public void testSongDetail(){
        SongDetailRequest request = new SongDetailRequest();
        request.setHost("https://api-stage.choosedigital.com");
        request.setCtx("/music/detail");
        request.setCdin("ad0e0846de6a932d897db5d3176db80e7b469819");
        Authentication authentication = new Authentication();
        request.setHeaders(authentication.getAuthenticationHeaders());
        String result = client.doRequest(request, new ResponseAdapter<String>(){
            public String convertTo(ResponseInfo f) {
                return StringUtils.toString(f.getBody());
            }
        });
        System.out.println(result);
    }

    @Test
    public void testPurchaseSong(){
        Authentication authentication = new Authentication();
        final MusicPurchaseRequest musicPurchaseRequest = new MusicPurchaseRequest();
        musicPurchaseRequest.setHost("https://api-stage.choosedigital.com");
        musicPurchaseRequest.setCtx("/music/purchase");
        musicPurchaseRequest.setHeaders(authentication.getAuthenticationHeaders());
        musicPurchaseRequest.addHeader("Content-Type","application/x-www-form-urlencoded");
        musicPurchaseRequest.setCdin("ad0e0846de6a932d897db5d3176db80e7b469819");
        musicPurchaseRequest.setPriceSold(99);
        musicPurchaseRequest.setTaxAmount(0);
        musicPurchaseRequest.setCustomerId("CustomerId");
        musicPurchaseRequest.setCustomerCountryCode("us");
        musicPurchaseRequest.setCustomerPostalCode("30005");
        musicPurchaseRequest.setAuthentication(authentication.calculateRFC2104HMAC(
                musicPurchaseRequest.getCdin()
                        + musicPurchaseRequest.getCustomerId()
                        + musicPurchaseRequest.getCustomerCountryCode()
                        + musicPurchaseRequest.getPriceSold(),
                "e8ruK7rWRLHxbUioLZzrsB7JifTPn0nzksfnod9zAK31xDsfm7tyWWEleDLMokh"));
        musicPurchaseRequest.setTenantId("tenantId123456");
        String result = client.doRequest(musicPurchaseRequest, new ResponseAdapter<String>() {
            public String convertTo(ResponseInfo f) {
                return StringUtils.toString(f.getBody());
            }
        });
        System.out.println(result);
    }

    @Test
    public void testSearch(){
        SearchRequest request = new SearchRequest();
        request.setHost("https://api-stage.choosedigital.com");
        request.setCtx("/music/search");
        request.setTerm("aa-bb");
        request.setHeaders(new Authentication().getAuthenticationHeaders());
        final String result = client.doRequest(request, new ResponseAdapter<String>() {
            public String convertTo(ResponseInfo f) {
                return StringUtils.toString(f.getBody());
            }
        });
        System.out.println(result);
    }
}
