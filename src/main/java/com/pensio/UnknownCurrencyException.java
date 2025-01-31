package com.pensio;

public class UnknownCurrencyException extends RuntimeException
{
    public static final int ERROR_CODE = 23455386;
    public UnknownCurrencyException(int numericValue)
    {
        super("Unkown currency: " + numericValue);
    }

    public UnknownCurrencyException(String stringValue)
    {
        super("Unkown currency: " + stringValue);
    }

    public UnknownCurrencyException()
    {
        super("Unkown currency: null");
    }

    private static final long serialVersionUID = 2623049437023049860L;

}
