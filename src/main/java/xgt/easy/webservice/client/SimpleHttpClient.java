package xgt.easy.webservice.client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;

import java.io.IOException;
import java.util.List;

public abstract class SimpleHttpClient extends SimpleClient{

    public ResponseInfo execute(final HttpRequestBase request) throws IOException {
        CloseableHttpClient client = HttpClients.createDefault();
        try{
            return execute(client,request);
        }finally {
            client.close();
        }
    }

    public ResponseInfo execute(final CloseableHttpClient client, final HttpRequestBase request) throws IOException {
        CloseableHttpResponse response = client.execute(request);
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            Header[] headers = response.getAllHeaders();
            for (Header header:headers){
                responseInfo.addHeader(header.getName(),header.getValue());
            }
            responseInfo.setCode(response.getStatusLine().getStatusCode());
            responseInfo.setBody(EntityUtils.toString(response.getEntity()));
        } finally {
            response.close();
        }
        return responseInfo;
    }

    public HttpGet createGet(final RequestInfo info){
        HttpGet get = new HttpGet(info.getRequestUrl());
        List<ParameterPair> headers = info.getHeaders();
        if(!headers.isEmpty()){
            for(ParameterPair header : headers)
            get.addHeader(header.getKey(),header.getStringValue());
        }
        return get;
    }
}
