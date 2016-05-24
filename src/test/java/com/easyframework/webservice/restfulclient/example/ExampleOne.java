package com.easyframework.webservice.restfulclient.example;

import com.easyframework.webservice.restfulclient.Client;
import com.easyframework.webservice.restfulclient.Request;
import com.easyframework.webservice.restfulclient.handler.BaiduRequest;
import com.easyframework.webservice.restfulclient.httpclient.client.HttpSimpleClient;
import com.easyframework.webservice.restfulclient.adapters.responseadapter.SimpleStringAdapter;

public class ExampleOne {
    public static void main(String[] args) {
        Client client = new HttpSimpleClient();
        Request request = new BaiduRequest();
        String html = client.doRequest(request,new SimpleStringAdapter());
        System.out.println(html);
    }
}
