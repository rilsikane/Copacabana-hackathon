package com.hkt.roomAttach.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hkt.roomAttach.dto.AttachDto;
import com.hkt.roomAttach.service.RoomAttachService;

@RestController
public class RoomAttachController {
	
	@Autowired
	RoomAttachService roomAttachService;

	@ResponseBody
	@RequestMapping(value = "/searchRoomAttach.service", method = { RequestMethod.POST })
	public List<AttachDto> searchRoomAttach(@RequestHeader HttpHeaders headers, @RequestBody AttachDto criteria){
		return roomAttachService.searchRoomAttach(criteria);
	}
}
