package com.pensio.api.request;

public class FundingListRequest
{
	private int page = 0;

	public FundingListRequest(int page)
	{
		this.page = page;
	}
	
	public FundingListRequest()
	{
		this.page = 0;
	}

	public int getPage()
	{
		return this.page;
	}
}
