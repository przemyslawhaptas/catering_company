
package com.mb;
  
import javax.ejb.EJB;
import javax.ejb.EJBException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.facade.PaymentInfoFacade;
import com.facade.UserFacade;
import com.model.PaymentInfo;
import com.model.User;
 
@ManagedBean
@RequestScoped
public class PaymentInfoMB extends ApplicationMB {
	
	private static final String PAYMENT_INFO = "paymentInfo";
	    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private PaymentInfoFacade paymentInfoFacade;
    
    private PaymentInfo paymentInfo;
    private User user;
    
    // Property access methods
    	
	public PaymentInfo getPaymentInfo() {
		if (paymentInfo == null) {
			PaymentInfo currentUsersPaymentInfo = getUser().getPaymentInfo();
			if (currentUsersPaymentInfo == null) {
				paymentInfo = new PaymentInfo();
			} else {
				paymentInfo = currentUsersPaymentInfo;
			}
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
    
	public String create() {
        try {
        	User user = getUser();
        	paymentInfo.setUser(user);
            paymentInfoFacade.save(paymentInfo);
            
            user.setPaymentInfo(paymentInfo);
            userFacade.update(user);
        } catch (EJBException e) {
            sendErrorMessageToUser(e.getMessage());         
        }       
 
        return PAYMENT_INFO;
	}
	
	public String delete() {
        try {
        	User user = getUser();
            user.setPaymentInfo(null);
            userFacade.update(user);
            
            paymentInfoFacade.delete(paymentInfo);
        } catch (EJBException e) {
            sendErrorMessageToUser(e.getMessage());         
        }
        
        return PAYMENT_INFO;
	}
	
}