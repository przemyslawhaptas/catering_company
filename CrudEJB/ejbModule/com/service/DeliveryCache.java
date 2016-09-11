package com.service;

import java.util.List;

import javax.ejb.Local;

import com.model.DeliveryInfo;

@Local
public interface DeliveryCache {
	
	public List<DeliveryInfo> getDeliveryInfos();
	public void setDeliveryInfos(List<DeliveryInfo> deliveryInfos);
	public void addDeliveryInfo(DeliveryInfo deliveryInfo);
	public void removeDeliveryInfo(int orderId);
	
}