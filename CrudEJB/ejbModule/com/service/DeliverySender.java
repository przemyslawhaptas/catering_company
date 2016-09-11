package com.service;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueSession;
import javax.jms.Session;

import com.model.AddressInfo;
import com.model.DeliveryInfo;

@Stateless
public class DeliverySender {
	
	@EJB
	DeliveryCache cache;
	
	@Resource(mappedName="java:jboss/exported/jms/RemoteConnectionFactory")
	private ConnectionFactory connectionFactory;
	
	@Resource(mappedName="java:jboss/exported/jms/queue/myQueue")
	private Queue queue;
	
	private MessageProducer producer;
	private Session session = null;
	
	public void perform(AddressInfo addressInfo, int orderId) {
		Connection connection = null;
		
		try {
			connection = connectionFactory.createConnection();
			session = connection.createSession(false, QueueSession.AUTO_ACKNOWLEDGE);
		    producer = session.createProducer(queue);
		    connection.start();
			
			MapMessage message = session.createMapMessage();
			message.setInt("orderId", orderId);
			message.setString("street", addressInfo.getStreet());
			message.setString("buildingAndApartmentNumber", addressInfo.getBuildingAndApartmentNumber());
			
			producer.send(queue, message);
			fin(orderId, addressInfo);
		} catch(JMSException e) {
			e.printStackTrace();
		} finally {
	        if (connection != null) {
	            try {
	                connection.close();
	            } catch (JMSException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
		
	private void fin(int orderId, AddressInfo addressInfo) {
		cache.addDeliveryInfo(new DeliveryInfo(addressInfo, orderId));
	}
}