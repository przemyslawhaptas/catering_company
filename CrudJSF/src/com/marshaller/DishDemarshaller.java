package com.marshaller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.model.Dish;

public class DishDemarshaller {
	
	private Gson gson;
	private JsonParser parser;
	
	public DishDemarshaller(Gson gson) {
		this.gson = gson;
		this.parser = new JsonParser();
	}
	
	public Object[] fromJson(String dishJSON) {
		JsonArray array = parser.parse(dishJSON).getAsJsonArray();
		
		JsonElement dishJson = array.get(0);
		JsonElement dishCategoryIdJson = array.get(1); 
		
		Dish dish = gson.fromJson(dishJson, Dish.class);
		int dishCategoryId = gson.fromJson(dishCategoryIdJson, Integer.class);	
		
		return new Object[]{ dish, dishCategoryId };
	}
}