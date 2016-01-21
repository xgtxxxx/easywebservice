package xgt.easy.webservice;

public class GetRequest extends Request {

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }
}
