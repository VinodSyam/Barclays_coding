package com.inventory.mgmt.inventory.entities;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Item {
	
	@Id
	private Long id;

	
    @NotNull
    @Column(name="name",unique=true)
	private String name;
	
	@Column(name="cost_price")
	private BigDecimal costPrice;
	
	@NotNull
	@Column(name="quantity")
	private Long quantity;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_on")
	private Date updatedOn;
	
	@Column(name="f_deleted")
	private boolean deleted;
		
	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "item")
    private Set<ItemSellingPrice> sellingPrice = new HashSet<>();

	@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "item")
    private Set<ItemOrder> ItemOrders = new HashSet<>();

	public Item() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<ItemSellingPrice> getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Set<ItemSellingPrice> sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public Set<ItemOrder> getItemOrders() {
		return ItemOrders;
	}

	public void setItemOrders(Set<ItemOrder> ItemOrders) {
		this.ItemOrders = ItemOrders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
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

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}
}
