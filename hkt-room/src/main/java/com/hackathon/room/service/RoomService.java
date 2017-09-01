package com.hackathon.room.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.room.Dto.RoomDto;
import com.hackathon.room.dao.RoomDao;




@Service
public class RoomService  {
	
	@Autowired
	private RoomDao roomDao;
	
	public List<RoomDto> searchRoom(RoomDto criteria){
		return roomDao.searchRoom(criteria);
	}
}
