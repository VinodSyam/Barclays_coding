package com.inventory.mgmt.inventory.controllers;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.mgmt.inventory.entities.Item;
import com.inventory.mgmt.inventory.entities.ItemSellingPrice;
import com.inventory.mgmt.inventory.entities.ItemOrder;
import com.inventory.mgmt.inventory.repositories.ItemRepository;
import com.inventory.mgmt.inventory.repositories.ItemSellingPriceRepository;
import com.inventory.mgmt.inventory.repositories.ItemOrderRepository;
import com.inventory.mgmt.inventory.repositories.ReportRepository;
import com.inventory.mgmt.inventory.utilities.Report;
import com.inventory.mgmt.inventory.utilities.ReportResult;

@RestController
public class InventoryController {
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	ItemSellingPriceRepository itemSellingPriceRepository;
	
	@Autowired
	ItemOrderRepository ItemOrderRepository;
	
	@Autowired
	ReportRepository reportRepository;
	
	@PostMapping("/create/{itemName}/{itemCostPrice}/{itemSellingPrice}")
	String createItem(@PathVariable String itemName,@PathVariable BigDecimal itemCostPrice,@PathVariable BigDecimal itemSellingPrice) {
		Item item = new Item();
		item.setName(itemName);
		item.setCostPrice(itemCostPrice);
		item.setUpdatedOn(new Date());
		item.setUpdatedBy("Admin");	
		item.setQuantity(0L);
		itemRepository.save(item);
		
		ItemSellingPrice  sellingPrice = new ItemSellingPrice();
		sellingPrice.setItem(item);
		sellingPrice.setSellingPrice(itemSellingPrice);
		item.setUpdatedOn(new Date());
		item.setUpdatedBy("Admin");	
		
		return "Item has been added successfully";
	}
	
	@PostMapping("/delete/{itemName}")
	String deleteItem(@PathVariable String itemName) {
		itemRepository.delete(itemRepository.findByName(itemName));		
		return "Item has been deleted successfully";
	}
	
	@PostMapping("/updateBuy/{itemName}/{quantity}")
	String updateBuyQuantityItem(@PathVariable String itemName,Long quantity) {
		Item item = itemRepository.findByName(itemName);	
		item.setQuantity(item.getQuantity()+quantity);
		item.setUpdatedOn(new Date());
		item.setUpdatedBy("Admin");
		itemRepository.flush();
		return "Item has been updated successfully";
	}
	
	@PostMapping("/updateSell/{itemName}/{quantity}")
	String updateSellQuantityItem(@PathVariable String itemName,Long quantity) {
		Item item = itemRepository.findByName(itemName);	
		//TODO:Exception
		item.setQuantity(item.getQuantity()-quantity);
		item.setUpdatedOn(new Date());
		item.setUpdatedBy("Admin");
		itemRepository.flush();
		
		ItemOrder ItemOrder=new ItemOrder();
		ItemOrder.setItem(item);
		ItemOrder.setQuantitySold(quantity);
		ItemOrder.setUpdatedOn(new Date());
		ItemOrder.setUpdatedBy("Admin");
		ItemOrder.setSellingPrice(itemSellingPriceRepository.fetchSellingPrice(itemName).getSellingPrice());
		ItemOrderRepository.save(ItemOrder);
		
		return "Item has been updated successfully";
	}
	
	@PostMapping("/updateSellPrice/{itemName}/{sellingPrice}")
	String updateSellingPriceItem(@PathVariable String itemName,BigDecimal sellingPrice) {
		Item item = itemRepository.findByName(itemName);	
		
		ItemSellingPrice itemSellingPrice = new ItemSellingPrice();
		itemSellingPrice.setItem(item);
		itemSellingPrice.setSellingPrice(sellingPrice);
		itemSellingPrice.setUpdatedOn(new Date());
		itemSellingPrice.setUpdatedBy("Admin");
		
		return "Item has been updated successfully";
	}
	
	@GetMapping("/report")
	public Report generateReport() {
		Report reportObj = new Report();
		List<Item> itemList = itemRepository.findAll();
		boolean flag =true;
		for(Item item:itemList) {
			flag =true;
			for(ItemSellingPrice sp : item.getSellingPrice()) {
				ReportResult result= new ReportResult();
				result.setItemName(item.getName());
				result.setBoughtAt(item.getCostPrice());
				result.setSoldAt(sp.getSellingPrice());
				result.setValue(item.getCostPrice().multiply(new BigDecimal(item.getQuantity())));
				if(flag) {
					reportObj.setTotalValue(reportObj.getTotalValue().add(result.getValue()));	
					flag =false;
				}
				reportObj.getItemList().add(result);
			}			
		}
		com.inventory.mgmt.inventory.entities.Report  report = reportRepository.recentlyInsertedReport();
		List<ItemOrder> ItemOrderList = ItemOrderRepository.fetchItemOrdersAfterPreviousReport(report.getUpdatedOn(),new Date());
		BigDecimal totalSellingPrice = new BigDecimal(0);
		BigDecimal totalCostPrice = new BigDecimal(0);
		
		for(ItemOrder ItemOrder:ItemOrderList) {
			Item item = ItemOrder.getItem();
			BigDecimal cp = item.getCostPrice().multiply(new BigDecimal(ItemOrder.getQuantitySold()));
			totalCostPrice = totalCostPrice.add(cp);
			
			BigDecimal sp = ItemOrder.getSellingPrice().multiply(new BigDecimal(ItemOrder.getQuantitySold()));
			totalCostPrice = totalCostPrice.add(sp);
		}
		reportObj.setProfit(totalSellingPrice.subtract(totalCostPrice));
		report.setUpdatedOn(new Date());
		report.setUpdatedBy("Admin");
		report.setProfit(reportObj.getProfit());
		report.setTotalValue(reportObj.getTotalValue());
		return reportObj;
	}
}
