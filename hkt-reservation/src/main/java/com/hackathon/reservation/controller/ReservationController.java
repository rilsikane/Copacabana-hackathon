package com.hackathon.reservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hackathon.reservation.dto.ReservationDto;
import com.hackathon.reservation.service.ReservationService;

@RestController
public class ReservationController {

	@Autowired
	ReservationService reservationService;

	@ResponseBody
	@RequestMapping(value = "/reserveRoom.service", method = { RequestMethod.POST })
	public void reserveRoom(@RequestHeader HttpHeaders headers, @RequestBody ReservationDto criteria) {
		// criteria = new ReservationDto();
		// criteria.setReserveId("1");
		// criteria.setRoomId("1");
		// criteria.setUserNo("1");
		// criteria.setApproveStatus("N");
		try {
			if (criteria != null) {
				reservationService.reservRoom(criteria);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/getReserveTrans.service", method = { RequestMethod.POST })
	public List<ReservationDto> getReserveTrans(@RequestHeader HttpHeaders headers, @RequestBody ReservationDto criteria) {
		try {
			if (criteria != null) {
				return reservationService.getReserveTrans(criteria);
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			return null;
		}
	}

}
