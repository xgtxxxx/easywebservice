package xgt.easy.webservice.hotel.util;

import xgt.easy.webservice.Request;

public class RequestFactory {
    public static Request createRequest(final Class<?extends Request> clazz) throws IllegalAccessException, InstantiationException {
        final Request request = clazz.newInstance();
        request.setHost("http://data.zumata.com");
        request.addHeader("X-Api-Key","8bd7f1db-f492-4545-e4ef-cbc04b7b48e8");
        return request;
    }

    public static Request createStaticRequest(final Class<?extends Request> clazz) throws IllegalAccessException, InstantiationException {
        final Request request = clazz.newInstance();
        request.setHost("http://data.zumata.com");
        request.addHeader("X-Api-Key","8bd7f1db-f492-4545-e4ef-cbc04b7b48e8");
        return request;
    }
}
