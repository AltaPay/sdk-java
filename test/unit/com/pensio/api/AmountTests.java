package com.pensio.api;

import com.pensio.Amount;
import com.pensio.AmountConversionException;
import com.pensio.Currency;
import org.junit.Assert;
import org.junit.Test;

public class AmountTests
{

	@Test
	public void testGetAmount_amountIsNegative_getsAmount() throws AmountConversionException
	{
		Amount result = Amount.get("-123.123", Currency.DKK);
		Assert.assertEquals(-123123, result.getAmount());
	}

	@Test
	public void testGetAmountDouble_amountIsTrailingZeroAfterComma_getsAmount() throws AmountConversionException
	{
		Amount result = Amount.get("-1.07", Currency.DKK);
		Assert.assertEquals(Double.valueOf(-1.07), result.getAmountDouble());
	}

	@Test
	public void testGetAmountDouble_amountIsNegativeZero_getsAmount() throws AmountConversionException
	{
		Amount result = Amount.get("-0", Currency.DKK);
		Assert.assertEquals(Double.valueOf(0), result.getAmountDouble());
	}

	@Test
	public void testGetAmountDouble_amountIsZero_getsAmount() throws AmountConversionException
	{
		Amount result = Amount.get("0", Currency.DKK);

		Assert.assertEquals(Double.valueOf(0), result.getAmountDouble());
	}

	//todo https://altapay.atlassian.net/browse/CORE-972 implement rounding
	@Test
	public void testGetAmountDouble_amountIsNumber_getsAmountWithoutRounding() throws AmountConversionException
	{
		Amount result = Amount.get("-17.049", Currency.DKK);
		Assert.assertEquals(Double.valueOf(-17.04), result.getAmountDouble());
	}

	@Test
	public void testGetAmountDouble_amountIsTrailingZeroes_getsAmount() throws AmountConversionException
	{
		Amount result = Amount.get("-17.00000", Currency.DKK);
		Assert.assertEquals(Double.valueOf(-17.00000), result.getAmountDouble());
	}

	@Test
	public void testGetAmountDouble_amountHasMoreDecimalsThanCurrency_getsTruncatedAmount() throws AmountConversionException
	{
		Amount result = Amount.get("-17.001", Currency.DKK);
		Assert.assertEquals(Double.valueOf(-17.00), result.getAmountDouble());
	}

	@Test
	public void testGetAmount_amountIsPositive_getsAmount() throws AmountConversionException
	{
		Amount result = Amount.get("123.123", Currency.DKK);
		Assert.assertEquals(123123, result.getAmount());
	}

	@Test
	public void testGetAmountString_amountIsNegative_getsString() throws AmountConversionException
	{
		String result = Amount.get("-9.35", Currency.DKK).getAmountString();
		Assert.assertEquals("-9.35", result);
	}

	@Test
	public void testGetAmountString_amountIsPositive_getsString() throws AmountConversionException
	{
		String result = Amount.get("123.12", Currency.DKK).getAmountString();
		Assert.assertEquals("123.12", result);
	}
}
