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
	public void testGetAmount_amountIsPositive_getsAmount() throws AmountConversionException
	{
		Amount result = Amount.get("123.123", Currency.DKK);
		Assert.assertEquals(123123, result.getAmount());
	}

	@Test
	public void testGetAmountString_amountIsNegative_getsString() throws AmountConversionException
	{
		String result = Amount.get("-123.12", Currency.DKK).getAmountString();
		Assert.assertEquals("-123.12", result);
	}

	@Test
	public void testGetAmountString_amountIsPositive_getsString() throws AmountConversionException
	{
		String result = Amount.get("123.12", Currency.DKK).getAmountString();
		Assert.assertEquals("123.12", result);
	}
}
