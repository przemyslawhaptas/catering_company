package com.marshaller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonFactory {
	
	private GsonBuilder builder;
	
	
	public GsonFactory() {
		this.builder = new GsonBuilder();
	}
	
	public Gson build() {
		builder.setPrettyPrinting();
		builder.serializeNulls();
		builder.excludeFieldsWithoutExposeAnnotation();
		
		return builder.create();
	}
}