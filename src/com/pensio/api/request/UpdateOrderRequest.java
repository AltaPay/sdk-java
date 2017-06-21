package com.pensio.api.request;

import com.pensio.Amount;

import java.util.List;

public class UpdateOrderRequest
{
	private String paymentId;
	private List<OrderLine> orderLines;

	public UpdateOrderRequest(String paymentId, List<OrderLine> orderLines)
	{
		if (orderLines == null) {
			throw new IllegalArgumentException("orderLines cannot be null");
		}
		else if (orderLines.size() != 2) {
			throw new IllegalArgumentException("orderLines must contain exactly two elements");
		}

		this.paymentId = paymentId;
		this.orderLines = orderLines;
	}

	public String getPaymentId()
	{
		return paymentId;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

}
