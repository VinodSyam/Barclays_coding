package com.inventory.mgmt.inventory.utilities;

import java.math.BigDecimal;

public class ReportResult {

	private String itemName;
	private BigDecimal boughtAt;
	private BigDecimal soldAt;
	private Long availableQuantity;
	private BigDecimal value;
	
	public ReportResult() {
		
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public BigDecimal getBoughtAt() {
		return boughtAt;
	}
	public void setBoughtAt(BigDecimal boughtAt) {
		this.boughtAt = boughtAt;
	}
	public BigDecimal getSoldAt() {
		return soldAt;
	}
	public void setSoldAt(BigDecimal soldAt) {
		this.soldAt = soldAt;
	}
	public Long getAvailableQuantity() {
		return availableQuantity;
	}
	public void setAvailableQuantity(Long availableQuantity) {
		this.availableQuantity = availableQuantity;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
}
