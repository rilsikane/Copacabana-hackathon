package com.hackathon.room.Dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class RoomDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3907617130833922350L;
	private String roomId;
	private String roomName;
	private String address;
	private String roomSize;
	private String price;
	private String tel;
	private String deposit; //ค่ามัดจำ
	private String roomAvail; //Y=ว่าง N=ไม่ว่าง
	private String priceRange; 	//0-1000 = 1
								//1001-6000 = 2
								//6001-10000 = 3
								//มากกว่า 10000 = 4
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRoomSize() {
		return roomSize;
	}
	public void setRoomSize(String roomSize) {
		this.roomSize = roomSize;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getRoomAvail() {
		return roomAvail;
	}
	public void setRoomAvail(String roomAvail) {
		this.roomAvail = roomAvail;
	}
	public String getPriceRange() {
		return priceRange;
	}
	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}
	
}