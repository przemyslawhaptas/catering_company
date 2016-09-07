package com.facade;
 
import java.util.List;
 
import javax.ejb.Local;
 
import com.model.PaymentInfo;

@Local
public interface PaymentInfoFacade {
 
    public abstract void save(PaymentInfo paymentInfo);
 
    public abstract PaymentInfo update(PaymentInfo paymentInfo);
 
    public abstract void delete(PaymentInfo paymentInfo);
 
    public abstract PaymentInfo find(int entityID);
 
    public abstract List<PaymentInfo> findAll();

}