package com.pensio.api.request;

import com.pensio.Amount;
import com.pensio.api.request.provider.ApplePayRequestData;

public class CardWalletSessionRequest extends PaymentRequest<CardWalletSessionRequest> {

    private ApplePayRequestData applePayRequestData;

    public CardWalletSessionRequest(String terminal, String shopOrderId, Amount amount) {
        super(shopOrderId, terminal, amount);
    }

    public ApplePayRequestData getApplePayRequestData() {
        return applePayRequestData;
    }

    public CardWalletSessionRequest setApplePayRequestData(ApplePayRequestData applePayRequestData) {
        this.applePayRequestData = applePayRequestData;
        return this;
    }

}
