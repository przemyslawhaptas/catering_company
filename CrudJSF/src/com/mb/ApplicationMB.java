package com.mb;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class ApplicationMB {

    protected void sendInfoMessageToUser(String message){
    	sendMessageToUser(message, FacesMessage.SEVERITY_INFO);
    }
 
    protected void sendErrorMessageToUser(String message){
    	sendMessageToUser(message, FacesMessage.SEVERITY_ERROR);
    }
    
    private void sendMessageToUser(String message, Severity severity) {
		getContext().addMessage(null, new FacesMessage(severity, message, message));
    }
    
    protected void storeInSession(String name, Object object) {    	
    	getSessionMap().put(name, object);
    }
    
    protected Object restoreFromSession(String name) {
    	return getSessionMap().get(name);
    }
    
    protected void removeFromSession(String name) {
    	getSessionMap().remove(name);
    }
    
    private FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }
    
	private ExternalContext getExternalContext() {
		return getContext().getExternalContext();
	}
    
    private Map<String, Object> getSessionMap() {
    	return getExternalContext().getSessionMap();
    }
}