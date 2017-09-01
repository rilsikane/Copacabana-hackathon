package com.hackathon.room.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.room.Dto.RoomDto;
import com.hackathon.room.service.RoomService;

@RestController
public class RoomController {
	
	@Autowired
	RoomService roomService;
	
	@ResponseBody
	@RequestMapping(value = "/searchAvailRoom.service", method = { RequestMethod.POST })
	public List<RoomDto> searchAvailRoom(@RequestHeader HttpHeaders headers, @RequestBody RoomDto criteria){
		return roomService.searchRoom(criteria);
	}
}
