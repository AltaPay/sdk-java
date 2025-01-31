package com.pensio;

import java.math.BigDecimal;
import java.math.BigInteger;

public class Amount
{
    private final long amount;
    private final Currency currency;

    private Amount(long amount, Currency currency)
    {
        this.amount = amount;
        if(currency == null)
        {
            throw new UnknownCurrencyException();
        }
        this.currency = currency;
    }

    /**
     *
     * @param amount Amount in 3 decimal notation. An amount of 10.32 would be 10320
     * @param currency
     * @return
     */
    public static Amount get(long amount, Currency currency)
    {
        return new Amount(amount, currency);
    }

    public static Amount get(double amount, Currency currency)
    {
        return new Amount(Math.round(amount * 1000), currency);
    }

    public static Amount get(String amount, Currency currency) throws AmountConversionException
    {
        return new Amount(convertStringAmountToLongWithThreeDecimals(amount), currency);
    }

    public static Amount getFromMinorUnit(String amount, Currency currency) throws AmountConversionException
    {
        return new Amount(convertStringAmountInMinorUnitToLongWithThreeDecimals(amount, currency), currency);
    }

    public static Amount get(String amount, String currency) throws AmountConversionException
    {
        return new Amount(convertStringAmountToLongWithThreeDecimals(amount), Currency.valueOf(currency));
    }

    public static Amount get(BigDecimal amount, Currency currency)
    {
        return new Amount(Math.round(amount.doubleValue() * 1000), currency);
    }

    public long getAmount()
    {
        return amount;
    }

    public Currency getCurrency()
    {
        return currency;
    }

    public BigInteger getAmountInMinorUnit()
    {
        return BigInteger.valueOf((long) (amount / Math.pow(10, 3 - currency.getDecimals())));
    }

    public Double getAmountDouble()
    {
        return Double.valueOf(getAmountString());
    }

    public String getAmountString()
    {
        return convertLongWithThreeDecimalsToStringWithTwoDecimals(amount, currency);
    }

    private static long convertStringAmountInMinorUnitToLongWithThreeDecimals(String amount, Currency currency)
            throws AmountConversionException
    {
        return (long)((Integer.valueOf(amount) * Math.pow(10, 3 - currency.getDecimals())));
    }

    private static long convertStringAmountToLongWithThreeDecimals(String amount)
            throws AmountConversionException
	{
		if(amount == null || amount.length() == 0)
		{
			return 0;
		}
		// Only allow digits of format:
		//  000, .000, 000., 000.000 and negative
		if(!amount.matches("^-?(\\d+|\\d*\\.\\d+|\\d+\\.\\d*)$"))
		{
			throw new AmountConversionException("Invalid amount: " + amount);
		}

        boolean isNegative = false;
        if (amount.startsWith("-"))
        {
            isNegative = true;
        }
        amount = amount.replace("-", "");

        amount = amount.replaceAll("0+$", "");
        String[] amountParts = amount.split("\\.");

        if ("".equals(amountParts[0]))
        {
            amountParts[0] = "0";
        }

        long result = getLongFromStringParts(amount, amountParts);

        if(isNegative)
		{
			result = -1*result;
		}

        return result;
    }

    private static long getLongFromStringParts(String amount, String[] amountParts) throws AmountConversionException
    {
        long result;
        if (amountParts.length > 1)
        {
            if (amountParts[1].length() > 3)
            {
                throw new AmountConversionException("Too many decimals when attempting to convert " + amount + " to long");
            }
            while (amountParts[1].length() < 3)
            {
                amountParts[1] += "0";
            }
             result = Long.parseLong(amountParts[0] + amountParts[1]);
        }
        else
        {
            result = Long.parseLong(amountParts[0] + "000");
        }
        return result;
    }

    private static String convertLongWithThreeDecimalsToStringWithTwoDecimals(long amount, Currency currency)
    {
        String negativePrefix = "";
        if(amount < 0)
        {
            amount = amount*-1;
            negativePrefix = "-";
        }

        long digitsPow = (long) Math.pow(10,3-currency.getDecimals());
        amount = amount / digitsPow;

        String digitPart = "" + (amount / (long) Math.pow(10,currency.getDecimals()));
        String decimalPart = "" + (amount % (long) Math.pow(10,currency.getDecimals()));

        while (decimalPart.length() < 3)
        {
            decimalPart = "0" + decimalPart;
        }

        return negativePrefix + digitPart + (currency.getDecimals() > 0 ? "."+(decimalPart).substring(3-currency.getDecimals()) : "");
    }

    @Override
    public String toString()
    {
        return getAmountString()+ " "+getCurrency();
    }

    @Override
    public boolean equals(Object obj)
    {
        if(!(obj instanceof Amount))
        {
            return false;
        }
        Amount objAmount = (Amount)obj;

        return objAmount.getCurrency() == getCurrency() && objAmount.getAmount() == getAmount();
    }

    public Amount to(Currency to, double rate)
    {
        return new Amount(Math.round(rate * amount), to);
    }
}
