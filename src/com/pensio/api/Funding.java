package com.pensio.api;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.pensio.Amount;


public class Funding
{
	private String id;
	private String contractIdentifier;
	private String Acquirer;
	private Date fundingDate;
	private Amount fundingAmount;
	private Date createdAt;
	private List<FundingRecord> records;

	public Funding()
	{
		records = new ArrayList<FundingRecord>();
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getContractIdentifier()
	{
		return contractIdentifier;
	}
	
	public void setContractIdentifier(String contractIdentifier)
	{
		this.contractIdentifier = contractIdentifier;
	}
	
	public String getAcquirer()
	{
		return Acquirer;
	}
	
	public void setAcquirer(String acquirer)
	{
		Acquirer = acquirer;
	}
	
	public Date getFundingDate()
	{
		return fundingDate;
	}
	
	public void setFundingDate(Date fundingDate)
	{
		this.fundingDate = fundingDate;
	}
	
	public Amount getFundingAmount()
	{
		return fundingAmount;
	}
	
	public void setFundingAmount(Amount fundingAmount)
	{
		this.fundingAmount = fundingAmount;
	}
	
	public Date getCreatedAt()
	{
		return createdAt;
	}
	
	public void setCreatedAt(Date createdAt)
	{
		this.createdAt = createdAt;
	}

	public void addRecord(FundingRecord record)
	{
		records.add(record);
	}
	
	public List<FundingRecord> getRecords()
	{
		return records;
	}
}
