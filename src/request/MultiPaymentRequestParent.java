package request;

import java.util.ArrayList;
import java.util.List;

import com.pensio.Currency;

public class MultiPaymentRequestParent
{
	//Mandatory fields
	private String shopOrderId;
	private String terminal;
	private Currency currency;
	private List<MultiPaymentRequestChild> multiPaymentRequestChildren;
	
	//Optional fields
	private String language;
	private String creditCardToken;
	private String cookie;
	private AuthType authType;
	private PaymentRequestConfig config;
	private PaymentInfos paymentInfos;
	private CustomerInfo customerInfo;
	
	public MultiPaymentRequestParent(String shopOrderId, String terminal, Currency currency, List<MultiPaymentRequestChild> multiPaymentRequestChildren)
	{
		this(shopOrderId, terminal, currency);
		this.multiPaymentRequestChildren = multiPaymentRequestChildren;
	}

	public MultiPaymentRequestParent(String shopOrderId, String terminal, Currency currency)
	{
		this.shopOrderId = shopOrderId;
		this.terminal = terminal;
		this.currency = currency;
		paymentInfos = new PaymentInfos();
		multiPaymentRequestChildren = new ArrayList<MultiPaymentRequestChild>();
	}

	public String getShopOrderId() {
		return shopOrderId;
	}

	public MultiPaymentRequestParent setShopOrderId(String shopOrderId) {
		this.shopOrderId = shopOrderId;
		return this;
	}

	public String getTerminal() {
		return terminal;
	}

	public MultiPaymentRequestParent setTerminal(String terminal) {
		this.terminal = terminal;
		return this;
	}

	public Currency getCurrency() {
		return currency;
	}

	public MultiPaymentRequestParent setCurrency(Currency currency) {
		this.currency = currency;
		return this;
	}

	public List<MultiPaymentRequestChild> getMultiPaymentRequestChildren() {
		return multiPaymentRequestChildren;
	}

	public MultiPaymentRequestParent addMultiPaymentRequestChild(
			MultiPaymentRequestChild multiPaymentRequestChild) {
		this.multiPaymentRequestChildren.add(multiPaymentRequestChild);
		return this;
	}
	
	public String getLanguage()
	{
		return language;
	}

	public MultiPaymentRequestParent setLanguage(String language)
	{
		this.language = language;
		return this;
	}

	public String getCreditCardToken() {
		return creditCardToken;
	}

	public MultiPaymentRequestParent setCreditCardToken(String creditCardToken) {
		this.creditCardToken = creditCardToken;
		return this;
	}

	public String getCookie() {
		return cookie;
	}

	public MultiPaymentRequestParent setCookie(String cookie) {
		this.cookie = cookie;
		return this;
	}

	public AuthType getAuthType() {
		return authType;
	}

	public MultiPaymentRequestParent setAuthType(AuthType authType) {
		this.authType = authType;
		return this;
	}
	
	public PaymentRequestConfig getConfig()
	{
		return config;
	}

	public MultiPaymentRequestParent setConfig(PaymentRequestConfig config)
	{
		this.config = config;
		return this;
	}
	
	public PaymentInfos getPaymentInfos()
	{
		return paymentInfos;
	}
	
	public MultiPaymentRequestParent addPaymentInfo(String key, String value)
	{
		paymentInfos.add(key, value);
		return this;
	}
	
	public CustomerInfo getCustomerInfo()
	{
		return customerInfo;
	}

	public MultiPaymentRequestParent setCustomerInfo(CustomerInfo customerInfo)
	{
		this.customerInfo = customerInfo;
		return this;
	}
}
