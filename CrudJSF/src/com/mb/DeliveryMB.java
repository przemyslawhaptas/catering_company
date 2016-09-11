package com.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;

import com.model.DeliveryInfo;
import com.service.DeliveryCache;

@ManagedBean
@RequestScoped
public class DeliveryMB {
	
	@EJB
	private DeliveryCache deliveryCache;
	
	public List<DeliveryInfo> getDeliveryInfos() {
		
		return deliveryCache.getDeliveryInfos();
	}
}