package com.hkt.roomAttach.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hkt.roomAttach.dto.AttachDto;

@Repository
public class RoomAttachDao extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initailize() {
		setDataSource(dataSource);
	}

	public List<AttachDto> searchRoomAttach(AttachDto criteria) {

		List<AttachDto> resultList = null;
		try {
			StringBuffer sql = new StringBuffer();

			if (criteria.getRoomId() != null && !"".equals(criteria.getRoomId())) {
				sql.append(" select * from room_attach ");
				sql.append(" where 1=1 ");
				if (criteria != null) {
					if (criteria.getRoomId() != null && !"".equals(criteria.getRoomId())) {
						sql.append(" and room_id = '" + criteria.getRoomId() + "' ");
					}
				}

				List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql.toString());
				if (!rows.isEmpty()) {
					resultList = new ArrayList<AttachDto>();
					for (Map row : rows) {
						AttachDto result = new AttachDto();
						result.setAttachId((String) row.get("attach_id"));
						result.setRoomId((String) row.get("room_id"));
						result.setUserNo((String) row.get("user_no"));
						result.setAttachPath((String) row.get("attach_path"));
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
