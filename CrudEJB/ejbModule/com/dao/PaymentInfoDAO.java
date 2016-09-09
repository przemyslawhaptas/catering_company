package com.dao;
 
import javax.ejb.Stateless;
 
import com.model.PaymentInfo;
 
@Stateless
public class PaymentInfoDAO extends GenericDAO<PaymentInfo> {
 	
    public PaymentInfoDAO() {
        super(PaymentInfo.class);
    }
    
}