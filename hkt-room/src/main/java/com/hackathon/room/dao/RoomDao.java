package com.hackathon.room.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hackathon.room.Dto.RoomDto;

@Repository
public class RoomDao extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initailize() {
		setDataSource(dataSource);
	}

	public List<RoomDto> searchRoom(RoomDto criteria) {
		List<RoomDto> resultList = null;
		try {
			StringBuffer sql = new StringBuffer();

			sql.append(" select * from room ");
			sql.append(" where 1=1 ");
			sql.append(" and roomavail = 'Y' ");
			if (criteria != null) {
				if (criteria.getPriceRange() != null && !"".equals(criteria.getPriceRange())) {
					if ("1".equals(criteria.getPriceRange())) {
						sql.append(" and to_number(price,'999G999') between 0 and 1000 ");
					} else if ("2".equals(criteria.getPriceRange())) {
						sql.append(" and to_number(price,'999G999') between 1001 and 3000 ");
					} else if ("3".equals(criteria.getPriceRange())) {
						sql.append(" and to_number(price,'999G999') between 3001 and 6000 ");
					} else if ("4".equals(criteria.getPriceRange())) {
						sql.append(" to_number(price,'999G999') between 6001 and 10000 ");
					} else if ("5".equals(criteria.getPriceRange())) {
						sql.append(" to_number(price,'999G999') > 10000 ");
					}
				}
			}

			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql.toString());
			if (!rows.isEmpty()) {
				resultList = new ArrayList<RoomDto>();
				for (Map row : rows) {
					RoomDto result = new RoomDto();
					result.setRoomId((String) row.get("room_id"));
					result.setRoomName((String) row.get("room_name"));
					result.setAddress((String) row.get("address"));
					result.setRoomSize((String) row.get("room_size"));
					result.setPrice((String) row.get("price"));
					result.setTel((String) row.get("tel"));
					result.setDeposit((String) row.get("deposit"));
					result.setRoomAvail((String) row.get("roomavail"));
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
