package com.inventory.mgmt.inventory.utilities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Report {

	private List<ReportResult> itemList;
	private BigDecimal totalValue;
	private BigDecimal profit;
	
	public Report() {
		itemList = new ArrayList();
	}
	
	public List<ReportResult> getItemList() {
		return itemList;
	}
	public void setItemList(List<ReportResult> itemList) {
		this.itemList = itemList;
	}
	public BigDecimal getTotalValue() {
		return totalValue;
	}
	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
}
