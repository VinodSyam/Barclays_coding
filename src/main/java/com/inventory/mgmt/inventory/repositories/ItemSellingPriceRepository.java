package com.inventory.mgmt.inventory.repositories;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.inventory.mgmt.inventory.entities.ItemSellingPrice;

@Transactional
public interface ItemSellingPriceRepository extends JpaRepository<ItemSellingPrice, Long>{
	public ItemSellingPrice fetchSellingPrice(@Param("itemName") String itemName);
}
