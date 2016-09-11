package com.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

import com.model.DeliveryInfo;
import com.service.DeliveryCache;

@MessageDriven(activationConfig = { 
	@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
	@ActivationConfigProperty(propertyName = "destination", propertyValue = "myQueue")
})
public class DeliveryListener implements MessageListener {
	
	@EJB
	private DeliveryCache deliveryCache;

	public DeliveryListener() {

    }

    public void onMessage(Message message) {
    	
    	try { 		
    		if (message instanceof MapMessage) {
    			MapMessage msg = (MapMessage) message;
    			String orderIdProperty = "orderId";
    			String streetProperty = "street";
    			String buildingAndApartmentNumberProperty = "buildingAndApartmentNumber";
    			
    			if (!msg.itemExists(orderIdProperty)) {
    	    		System.out.println("I received a message, but property doesn't exist:" + orderIdProperty);
    			} else {
	    			int orderId = msg.getInt(orderIdProperty);
	    			String street = msg.getString(streetProperty);
	    			String buildingAndApartmentNumber = msg.getString(buildingAndApartmentNumberProperty);
	    			
	    			DeliveryInfo deliveryInfo = new DeliveryInfo(street, buildingAndApartmentNumber, orderId);
	    			deliveryCache.addDeliveryInfo(deliveryInfo);
	
		    		System.out.println("I received following message:" + orderId);
	    		}  
    		} else {
    			System.out.println("Not valid message for this queue");
    		}   		
    	} catch (JMSException e) { 
			e.printStackTrace();
    	}
    }

}
