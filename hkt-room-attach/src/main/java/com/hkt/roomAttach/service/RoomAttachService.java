package com.hkt.roomAttach.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hkt.roomAttach.dao.RoomAttachDao;
import com.hkt.roomAttach.dto.AttachDto;

@Service
public class RoomAttachService {
	
	@Autowired
	RoomAttachDao roomAttachDao;
	
	public List<AttachDto> searchRoomAttach(AttachDto criteria){
		return roomAttachDao.searchRoomAttach(criteria);
	}
}
