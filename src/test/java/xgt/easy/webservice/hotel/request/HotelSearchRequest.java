package xgt.easy.webservice.hotel.request;

import xgt.easy.webservice.GetRequest;
import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.Rename;
import xgt.easy.webservice.annotation.Skip;

@Skip
public class HotelSearchRequest extends GetRequest {
    @Path
    private String method;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Rename("destination_id")
    private String destId;
    @Rename("check_in_date")
    private String checkInDate;
    @Rename("check_out_date")
    private String checkOutDate;
    @Rename("room_count")
    private Integer roomCount;
    @Rename("adult_count")
    private Integer adultCount;
    @Rename("child_count")
    private Integer childCount;
    private String currency;
    @Rename("source_market")
    private String sourceMarket;

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(String checkInDate) {
        this.checkInDate = checkInDate;
    }

    public String getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(String checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Integer getAdultCount() {
        return adultCount;
    }

    public void setAdultCount(Integer adultCount) {
        this.adultCount = adultCount;
    }

    public Integer getChildCount() {
        return childCount;
    }

    public void setChildCount(Integer childCount) {
        this.childCount = childCount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSourceMarket() {
        return sourceMarket;
    }

    public void setSourceMarket(String sourceMarket) {
        this.sourceMarket = sourceMarket;
    }
}
