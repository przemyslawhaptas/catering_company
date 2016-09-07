package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PAYMENT_INFOS")
public class PaymentInfo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String bankAccountNumber;
	private String authenticationCode;
    
    @OneToOne(mappedBy = "paymentInfo")
    private User user;
	 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }	

	public String getBankAccountNumber() {
		return bankAccountNumber;
	}
	

	public void setBankAccountNumber(String bankAccountNumber) {
		this.bankAccountNumber = bankAccountNumber;
	}
	

	public String getAuthenticationCode() {
		return authenticationCode;
	}
	

	public void setAuthenticationCode(String authenticationCode) {
		this.authenticationCode = authenticationCode;
	}
	

	public User getUser() {
		return user;
	}
	

	public void setUser(User user) {
		this.user = user;
	}
	

	@Override
    public int hashCode() {
        return getId();
    }

	@Override
    public boolean equals(Object obj) {
 
        if(obj instanceof PaymentInfo){
            PaymentInfo paymentInfo = (PaymentInfo) obj;
            return paymentInfo.getId() == getId();
        }
 
        return false;
    }
}