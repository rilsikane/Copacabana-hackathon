package com.hkt.report.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hkt.report.dto.MonthlyReportDto;

@Repository
public class MonthlyIncomeDao extends JdbcDaoSupport{
	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initailize() {
		setDataSource(dataSource);
	}
	
	public List<MonthlyReportDto> getMonthlyReport(MonthlyReportDto criteria) {
		List<MonthlyReportDto> resultList = null;
		try {
			StringBuffer sql = new StringBuffer();

			sql.append(" select * from monthly_income ");
			sql.append(" where 1=1 ");
			
//			if (criteria != null) {
//				if (criteria.getPriceRange() != null && !"".equals(criteria.getPriceRange())) {
//					if ("1".equals(criteria.getPriceRange())) {
//						sql.append(" and price between 0 and 1000 ");
//					} else if ("2".equals(criteria.getPriceRange())) {
//						sql.append(" and price between 1001 and 3000 ");
//					} else if ("3".equals(criteria.getPriceRange())) {
//						sql.append(" and price between 3001 and 6000 ");
//					} else if ("4".equals(criteria.getPriceRange())) {
//						sql.append(" and price between 6001 and 10000 ");
//					} else if ("5".equals(criteria.getPriceRange())) {
//						sql.append(" and price > 10000 ");
//					}
//				}
//			}

			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql.toString());
			if (!rows.isEmpty()) {
				resultList = new ArrayList<MonthlyReportDto>();
				for (Map row : rows) {
					MonthlyReportDto result = new MonthlyReportDto();
					result.setId((Integer) row.get("id"));
					result.setMonth((String) row.get("month"));
					result.setYear((String) row.get("year"));
					result.setIncome((BigDecimal) row.get("income"));
					resultList.add(result);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return resultList;
	}
}
