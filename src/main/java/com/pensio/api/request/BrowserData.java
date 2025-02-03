package com.pensio.api.request;

public class BrowserData {

    private String timeZone;
    private String javascriptEnabled;
    private String javaEnabled;
    private String screenWidth;
    private String screenHeight;
    private String colorDepth;
    private String forwardedIp;
    private String userAgent;
    private String accept;
    private String acceptLanguage;

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getJavascriptEnabled() {
        return javascriptEnabled;
    }

    public void setJavascriptEnabled(String javascriptEnabled) {
        this.javascriptEnabled = javascriptEnabled;
    }

    public String getJavaEnabled() {
        return javaEnabled;
    }

    public void setJavaEnabled(String javaEnabled) {
        this.javaEnabled = javaEnabled;
    }

    public String getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }

    public String getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }

    public String getColorDepth() {
        return colorDepth;
    }

    public void setColorDepth(String colorDepth) {
        this.colorDepth = colorDepth;
    }

    public String getForwardedIp() {
        return forwardedIp;
    }

    public void setForwardedIp(String forwardedIp) {
        this.forwardedIp = forwardedIp;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }
}
