package com.inventory.mgmt.inventory.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.mgmt.inventory.entities.Item;

@Transactional
public interface ItemRepository extends JpaRepository<Item, Long>{
	public Item findByName(String name);
	public List<Item> findAllByOrderByName();
}
