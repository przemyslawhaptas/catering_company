package com.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class DishServiceClient {
	public static void main(String[] args) {
		try {
			ClientRequest request = new ClientRequest("http://localhost:8080/CrudJSF/rest/dish");
			request.accept(MediaType.APPLICATION_JSON);
			
			String dishName = "restDish";
			String dishDescription = "restDescription";
			String dishPrice = "0";
			String dishCategoryId = "2";
			
			String dishJSON = dishJSONBuilder(dishName, dishDescription, dishPrice, dishCategoryId);	

			request.body(MediaType.APPLICATION_JSON, dishJSON);
			
			ClientResponse<String> response = request.post(String.class);
		
			if (response.getStatus() != 201) {
				throw new RuntimeException("Failed : HTTP error code : " + response.getStatus());
			}
		
			BufferedReader br = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(response.getEntity().getBytes())));
		
			String output;
		
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	
	public static String dishJSONBuilder(String dishName, String dishDescription, String dishPrice, 
			String dishCategoryId) {
		
		return  
			"[" +
				"{ " + 
					"name: \"" + dishName + "\", " + 
					"description: \"" + dishDescription + "\", " + 
					"price: " + dishPrice + 
				" }, " +
				"\"" + dishCategoryId + "\"" + 
			"]";
	}
}