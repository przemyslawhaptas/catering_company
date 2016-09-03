package com.marshaller;

import com.google.gson.Gson;

public class Marshaller {
	
	private Gson gson;
	
	public Marshaller(Gson gson) {
		this.gson = gson;
	}
	
	public String toJson(Object object) {
		
		return gson.toJson(object);
	}
}