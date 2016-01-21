package xgt.easy.webservice.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import xgt.easy.webservice.exception.EasyWebserviceException;
import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class HttpAbstractClient extends SimpleClient {

    private HttpAbstractClientFactory httpClientFactory;

    public ResponseInfo execute(final HttpRequestBase request) throws EasyWebserviceException {
        CloseableHttpClient client = httpClientFactory==null?HttpClients.createDefault():httpClientFactory.build();
        try {
            return execute(client,request);
        } finally{
            if(httpClientFactory==null){
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
        post.setEntity(createEntity(info.getFormData()));
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

    private HttpEntity createEntity(final List<ParameterPair> parameters){
        StringBuffer sb = new StringBuffer();
        for(ParameterPair parameter: parameters){
            if(sb.length()>0){
                sb.append('&');
            }
            sb.append(parameter.getKey()).append('=').append(parameter.getStringValue());
        }
        return new StringEntity(sb.toString(),ContentType.create("application/x-www-form-urlencoded", "utf-8"));
    }

    private Map<String,String> toMap(List<ParameterPair> list){
        Map<String,String> map = new HashMap<String, String>();
        for(ParameterPair parameterPair:list){
            map.put(parameterPair.getKey(),parameterPair.getStringValue());
        }
        return map;
    }

    public void setHttpClientFactory(HttpAbstractClientFactory httpClientFactory) {
        this.httpClientFactory = httpClientFactory;
    }
}
