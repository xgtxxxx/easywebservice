package com.easyframework.webservice.restfulclient.hotel.request;

import com.easyframework.webservice.restfulclient.GetRequest;
import com.easyframework.webservice.restfulclient.annotation.Path;
import com.easyframework.webservice.restfulclient.annotation.UrlParameter;
import com.easyframework.webservice.restfulclient.annotation.Skip;

public class HotelReviewRequest extends GetRequest {
    @Path
    private String method = "hotel_reviews";
    @Path
    private String hotelId;
    @UrlParameter
    @Skip
    private String lang;

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getHotelId() {
        return hotelId;
    }

    public void setHotelId(String hotelId) {
        this.hotelId = hotelId;
    }
}
