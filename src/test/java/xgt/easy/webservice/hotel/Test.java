package xgt.easy.webservice.hotel;

import org.junit.BeforeClass;
import xgt.easy.webservice.adapters.SimpleStringAdapter;
import xgt.easy.webservice.httpclient.client.HttpSimpleClient;
import xgt.easy.webservice.client.SimpleClient;
import xgt.easy.webservice.handler.RequestHandlerChainFactory;
import xgt.easy.webservice.hotel.request.AutoSuggestRequest;
import xgt.easy.webservice.hotel.request.HotelReviewRequest;
import xgt.easy.webservice.hotel.request.StaticSearchRequest;
import xgt.easy.webservice.hotel.util.RequestFactory;

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
            final AutoSuggestRequest request = (AutoSuggestRequest)RequestFactory.createStaticRequest(AutoSuggestRequest.class);
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
