package xgt.easy.webservice.client;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import xgt.easy.webservice.Adapter;
import xgt.easy.webservice.Client;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.handler.RequestHandlerChain;
import xgt.easy.webservice.model.FormPair;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.utils.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class SimpleClient implements Client {

    private RequestHandlerChain handlerChain;

    public <T> T doRequest(Request request, Adapter<Object, T> adapter) throws IllegalAccessException, IOException {
        final RequestInfo info = this.handlerChain.handle(request);
        CloseableHttpClient httpclient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        try {
            response = httpclient.execute(getHttpUriRequest(info));
            return adapter.convertTo(response.getEntity().getContent());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            response.close();
            httpclient.close();
        }
        return null;
    }

    private HttpUriRequest getHttpUriRequest(final RequestInfo info) throws UnsupportedEncodingException {
        HttpUriRequest request = null;
        switch (info.getHttpMethod()){
            case GET:
                request = new HttpGet(info.getRequestUrl());
                break;
            case POST:
                HttpPost post = new HttpPost(info.getRequestUrl());
                post.setEntity(new UrlEncodedFormEntity(convertTo(info.getFormData())));
                request = post;
                break;
            case PUT:
                //TODO
                break;
            case DELETE:
                //TODO
                break;
        }
        return request;
    }

    private List<NameValuePair> convertTo(List<FormPair> formPairs){
        List <NameValuePair> nvps = new ArrayList<NameValuePair>();
        for(FormPair formPair:formPairs){
            nvps.add(new BasicNameValuePair(formPair.getKey(), StringUtils.toString(formPair.getValue())));
        }
        return nvps;
    }
}
