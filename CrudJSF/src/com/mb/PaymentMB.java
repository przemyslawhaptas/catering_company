package com.mb;
  
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
 
import com.facade.UserFacade;
import com.model.Order;
import com.model.PaymentInfo;
import com.model.User;
 
@ManagedBean
@RequestScoped
public class PaymentMB extends ApplicationMB {
	
    private static final String DELIVERY = "newDelivery";
    
    @EJB
    private UserFacade userFacade;
    
    private Order order;
    private PaymentInfo paymentInfo;
    private User user;
    
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

    public User getUser(){
        if (user == null){
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
	
	public String pay() {
		if (getPaymentInfo() == null) {
			sendErrorMessageToUser("Your order cannot be processed. Please add your payment info first.");
		
			return null;
		}
				
		
		return DELIVERY;
	}

}