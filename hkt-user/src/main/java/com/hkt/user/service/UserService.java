package com.hkt.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkt.user.dao.UserDao;
import com.hkt.user.dto.UserDto;

@Service
public class UserService {
	
	@Autowired
	UserDao userDao;
	
	public UserDto register(UserDto user){
		return userDao.register(user);
	}
	
	public UserDto login(UserDto user){
		return userDao.login(user);
	}
}
