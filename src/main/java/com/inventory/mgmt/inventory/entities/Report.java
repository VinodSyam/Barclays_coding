package com.inventory.mgmt.inventory.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "Report.recentlyInsertedReport",
  query = "SELECT r FROM Report r WHERE r.id = (select max(id) from Report)"
)
public class Report {

	@Id
	private Long id;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_on")
	private Date updatedOn;
	
	@Column(name="profit")
	private BigDecimal profit;
	
	@Column(name="total_value")
	private BigDecimal totalValue;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public BigDecimal getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(BigDecimal totalValue) {
		this.totalValue = totalValue;
	}
	
}
