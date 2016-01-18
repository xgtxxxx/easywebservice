package xgt.easy.webservice.handler;

import xgt.easy.webservice.HttpMethod;
import xgt.easy.webservice.Request;
import xgt.easy.webservice.annotation.SupperAvailable;

@SupperAvailable
public class BaiduRequest extends Request {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
    @Override
    public String getHost() {
        return "http://www.baidu.com";
    }
}
