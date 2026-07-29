package com.pensio.api.request;

import java.util.List;

public class CreateCheckoutSessionRequest extends PaymentRequest<CreateCheckoutSessionRequest> {

    private List<String> terminals;
    private String riskManagerPolicyGroup;
    private String riskManagerPolicy;
    private String maxConversionPolicyGroup;
    private String maxConversionPolicy;

    public List<String> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<String> terminals) {
        this.terminals = terminals;
    }

    public String getRiskManagerPolicyGroup() {
        return riskManagerPolicyGroup;
    }

    public CreateCheckoutSessionRequest setRiskManagerPolicyGroup(String riskManagerPolicyGroup) {
        this.riskManagerPolicyGroup = riskManagerPolicyGroup;
        return this;
    }

    public String getRiskManagerPolicy() {
        return riskManagerPolicy;
    }

    public CreateCheckoutSessionRequest setRiskManagerPolicy(String riskManagerPolicy) {
        this.riskManagerPolicy = riskManagerPolicy;
        return this;
    }

    public String getMaxConversionPolicyGroup() {
        return maxConversionPolicyGroup;
    }

    public CreateCheckoutSessionRequest setMaxConversionPolicyGroup(String maxConversionPolicyGroup) {
        this.maxConversionPolicyGroup = maxConversionPolicyGroup;
        return this;
    }

    public String getMaxConversionPolicy() {
        return maxConversionPolicy;
    }

    public CreateCheckoutSessionRequest setMaxConversionPolicy(String maxConversionPolicy) {
        this.maxConversionPolicy = maxConversionPolicy;
        return this;
    }
}
