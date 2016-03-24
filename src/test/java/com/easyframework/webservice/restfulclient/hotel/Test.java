package com.easyframework.webservice.restfulclient.hotel;

import com.easyframework.webservice.restfulclient.client.SimpleClient;
import com.easyframework.webservice.restfulclient.handler.RequestHandlerChainFactory;
import com.easyframework.webservice.restfulclient.hotel.request.StaticSearchRequest;
import com.easyframework.webservice.restfulclient.hotel.util.RequestFactory;
import org.junit.BeforeClass;
import com.easyframework.webservice.restfulclient.adapters.SimpleStringAdapter;
import com.easyframework.webservice.restfulclient.httpclient.client.HttpSimpleClient;
import com.easyframework.webservice.restfulclient.hotel.request.AutoSuggestRequest;
import com.easyframework.webservice.restfulclient.hotel.request.HotelReviewRequest;

import java.util.Date;

public class Test {

    static SimpleClient client;

    @BeforeClass
    public static void setup(){
        client = new HttpSimpleClient();
        client.setHandlerFactory(new RequestHandlerChainFactory());
    }

    @org.junit.Test
    public void testAutoSuggest(){
        try{
            final AutoSuggestRequest request = (AutoSuggestRequest) RequestFactory.createStaticRequest(AutoSuggestRequest.class);
            request.setTerm("aL");
            request.setTypes("city");
            final String result = client.doRequest(request, new SimpleStringAdapter());
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testSearchHotel(){
        try{
            final StaticSearchRequest request = (StaticSearchRequest)RequestFactory.createStaticRequest(StaticSearchRequest.class);
            request.setDestId("3TL6");
            Date start = new Date();
            final String result = client.doRequest(request, new SimpleStringAdapter());
            System.out.println(result);
            Date end = new Date();
            System.out.println("total time: "+(end.getTime()-start.getTime())/1000);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void testReviewHotel(){
        try {
            final HotelReviewRequest request = (HotelReviewRequest)RequestFactory.createStaticRequest(HotelReviewRequest.class);
//            request.setLang("zh_CN");
            request.setHotelId("yAbP");
            final String result = client.doRequest(request, new SimpleStringAdapter());
            System.out.println(result);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
