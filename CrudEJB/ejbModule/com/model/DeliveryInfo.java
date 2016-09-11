package com.model;

public class DeliveryInfo {

	private String street;
	private String buildingAndApartmentNumber;
	private int orderId;
	
	public DeliveryInfo(AddressInfo addressInfo, int orderId) {
		this.street = addressInfo.getStreet();
		this.buildingAndApartmentNumber = addressInfo.getBuildingAndApartmentNumber();
		this.orderId = orderId;
	}
	
	public DeliveryInfo(String street, String buildingAndApartmentNumber, int orderId) {
		this.street = street;
		this.buildingAndApartmentNumber = buildingAndApartmentNumber;
		this.orderId = orderId;
	}
	
	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getBuildingAndApartmentNumber() {
		return buildingAndApartmentNumber;
	}

	public void setBuildingAndApartmentNumber(String buildingAndApartmentNumber) {
		this.buildingAndApartmentNumber = buildingAndApartmentNumber;
	}

	public int getOrderId() {
		return orderId;
	}
	
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
    
}