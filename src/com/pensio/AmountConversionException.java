package com.pensio;

public class AmountConversionException extends Exception {
    private static final long serialVersionUID = -8037241738826605867L;

    public AmountConversionException(String error)
    {
        super(error);
    }
}
