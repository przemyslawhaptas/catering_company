package com.mb;
  
import javax.ejb.EJB;
import javax.ejb.EJBException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.facade.AddressInfoFacade;
import com.facade.UserFacade;
import com.model.AddressInfo;
import com.model.User;
 
@ManagedBean
@RequestScoped
public class AddressInfoMB extends ApplicationMB {
	
	private static final String ADDRESS_INFO = "addressInfo";
	    
    @EJB
    private UserFacade userFacade;
    
    @EJB
    private AddressInfoFacade addressInfoFacade;
    
    private AddressInfo addressInfo;
    private User user;
    
    // Property access methods
    	
	public AddressInfo getAddressInfo() {
		if (addressInfo == null) {
			AddressInfo currentUsersAddressInfo = getUser().getAddressInfo();
			if (currentUsersAddressInfo == null) {
				addressInfo = new AddressInfo();
			} else {
				addressInfo = currentUsersAddressInfo;
			}
		}	
			
		return addressInfo;
	}

	public void setAddressInfo(AddressInfo addressInfo) {
		this.addressInfo = addressInfo;
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
        	addressInfo.setUser(user);
            addressInfoFacade.save(addressInfo);
            
            user.setAddressInfo(addressInfo);
            userFacade.update(user);
        } catch (EJBException e) {
            sendErrorMessageToUser(e.getMessage());         
        }       
 
        return ADDRESS_INFO;
	}	
	
	public String delete() {
        try {
        	User user = getUser();
            user.setAddressInfo(null);
            userFacade.update(user);
            
            addressInfoFacade.delete(addressInfo);
        } catch (EJBException e) {
            sendErrorMessageToUser(e.getMessage());         
        }
        
        return ADDRESS_INFO;
	}
}