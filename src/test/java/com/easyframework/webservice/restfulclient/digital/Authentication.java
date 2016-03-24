package com.easyframework.webservice.restfulclient.digital;

import org.apache.commons.codec.binary.Base64;
import com.easyframework.webservice.restfulclient.model.ParameterPair;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.text.SimpleDateFormat;
import java.util.*;

public class Authentication {
    private static final String CDACCESSKEYID = "cdaccesskeyid";
    private static final String X_CD_DATE = "x-cd-date";
    private static final String AUTHORIZATION = "authorization";
    private static final String CONTENT_TYPE = "Content-type";
    private static final String ACCEPT = "Accept";

    public List<ParameterPair> getAuthenticationHeaders() {
        final List<ParameterPair> headers = new ArrayList<ParameterPair>();
        headers.add(new ParameterPair(CDACCESSKEYID, "2F65501DD16DF28C47691C32C44499FD"));
        final String date = getFormatDateOfNow();
        headers.add(new ParameterPair(X_CD_DATE, date));
        headers.add(new ParameterPair(AUTHORIZATION, calculateRFC2104HMAC(date, "e8ruK7rWRLHxbUioLZzrsB7JifTPn0nzksfnod9zAK31xDsfm7tyWWEleDLMokh")));
//        headers.add(new ParameterPair(CONTENT_TYPE, "application/json"));
        headers.add(new ParameterPair(ACCEPT, "application/json"));
        return headers;
    }

    private String getFormatDateOfNow() {
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+0"));
        return sdf.format(new Date());
    }

    public String calculateRFC2104HMAC(final String data, final String secretKey) {
        String result = null;
        try {
            // get an hmac_sha1 key from the raw key bytes
            final SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(), "HmacSHA1");

            // get an hmac_sha1 Mac instance and initialize with the signing key
            final Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);

            // compute the hmac on input data bytes
            final byte[] rawHmac = mac.doFinal(data.getBytes());

            // base64-encode the hmac
            result = Base64.encodeBase64String(rawHmac);
        } catch (final Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
