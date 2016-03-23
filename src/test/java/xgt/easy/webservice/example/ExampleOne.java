package xgt.easy.webservice.example;

import xgt.easy.webservice.Client;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.adapters.SimpleStringAdapter;
import xgt.easy.webservice.handler.BaiduRequest;
import xgt.easy.webservice.httpclient.client.HttpSimpleClient;

public class ExampleOne {
    public static void main(String[] args) {
        Client client = new HttpSimpleClient();
        Request request = new BaiduRequest();
        String html = client.doRequest(request,new SimpleStringAdapter());
        System.out.println(html);
    }
}
