package com.ws.client;

import com.model.PaymentInfo;
import com.ws.PaymentService;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class PaymentServiceClient {
	
	public static final String WSDL_URL = "http://localhost:9969/ws/paymentService?wsdl"; 
	private String bankAccountNumber;
	private String authenticationCode;
	private double price;
	
	
	public PaymentServiceClient(PaymentInfo paymentInfo, double price) {
		this.bankAccountNumber = paymentInfo.getBankAccountNumber();
		this.authenticationCode = paymentInfo.getAuthenticationCode();
		this.price = price;
	}
	
	public boolean paymentAccepted() {
		boolean accepted = false;
		
		try {
			URL url = new URL(WSDL_URL);
			QName qname = new QName("http://ws.com/", "PaymentServiceImpService");
			Service service = Service.create(url, qname);
			PaymentService paymentService = service.getPort(PaymentService.class);
			
			accepted = paymentService.acceptPayment(getBankAccountNumber(), getAuthenticationCode(), getPrice());
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return accepted;
	}

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}

	public String getAuthenticationCode() {
		return authenticationCode;
	}

	public void setAuthenticationCode(String authenticationCode) {
		this.authenticationCode = authenticationCode;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}	