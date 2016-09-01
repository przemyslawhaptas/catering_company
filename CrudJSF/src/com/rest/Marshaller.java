package com.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Marshaller {
	
	private Gson gson;
	
	public Marshaller() {
		GsonBuilder builder = new GsonBuilder();
		builder.setDateFormat("yyy-MM-dd'T'HH:mm:ss.SSS'Z'"); // ISO8601 / UTC
		gson = builder.create();
	}
	
	public String toJSONString(Object object) {
		
		return gson.toJson(object);
	}
}