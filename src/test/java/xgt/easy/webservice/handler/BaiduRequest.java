package xgt.easy.webservice.handler;

import xgt.easy.webservice.HttpMethod;
import xgt.easy.webservice.annotation.SupperAvailable;
import xgt.easy.webservice.client.SimpleClient;
import xgt.easy.webservice.request.SimpleRequest;

@SupperAvailable
public class BaiduRequest extends SimpleRequest{
    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    private String keyWord = "api";

    private String rsv_spt = "1";

    private String rsv_iqid = "0xce40268300033c2f";

    private String issp = "1";

    private String f = "8";

    private String rsv_bp = "1";

    private String rsv_idx = "2";

    private String ie = "utf-8";

    private String tn = "baiduhome_pg";

    private String rsv_enter = "1";

    private String rsv_sug3 = "4";

    private String rsv_sug1 = "3";

    private String rsv_sug2 = "0";

    private String rsv_sug7 = "100";

    private String inputT = "1161";

    private String rsv_sug4 = "2132";
}
