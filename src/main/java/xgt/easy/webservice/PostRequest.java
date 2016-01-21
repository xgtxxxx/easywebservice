package xgt.easy.webservice;

public class PostRequest extends Request {
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }
}
