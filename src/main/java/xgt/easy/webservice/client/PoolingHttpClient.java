package xgt.easy.webservice.client;

import org.springframework.beans.factory.InitializingBean;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;

import java.io.IOException;

public class PoolingHttpClient extends SimpleHttpClient implements InitializingBean{

    private HttpClientFactory httpClientFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        super.afterPropertiesSet();
        if(httpClientFactory==null){
            httpClientFactory = new HttpClientFactory();
            httpClientFactory.build();
        }
    }

    @Override
    public ResponseInfo doGet(RequestInfo info) throws IOException {
        return execute(httpClientFactory.getClient(),createGet(info));
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
