package com.facade;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.ejb.Stateless;
 
import com.dao.PaymentInfoDAO;
import com.model.PaymentInfo;
 
@Stateless
public class PaymentInfoFacadeImp implements PaymentInfoFacade {
	
	private static final int BANK_ACCOUNT_NUMBER_LENGTH = 12;
	private static final int AUTHENTICATION_CODE_LENGTH = 5;
 
    @EJB
    private PaymentInfoDAO paymentInfoDAO;
 
    @Override
    public void save(PaymentInfo paymentInfo) {
        validate(paymentInfo);
 
        paymentInfoDAO.save(paymentInfo);
    }
 
    @Override
    public PaymentInfo update(PaymentInfo paymentInfo) {
        validate(paymentInfo);
 
        return paymentInfoDAO.update(paymentInfo);
    }
 
    @Override
    public void delete(PaymentInfo paymentInfo) {
        paymentInfoDAO.delete(paymentInfo.getId());
    }
 
    @Override
    public PaymentInfo find(int entityID) {
        return paymentInfoDAO.find(entityID);
    }
 
    @Override
    public List<PaymentInfo> findAll() {
        return paymentInfoDAO.findAll();
    }

    private void validate(PaymentInfo paymentInfo){
        String error = null;
 
        if (paymentInfo == null) {
            error = "paymentInfo == null";
        }
 
        if (paymentInfo.getUser() == null) {
        	error = "paymentInfo.getUser() == null";
        }
        
        if (paymentInfo.getBankAccountNumber().length() != BANK_ACCOUNT_NUMBER_LENGTH) {
            error = "paymentInfo.getBankAccountNumber().length() != BANK_ACCOUNT_NUMBER_LENGTH";
        }
        
        if (paymentInfo.getAuthenticationCode().length() != AUTHENTICATION_CODE_LENGTH) {
            error = "paymentInfo.getAuthenticationCode().length() != AUTHENTICATION_CODE_LENGTH";
        }
 
        if (error != null) {
            throw new IllegalArgumentException("Validation failed: " + error);
        }
    }
}