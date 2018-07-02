package com.inventory.mgmt.inventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.mgmt.inventory.entities.Report;


public interface ReportRepository extends JpaRepository<Report, Long>{

	public Report recentlyInsertedReport();
}
