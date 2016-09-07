package com.mb;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class ApplicationMB {

    protected void sendInfoMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }
 
    protected void sendErrorMessageToUser(String message){
        FacesContext context = getContext();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }
 
    protected FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }
    
	protected ExternalContext getExternalContext() {
		return getContext().getExternalContext();
	}
    
    protected void storeInSession(String name, Object object) {
    	Map<String, Object> sessionMap = getExternalContext().getSessionMap();
    	
    	sessionMap.put(name, object);
    }
    
    protected Object restoreFromSession(String name) {
    	Map<String, Object> sessionMap = getExternalContext().getSessionMap();
    	
    	return sessionMap.remove(name);
    }
}