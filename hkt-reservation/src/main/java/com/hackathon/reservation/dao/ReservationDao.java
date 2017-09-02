package com.hackathon.reservation.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hackathon.reservation.dto.ReservationDto;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

@Repository
public class ReservationDao extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initailize() {
		setDataSource(dataSource);
	}

	public void reservationRoom(ReservationDto dto) throws Exception {
		List<ReservationDto> resultList = null;
		try {
			String sql = " INSERT INTO reservation(room_id, user_no, approve_status) " + " VALUES (?, ?, ?) ";
			getJdbcTemplate().update(sql, dto.getRoomId(), dto.getUserNo(), dto.getApproveStatus());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public void updateRoomStatus(String roomId) throws Exception {
		try {
			String sql = " update room set roomavail ='N' where room_id='" + roomId + "'";
			getJdbcTemplate().update(sql);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

	public List<ReservationDto> getReserveTrans(ReservationDto criteria) {
		List<ReservationDto> resultList = null;
		try {
			if (criteria.getUserNo() != null && !"".equals(criteria.getUserNo())) {
				StringBuffer sql = new StringBuffer();

				sql.append(" select * from reservation ");
				sql.append(" where 1=1 ");
				if (criteria != null) {
					if (criteria.getUserNo() != null && !"".equals(criteria.getUserNo())) {
						sql.append(" and user_no = '" + criteria.getUserNo() + "'");
					}
				}

				List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql.toString());
				if (!rows.isEmpty()) {
					resultList = new ArrayList<ReservationDto>();
					for (Map row : rows) {
						ReservationDto result = new ReservationDto();
						result.setReserveId((Integer) row.get("reserve_id"));
						result.setRoomId((String) row.get("room_id"));
						result.setUserNo((String) row.get("user_no"));
						result.setApproveStatus((String) row.get("approve_status"));
						resultList.add(result);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return resultList;
	}

}
