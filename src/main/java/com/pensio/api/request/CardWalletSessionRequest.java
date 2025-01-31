package com.pensio.api.request;

public class CardWalletSessionRequest {

    private String terminal;
    private String validationUrl;
    private String domain;


    public CardWalletSessionRequest(String terminal, String validationUrl, String domain) {
        this.terminal = terminal;
        this.validationUrl = validationUrl;
        this.domain = domain;

    }

    public String getTerminal() {
        return terminal;
    }

    public CardWalletSessionRequest setTerminal(String terminal) {
        this.terminal = terminal;
        return this;
    }

    public String getValidationUrl() {
        return validationUrl;
    }

    public CardWalletSessionRequest setValidationUrl(String validationUrl) {
        this.validationUrl = validationUrl;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public CardWalletSessionRequest setDomain(String domain) {
        this.domain = domain;
        return this;
    }

}
