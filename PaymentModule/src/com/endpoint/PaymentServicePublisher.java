package com.endpoint;

import javax.xml.ws.Endpoint;

import com.ws.PaymentServiceImp;

public class PaymentServicePublisher {
	
	public static final String URL = "http://localhost:9969/ws/paymentService"; 
	
	public static void main(String[] args) {
		Endpoint.publish(URL, new PaymentServiceImp());
	}
}