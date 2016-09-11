package com.mb;
  
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
 
import com.service.DeliverySender;
import com.ws.client.PaymentServiceClient;
import com.facade.OrderFacade;
import com.facade.UserFacade;
import com.model.AddressInfo;
import com.model.Order;
import com.model.PaymentInfo;
import com.model.User;
 
@ManagedBean
@RequestScoped
public class PaymentMB extends ApplicationMB {
	
    private static final String START = "start";
    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private OrderFacade orderFacade;
    
    private Order order;
    private PaymentInfo paymentInfo;
    private User user;
    
    @EJB
	private DeliverySender deliverySender;
    
    // Property access methods
    
    public Order getOrder() {
    	if (order == null) {
    		order = (Order) restoreFromSession("order");
    	}
    	
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
	
	public PaymentInfo getPaymentInfo() {
		if (paymentInfo == null) {
			paymentInfo = getUser().getPaymentInfo();
		}
		
		return paymentInfo;
	}

	public void setPaymentInfo(PaymentInfo paymentInfo) {
		this.paymentInfo = paymentInfo;
	}

    public User getUser() {
        if (user == null) {
            ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
            String userEmail = context.getUserPrincipal().getName();
 
            user = userFacade.findUserByEmail(userEmail);
        }
 
        return user;
    }

	public void setUser(User user) {
		this.user = user;
	}

    // Controller methods
	
	public String pay(AddressInfo addressInfo) {
		PaymentInfo paymentInfo = getPaymentInfo();
		if (paymentInfo == null) {
			sendErrorMessageToUser("Your order cannot be processed. Please add your payment info first.");
		
			return null;
		}
		
		double price = order.getPrice();
		removeFromSession("order");
		PaymentServiceClient paymentServiceClient = new PaymentServiceClient(paymentInfo, price);
		boolean accepted = paymentServiceClient.paymentAccepted();
		
		if (!accepted) {
			sendErrorMessageToUser("Something went wrong... Please try again later!");
			
			return null;
		}
		
		order.setStatus(Order.STATUSES[1]);
		orderFacade.update(order);
		
		int estimatedTime = (int) (Math.random() * 100);
		sendInfoMessageToUser("Payment accepted! Estimated delivery time: " + estimatedTime + " minutes. Bon apetit!");
		
		sendDelivery(addressInfo, order.getId());
		
		return START;
	}
	
	public void sendDelivery(AddressInfo addressInfo, int orderId) {
		deliverySender.perform(addressInfo, orderId);
	}

}