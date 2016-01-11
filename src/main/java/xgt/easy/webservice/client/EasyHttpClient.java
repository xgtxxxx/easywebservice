package xgt.easy.webservice.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;

import java.io.IOException;

//time out
//pooling
public class EasyHttpClient extends SimpleClient {
    @Override
    public ResponseInfo doGet(RequestInfo info) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(info.getRequestUrl());
        CloseableHttpResponse response = httpclient.execute(httpGet);
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

    @Override
    public ResponseInfo doPost(RequestInfo info) {
        return null;
    }

    @Override
    public ResponseInfo doPut(RequestInfo info) {
        return null;
    }

    @Override
    public ResponseInfo doDelete(RequestInfo info) {
        return null;
    }
}
