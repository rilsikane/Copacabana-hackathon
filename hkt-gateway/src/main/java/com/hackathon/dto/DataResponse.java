package com.hackathon.dto;

import java.io.Serializable;

import com.hackathon.utils.GWSConstants;

public class DataResponse implements Serializable {
	private static final long serialVersionUID = 2918626621089562037L;
	private String status = GWSConstants.RESPONSE_STATUS.SUCCESS;
	private String errorMsg;
	private long reqTime;
	private Object result;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public long getReqTime() {
		return reqTime;
	}

	public void setReqTime(long reqTime) {
		this.reqTime = reqTime;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

}
