package com.pensio.api.request;

public class Verify3dRequest
{
	protected String transactionId;
	protected String paRes;
	protected String md;

	public Verify3dRequest(String transactionId, String paRes, String md) {
		this.transactionId = transactionId;
		this.paRes = paRes;
		this.md = md;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public Verify3dRequest setTransactionId(String transactionId) {
		this.transactionId = transactionId;
		return this;
	}

	public String getPaRes() {
		return paRes;
	}

	public Verify3dRequest setPaRes(String paRes) {
		this.paRes = paRes;
		return this;
	}

	public String getMd() {
		return md;
	}

	public Verify3dRequest setMd(String md) {
		this.md = md;
		return this;
	}

}
