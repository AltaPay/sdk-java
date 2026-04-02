package com.pensio.api.request.provider;

public class ApplePayRequestData {

    private String validationUrl;
    private String domain;
    private ApplePaySource source;

    public String getValidationUrl() {
        return validationUrl;
    }

    public void setValidationUrl(String validationUrl) {
        this.validationUrl = validationUrl;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public ApplePaySource getSource() {
        return source;
    }

    public void setSource(ApplePaySource source) {
        this.source = source;
    }
}
