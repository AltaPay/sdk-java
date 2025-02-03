package com.pensio.api.request;

public class Verify3dRequest
{
	protected String transactionId;
	protected String paRes;
	protected String threeDSecureData;

	public Verify3dRequest(String transactionId) {
		this.transactionId = transactionId;
	}

	@Deprecated
	public Verify3dRequest(String transactionId, String paRes) {
		this(transactionId, paRes, null);
	}

	public Verify3dRequest(String transactionId, String paRes, String threeDSecureData) {
		this.transactionId = transactionId;
		this.paRes = paRes;
		this.threeDSecureData = threeDSecureData;
	}

	public String getTransactionId() {
		return transactionId;
	}

	@Deprecated
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

	public String getThreeDSecureData() {
		return threeDSecureData;
	}

	public Verify3dRequest setThreeDSecureData(String threeDSecureData) {
		this.threeDSecureData = threeDSecureData;
		return this;
	}
}
