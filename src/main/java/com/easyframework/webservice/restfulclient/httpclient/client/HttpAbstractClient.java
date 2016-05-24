package com.easyframework.webservice.restfulclient.httpclient.client;

import com.easyframework.webservice.restfulclient.adapters.requestadapter.PostBodyAdapter;
import com.easyframework.webservice.restfulclient.client.AbstractSimpleClient;
import com.easyframework.webservice.restfulclient.exception.EasyWebserviceException;
import com.easyframework.webservice.restfulclient.httpclient.HttpPostBodyAdapter;
import com.easyframework.webservice.restfulclient.model.ParameterPair;
import com.easyframework.webservice.restfulclient.model.RequestInfo;
import com.easyframework.webservice.restfulclient.model.ResponseInfo;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HttpAbstractClient extends AbstractSimpleClient<CloseableHttpClient> {

    private PostBodyAdapter<HttpEntity> entityAdapter;

    public ResponseInfo execute(final HttpRequestBase request) throws EasyWebserviceException {
        CloseableHttpClient client = clientFactory==null?HttpClients.createDefault():clientFactory.build();
        try {
            return execute(client,request);
        } finally{
            if(clientFactory==null){
                try{
                    client.close();
                }catch (IOException e){
                    throw new EasyWebserviceException(e);
                }
            }
        }
    }

    public ResponseInfo execute(final CloseableHttpClient client, final HttpRequestBase request) throws EasyWebserviceException {
        CloseableHttpResponse response = null;
        ResponseInfo responseInfo = new ResponseInfo();
        try {
            response = client.execute(request);
            Header[] headers = response.getAllHeaders();
            for (Header header:headers){
                responseInfo.addHeader(header.getName(),header.getValue());
            }
            responseInfo.setCode(response.getStatusLine().getStatusCode());
            responseInfo.setBody(EntityUtils.toString(response.getEntity()));
        } catch(IOException e){
            throw new EasyWebserviceException(e);
        } finally{
            try {
                response.close();
            } catch (IOException e) {
                throw new EasyWebserviceException(e);
            }
        }
        return responseInfo;
    }

    public HttpGet createGet(final RequestInfo info){
        HttpGet get = new HttpGet(info.getRequestUrl());
        addHeaders(info,get);
        return get;
    }

    public HttpPost createPost(final RequestInfo info){
        HttpPost post = new HttpPost(info.getRequestUrl());
        addHeaders(info,post);
        post.setEntity(createEntity(info));
        return post;
    }

    private void addHeaders(final RequestInfo info,final HttpRequestBase request){
        List<ParameterPair> headers = info.getHeaders();
        if(!headers.isEmpty()){
            for(ParameterPair header : headers){
                request.addHeader(header.getKey(),header.getStringValue());
            }
        }
    }

    private HttpEntity createEntity(final RequestInfo info){
        if(entityAdapter==null){
            entityAdapter = new HttpPostBodyAdapter();
        }
        return entityAdapter.convertTo(info);
    }

    public void setEntityAdapter(PostBodyAdapter<HttpEntity> entityAdapter) {
        this.entityAdapter = entityAdapter;
    }
}
