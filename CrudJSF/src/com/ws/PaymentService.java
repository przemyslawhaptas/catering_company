package com.ws;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface PaymentService {
	
	@WebMethod
	public boolean acceptPayment(String bankAccountNumber, String authenticationCode, double price);
}