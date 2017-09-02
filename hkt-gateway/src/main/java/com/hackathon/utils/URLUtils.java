package com.hackathon.utils;

public class URLUtils {
	public static String getEnpointService(String target) {
		
		return getEndPoint(target);
	}
	private static String getEndPoint(String target){
		String port = "8107";
		String[] targets = target.split("\\.");
		switch (targets[0]) {
		case "room":
			port = "8107";
		break;
		case "reservation":
			port = "8207";
		break;
		case "attach":
			port = "8307";
		break;
		case "user":
			port = "8407";
		break;
//		case "attach":
//			port = "9084";
//			break;
		
		default:
			break;
		}
		
		return "http://139.59.125.200:"+port+"/"+targets[1]+"."+targets[2];
//		return "http://localhost:"+port+"/"+targets[1]+"."+targets[2];
	}
}
