package com.hkt.report.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hkt.report.dto.MonthlyReportDto;
import com.hkt.report.service.MonthlyReportService;


@RestController
public class ReportController {
	
	@Autowired
	MonthlyReportService monthlyReportService;
	
	@ResponseBody
	@RequestMapping(value = "/getMonthlyReport.service", method = { RequestMethod.POST })
	public List<MonthlyReportDto> getMonthlyReport(@RequestHeader HttpHeaders headers, @RequestBody MonthlyReportDto criteria){
		return monthlyReportService.getMonthlyReport(criteria);
	}
}
