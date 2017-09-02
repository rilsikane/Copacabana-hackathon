package com.hackathon.reservation.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hackathon.reservation.dao.ReservationDao;
import com.hackathon.reservation.dto.ReservationDto;

@Service
public class ReservationService {

	@Autowired
	private ReservationDao reservationDao;
	
	@Transactional(rollbackForClassName = {"Exception"})
	public void reservRoom(ReservationDto criteria) throws Exception{
		reservationDao.reservationRoom(criteria);
		reservationDao.updateRoomStatus(criteria.getRoomId());
	}
	
	public List<ReservationDto> getReserveTrans(ReservationDto criteria){
		return reservationDao.getReserveTrans(criteria);
	}
}
