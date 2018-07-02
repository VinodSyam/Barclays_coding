package com.inventory.mgmt.inventory.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.inventory.mgmt.inventory.entities.ItemOrder;


public interface ItemOrderRepository extends JpaRepository<ItemOrder, Long>{
	public List<ItemOrder> fetchItemOrdersAfterPreviousReport(@Param("reportDate") Date reportDate,@Param("today") Date today);
}
