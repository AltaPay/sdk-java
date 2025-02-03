package com.pensio.api.request;

public class OrderLine {
	
	//Mandatory fields
	private String description;
	private String itemId;
	private double quantity;
	private double unitPrice;

	//Optional fields
	private double taxPercent;
	private double taxAmount;
	private String unitCode;
	private Double discount;
	private String goodsType;
	private String imageUrl;
	private String productUrl;
	
	public OrderLine(String description, String itemId, double quantity, double unit)
	{
		this.description = description;
		this.itemId = itemId;
		this.quantity = quantity;
		this.unitPrice = unit;
		this.taxPercent = 0;
		this.taxAmount = 0;
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
		return taxPercent;
	}


	public void setTaxPercent(double taxPercent) {
		this.taxPercent = taxPercent;
	}

	public double getTaxAmount() {
		return taxAmount;
	}


	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}


	public String getUnitCode() {
		return unitCode;
	}

	public void setUnitCode(String unitCode) {
		this.unitCode = unitCode;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProductUrl() {
		return productUrl;
	}

	public void setProductUrl(String productUrl) {
		this.productUrl = productUrl;
	}
}
