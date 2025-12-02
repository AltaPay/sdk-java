package com.pensio.api.request;

import com.pensio.Amount;

public class CardWalletAuthorizeRequest extends PaymentRequest<CardWalletAuthorizeRequest> {

    private String providerData;
    private String paymentId;

    // Optional parameters -- deprecated as others payment request attributes (reusing existing payment by payment_id)
    private String saleReconciliationIdentifier;
    private String saleInvoiceNumber;
    private String salesTax;

    public CardWalletAuthorizeRequest(String providerData, String paymentId, String terminal, String shopOrderId, Amount amount) {
        super(shopOrderId, terminal, amount);
        this.providerData = providerData;
        this.paymentId = paymentId;
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

    public String getSaleReconciliationIdentifier() {
        return saleReconciliationIdentifier;
    }

    public CardWalletAuthorizeRequest setSaleReconciliationIdentifier(String saleReconciliationIdentifier) {
        this.saleReconciliationIdentifier = saleReconciliationIdentifier;
        return this;
    }

    public String getSaleInvoiceNumber() {
        return saleInvoiceNumber;
    }

    public CardWalletAuthorizeRequest setSaleInvoiceNumber(String saleInvoiceNumber) {
        this.saleInvoiceNumber = saleInvoiceNumber;
        return this;
    }

    public String getSalesTax() {
        return salesTax;
    }

    public CardWalletAuthorizeRequest setSalesTax(String salesTax) {
        this.salesTax = salesTax;
        return this;
    }

}
