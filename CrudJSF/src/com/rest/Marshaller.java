package com.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Marshaller {
	
	private Gson gson;
	
	public Marshaller() {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		builder.serializeNulls();
		builder.excludeFieldsWithoutExposeAnnotation();
		gson = builder.create();
	}
	
	public String toJSONString(Object object) {
		
		return gson.toJson(object);
	}
}