package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Singleton;
import javax.ejb.Stateful;

import com.model.DeliveryInfo;

@Singleton
public class DeliveryCacheBean implements DeliveryCache {
	
	private List<DeliveryInfo> deliveryInfos = new ArrayList<DeliveryInfo>();
	
	@Override
	public List<DeliveryInfo> getDeliveryInfos() {
		
		return deliveryInfos;
	}

	@Override
	public void setDeliveryInfos(List<DeliveryInfo> deliveryInfos) {
		this.deliveryInfos = deliveryInfos;
	}

	@Override
	public void addDeliveryInfo(DeliveryInfo deliveryInfo) {
		this.deliveryInfos.add(deliveryInfo);
	}
}