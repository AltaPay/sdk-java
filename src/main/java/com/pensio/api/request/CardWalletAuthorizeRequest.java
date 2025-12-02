package com.pensio.api.request;

public class CardWalletAuthorizeRequest {

    private String paymentId;
    private String providerData;
    private final PaymentInfos paymentInfos = new PaymentInfos();

    public PaymentInfos getPaymentInfos() {
        return paymentInfos;
    }

    public CardWalletAuthorizeRequest addPaymentInfo(String key, String value) {
        paymentInfos.add(key, value);
        return this;
    }

    public String getProviderData() {
        return providerData;
    }

    public CardWalletAuthorizeRequest setProviderData(String providerData) {
        this.providerData = providerData;
        return this;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public CardWalletAuthorizeRequest setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

}
