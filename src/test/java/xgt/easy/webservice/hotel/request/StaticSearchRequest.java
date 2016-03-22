package xgt.easy.webservice.hotel.request;

import xgt.easy.webservice.GetRequest;
import xgt.easy.webservice.annotation.Path;

public class StaticSearchRequest extends GetRequest {
    @Path
    private String method = "destinations";
    @Path
    private String destId;
    @Path
    private String lang = "zh_CN";
    @Path
    private String format = "long.json";

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getDestId() {
        return destId;
    }

    public void setDestId(String destId) {
        this.destId = destId;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
