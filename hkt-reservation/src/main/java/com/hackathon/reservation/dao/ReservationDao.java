package com.hackathon.reservation.dao;

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

	public void reservationRoom(ReservationDto dto) {
		List<ReservationDto> resultList = null;
		try {
			String sql = " INSERT INTO reservation(room_id, user_no, approve_status) "
					+ " VALUES (?, ?, ?) ";


			getJdbcTemplate().update(sql, dto.getRoomId(),
					dto.getUserNo(), dto.getApproveStatus());

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}
