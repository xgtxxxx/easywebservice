package xgt.easy.webservice.httpclient.client;

import xgt.easy.webservice.exception.EasyWebserviceException;
import xgt.easy.webservice.httpclient.client.HttpAbstractClient;
import xgt.easy.webservice.model.RequestInfo;
import xgt.easy.webservice.model.ResponseInfo;

public class HttpSimpleClient extends HttpAbstractClient {

    @Override
    public ResponseInfo doGet(RequestInfo info) throws EasyWebserviceException {
        return this.execute(createGet(info));
    }

    @Override
    public ResponseInfo doPost(RequestInfo info) throws EasyWebserviceException {
        return this.execute(createPost(info));
    }

}
