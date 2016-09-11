package com.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;

import com.facade.OrderFacade;
import com.model.DeliveryInfo;
import com.model.Order;
import com.service.DeliveryCache;

@ManagedBean
@RequestScoped
public class DeliveryMB {
	
	@EJB
	private OrderFacade orderFacade;
	
	@EJB
	private DeliveryCache deliveryCache;
	
	public List<DeliveryInfo> getDeliveryInfos() {
		return deliveryCache.getDeliveryInfos();
	}
	
	public void deliver(int orderId) {
		setStatusDelivered(orderId);
		deliveryCache.removeDeliveryInfo(orderId);
	}
	
	private void setStatusDelivered(int orderId) {
		Order deliveredOrder = orderFacade.find(orderId);
		deliveredOrder.setStatus(Order.STATUSES[2]);
		orderFacade.update(deliveredOrder);
	}
}