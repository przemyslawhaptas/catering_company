package com.facade;
 
import java.util.List;
 
import javax.ejb.Local;
 
import com.model.AddressInfo;

@Local
public interface AddressInfoFacade {
 
    public abstract void save(AddressInfo addressInfo);
 
    public abstract AddressInfo update(AddressInfo addressInfo);
 
    public abstract void delete(AddressInfo addressInfo);
 
    public abstract AddressInfo find(int entityID);
 
    public abstract List<AddressInfo> findAll();

}