package com.pensio.api;

import java.util.Date;

import com.pensio.Amount;

public class FundingRecord
{
	private String id;   
	private String paymentId;
	private String orderId;
	private String terminal;
	private String shop;
	private String reconciliationIdentifier;
	private String recordType;
	private Amount paymentAmount;
	private double exchangeRate;
	private Amount fundingAmount;
	private Amount fixedFeeAmount;
	private Amount fixedFeeVatAmount;
	private Amount rateBasedFeeAmount;
	private Amount rateBasedFeeVatAmount;
	private Date fundingDate;
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getPaymentId()
	{
		return paymentId;
	}
	
	public void setPaymentId(String paymentId)
	{
		this.paymentId = paymentId;
	}
	
	public String getReconciliationIdentifier()
	{
		return reconciliationIdentifier;
	}
	
	public void setReconciliationIdentifier(String reconciliationIdentifier)
	{
		this.reconciliationIdentifier = reconciliationIdentifier;
	}
	
	public String getRecordType()
	{
		return recordType;
	}
	
	public void setRecordType(String recordType)
	{
		this.recordType = recordType;
	}
	
	public Amount getPaymentAmount()
	{
		return paymentAmount;
	}
	
	public void setPaymentAmount(Amount paymentAmount)
	{
		this.paymentAmount = paymentAmount;
	}
	
	public Amount getFundingAmount()
	{
		return fundingAmount;
	}
	
	public void setFundingAmount(Amount fundingAmount)
	{
		this.fundingAmount = fundingAmount;
	}
	
	public Amount getFixedFeeAmount()
	{
		return fixedFeeAmount;
	}
	
	public void setFixedFeeAmount(Amount fixedFeeAmount)
	{
		this.fixedFeeAmount = fixedFeeAmount;
	}
	
	public Amount getFixedFeeVatAmount()
	{
		return fixedFeeVatAmount;
	}
	
	public void setFixedFeeVatAmount(Amount fixedFeeVatAmount)
	{
		this.fixedFeeVatAmount = fixedFeeVatAmount;
	}
	
	public Amount getRateBasedFeeAmount()
	{
		return rateBasedFeeAmount;
	}
	
	public void setRateBasedFeeAmount(Amount rateBasedFeeAmount)
	{
		this.rateBasedFeeAmount = rateBasedFeeAmount;
	}
	
	public Amount getRateBasedFeeVatAmount()
	{
		return rateBasedFeeVatAmount;
	}
	
	public void setRateBasedFeeVatAmount(Amount rateBasedFeeVatAmount)
	{
		this.rateBasedFeeVatAmount = rateBasedFeeVatAmount;
	}
	
	public Date getFundingDate()
	{
		return fundingDate;
	}
	
	public void setFundingDate(Date fundingDate)
	{
		this.fundingDate = fundingDate;
	}

	public String getOrderId()
	{
		return orderId;
	}

	public void setOrderId(String orderId)
	{
		this.orderId = orderId;
	}

	public String getTerminal()
	{
		return terminal;
	}

	public void setTerminal(String terminal)
	{
		this.terminal = terminal;
	}

	public String getShop()
	{
		return shop;
	}

	public void setShop(String shop)
	{
		this.shop = shop;
	}

	public double getExchangeRate()
	{
		return exchangeRate;
	}

	public void setExchangeRate(double exchangeRate)
	{
		this.exchangeRate = exchangeRate;
	}

}
