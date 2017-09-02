package com.hkt.report.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkt.report.dao.MonthlyIncomeDao;
import com.hkt.report.dto.MonthlyReportDto;

@Service
public class MonthlyReportService {
	
	@Autowired
	MonthlyIncomeDao monthlyIncomeDao;
	public List<MonthlyReportDto> getMonthlyReport(MonthlyReportDto criteria){
		return monthlyIncomeDao.getMonthlyReport(criteria);
	}
}
