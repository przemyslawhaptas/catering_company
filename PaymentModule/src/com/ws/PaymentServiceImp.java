package com.ws;

import javax.jws.WebService;

@WebService(endpointInterface = "com.ws.PaymentService")
public class PaymentServiceImp implements PaymentService {
	
	@Override
	public boolean acceptPayment(String bankAccountNumber, String authenticationCode, double price) {
		return validAccountNumber(bankAccountNumber) && enoughMoney(bankAccountNumber, authenticationCode, price);
	}
	
	private boolean validAccountNumber(String bankAccountNumber) {
		return true;
	}
	
	private boolean enoughMoney(String bankAccountNumber, String authenticationCode, double price) {
		return true;
	}
}