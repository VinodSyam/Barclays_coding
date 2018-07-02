package com.inventory.mgmt.inventory.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.validation.constraints.NotNull;

@Entity
@NamedQuery(name = "ItemOrder.fetchItemOrdersAfterPreviousReport",
	query = "SELECT o FROM ItemOrder o WHERE o.updatedOn >:reportDate and o.updatedOn <=today"
)
public class ItemOrder {

	@Id
	private Long id;
	
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
	Item item;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_on")
	private Date updatedOn;
	
	@Column(name="quantity_sold")
	Long quantitySold;
	
	@NotNull
	@Column(name="selling_price")
	private BigDecimal sellingPrice;

	
	public BigDecimal getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(BigDecimal sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public ItemOrder() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
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

	public Long getQuantitySold() {
		return quantitySold;
	}

	public void setQuantitySold(Long quantitySold) {
		this.quantitySold = quantitySold;
	}
	
}
