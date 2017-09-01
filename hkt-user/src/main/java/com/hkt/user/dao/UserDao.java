package com.hkt.user.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.hkt.user.dto.UserDto;

@Repository
public class UserDao extends JdbcDaoSupport {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	private void initailize() {
		setDataSource(dataSource);
	}

	public UserDto register(UserDto user) {
		try {

			StringBuffer sql = new StringBuffer();

			sql.append(" select * from user ");
			sql.append(" where username = " + user.getUserName());

			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql.toString());
			if (!rows.isEmpty()) {
				user.setMsg("101");// duplicate username
				return user;
			}

			String sql2 = " INSERT INTO user(username, password, first_name, last_name, gender) VALUES (?, ?, ?, ? ,?) ";
			getJdbcTemplate().update(sql2, user.getUserName(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getGender());
			user.setMsg("100");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			user.setMsg("200");
		}
		return user;
	}
		
	public UserDto login(UserDto user){
		
		try{
			StringBuffer sql = new StringBuffer();

			sql.append(" select * from user ");
			sql.append(" where username = '" + user.getUserName() + "'");

			List<Map<String, Object>> rows = getJdbcTemplate().queryForList(sql.toString());
			if (!rows.isEmpty()) {
				UserDto result = (UserDto) rows.get(0);
				if(!result.getPassword().equals(user.getPassword())){
					user.setMsg("103");//wrong password
					return user;
				}else{
					result.setMsg("100");
					return result;
				}
			}else{
				user.setMsg("102");// wrong username
				return user;
			}
		}catch (Exception e){
			e.printStackTrace();
			System.out.println(e.getMessage());
			user.setMsg("200");
			return user;
		}
	}
}
