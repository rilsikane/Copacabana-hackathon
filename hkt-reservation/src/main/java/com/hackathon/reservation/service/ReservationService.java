package com.hackathon.reservation.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hackathon.reservation.dao.ReservationDao;
import com.hackathon.reservation.dto.ReservationDto;


@Service
public class ReservationService {

	@Autowired
	private ReservationDao reservationDao;
	
	public void reservRoom(ReservationDto criteria){
		reservationDao.reservationRoom(criteria);
	}
}
