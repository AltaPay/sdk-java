package com.pensio.api.request;

import com.pensio.Amount;

public class CardWalletAuthorizeRequest extends PaymentRequest<CardWalletAuthorizeRequest> {

    private String providerData;

    // Optional parameters
    private String saleReconciliationIdentifier;
    private String saleInvoiceNumber;
    private String salesTax;

    public CardWalletAuthorizeRequest(String providerData, String terminal, String shopOrderId, Amount amount) {
        super(shopOrderId, terminal, amount);
        this.providerData = providerData;
    }

    public String getProviderData() {
        return providerData;
    }

    public CardWalletAuthorizeRequest setProviderData(String providerData) {
        this.providerData = providerData;
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
