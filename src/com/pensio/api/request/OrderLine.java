package com.pensio.api.request;

public class OrderLine {
	public enum TaxType
	{
		AMOUNT("taxAmount")
		, PERCENT("taxPercent"); 
		
		private final String name;

		private TaxType(String name)
		{ 
			this.name = name; 
		}

		public String getName() 
		{
			return name;
		}
	};
	
	//Mandatory fields
	private String description;
	private String itemId;
	private double quantity;
	private double unitPrice;

	//Optional fields
	private TaxType taxType;
	private double taxValue;
	private String unitCode;
	private double discount;
	private String goodsType;
	private String imageUrl;
	
	public OrderLine(String description, String itemId, double quantity, double unit)
	{
		this.description = description;
		this.itemId = itemId;
		this.quantity = quantity;
		this.unitPrice = unit;
		taxType = TaxType.AMOUNT;
	}

	public TaxType getTaxType() 
	{
		return taxType;
	}
	
	public double getTaxValue()
	{
		return taxValue;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public double getTaxPercent() {
		return taxValue;
	}
	
	/**
	 * You can only specify one tax value so setting this as taxPercent will overwrite any previously set taxAmount
	 * @param taxPercent
	 */
	public void setTaxPercent(double taxPercent) {
		taxValue = taxPercent;
		taxType = TaxType.PERCENT;
	}

	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public double getTaxAmount() {
		return taxValue;
	}

	/**
	 * You can only specify one tax value so setting this as taxAmount will overwrite any previously set taxPercent
	 * @param taxAmount
	 */
	public void setTaxAmount(double taxAmount) {
			taxValue = taxAmount;
			taxType = TaxType.AMOUNT;
	}
	
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
}
