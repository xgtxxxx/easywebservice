package xgt.easy.webservice.digital;

import xgt.easy.webservice.PostRequest;
import xgt.easy.webservice.annotation.Skip;
import xgt.easy.webservice.annotation.SupperAvailable;

@SupperAvailable
@Skip
public class MusicPurchaseRequest extends PostRequest {
    private String cdin;
    private Integer priceSold;
    private Integer taxAmount;
    private String customerId;
    private String externalMemberId;
    private String customerEmail;
    private String customerPostalCode;
    private String customerCountryCode;
    private String promoCode;
    private String tenantId;
    private String transactionId;
    private String segment;
    private String tier;
    private String authentication;

    public String getCdin() {
        return cdin;
    }

    public void setCdin(String cdin) {
        this.cdin = cdin;
    }

    public Integer getPriceSold() {
        return priceSold;
    }

    public void setPriceSold(Integer priceSold) {
        this.priceSold = priceSold;
    }

    public Integer getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getExternalMemberId() {
        return externalMemberId;
    }

    public void setExternalMemberId(String externalMemberId) {
        this.externalMemberId = externalMemberId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    public String getCustomerCountryCode() {
        return customerCountryCode;
    }

    public void setCustomerCountryCode(String customerCountryCode) {
        this.customerCountryCode = customerCountryCode;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getTier() {
        return tier;
    }

    public void setTier(String tier) {
        this.tier = tier;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }
}
