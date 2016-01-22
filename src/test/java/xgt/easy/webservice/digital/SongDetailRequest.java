package xgt.easy.webservice.digital;

import org.apache.commons.codec.digest.DigestUtils;
import xgt.easy.webservice.GetRequest;
import xgt.easy.webservice.annotation.Path;
import xgt.easy.webservice.annotation.SupperAvailable;

@SupperAvailable
public class SongDetailRequest extends GetRequest {
    @Path
    private String cdin;
    @Path
    private String country = "us";
    private String hashedCustomerId = "CustomerId";
    private String tenantId = "tenantId123456";

    public String getCdin() {
        return cdin;
    }

    public void setCdin(String cdin) {
        this.cdin = cdin;
    }
}
