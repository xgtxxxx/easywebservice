package xgt.easy.webservice.hotel.request;

import xgt.easy.webservice.GetRequest;
import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.Skip;
import xgt.easy.webservice.annotation.UrlParameter;

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
