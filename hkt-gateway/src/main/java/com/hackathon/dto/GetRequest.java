package com.hackathon.dto;

import java.io.Serializable;

public class GetRequest implements Serializable {
	private static final long serialVersionUID = -8460853359624395408L;
	private String target;

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
}
