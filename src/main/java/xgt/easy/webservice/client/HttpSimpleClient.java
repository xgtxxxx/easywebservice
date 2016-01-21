package xgt.easy.webservice.client;

import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import xgt.easy.webservice.exception.EasyWebserviceException;
import xgt.easy.webservice.model.ParameterPair;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;

import java.io.IOException;
import java.util.List;

public class HttpSimpleClient extends HttpAbstractClient{

    @Override
    public ResponseInfo doGet(RequestInfo info) throws EasyWebserviceException {
        return this.execute(createGet(info));
    }

    @Override
    public ResponseInfo doPost(RequestInfo info) throws EasyWebserviceException {
        return this.execute(createPost(info));
    }

}
