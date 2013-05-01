package request;

import java.util.ArrayList;
import java.util.List;

public class MultiPaymentRequestChild {
	
	//Mandatory fields
	private double amount;

	//Optional fields
	private AuthType authType;
	private String terminal;
	private String shopOrderId;
	private String language;
	private PaymentInfos paymentInfos;
	private String shippingMethod;
	private String saleReconciliationIdentifier;
	private List<OrderLine> orderLines; 
	
	public MultiPaymentRequestChild(double d)
	{
		this.amount = d;
		paymentInfos = new PaymentInfos();
		orderLines = new ArrayList<OrderLine>();
	}
	
	public PaymentInfos getPaymentInfos()
	{
		return paymentInfos;
	}
	
	public MultiPaymentRequestChild addPaymentInfo(String key, String value)
	{
		paymentInfos.add(key, value);
		return this;
	}

	public double getAmount() {
		return amount;
	}

	public MultiPaymentRequestChild setAmount(double amount) {
		this.amount = amount;
		return this;
	}

	public AuthType getAuthType() {
		return authType;
	}

	public MultiPaymentRequestChild setAuthType(AuthType authType) {
		this.authType = authType;
		return this;
	}

	public String getTerminal() {
		return terminal;
	}

	public MultiPaymentRequestChild setTerminal(String terminal) {
		this.terminal = terminal;
		return this;
	}

	public String getShopOrderId() {
		return shopOrderId;
	}

	public MultiPaymentRequestChild setShopOrderId(String shopOrderId) {
		this.shopOrderId = shopOrderId;
		return this;
	}

	public String getLanguage() {
		return language;
	}

	public MultiPaymentRequestChild setLanguage(String language) {
		this.language = language;
		return this;
	}

	public String getShippingMethod() {
		return shippingMethod;
	}

	public MultiPaymentRequestChild setShippingMethod(String shippingMethod) {
		this.shippingMethod = shippingMethod;
		return this;
	}

	public String getSaleReconciliationIdentifier() {
		return saleReconciliationIdentifier;
	}

	public MultiPaymentRequestChild setSaleReconciliationIdentifier(String saleReconciliationIdentifier) {
		this.saleReconciliationIdentifier = saleReconciliationIdentifier;
		return this;
	}

	public List<OrderLine> getOrderLines() {
		return orderLines;
	}

	public MultiPaymentRequestChild setOrderLines(List<OrderLine> orderLines) {
		this.orderLines = orderLines;
		return this;
	}
}
