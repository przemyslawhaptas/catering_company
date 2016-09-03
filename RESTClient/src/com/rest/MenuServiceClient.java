package com.rest;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.String;

import javax.ws.rs.core.MediaType;

import org.jboss.resteasy.client.ClientRequest;
import org.jboss.resteasy.client.ClientResponse;

public class MenuServiceClient {
	public static void main(String[] args) {
		try {
			ClientRequest request = new ClientRequest("http://localhost:8080/CrudJSF/rest/menuService/menu");
			request.accept(MediaType.APPLICATION_JSON);
			ClientResponse<String> response = request.get(String.class);
		
			if (response.getStatus() != 200) {
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
}