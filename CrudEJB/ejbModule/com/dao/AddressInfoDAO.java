package com.dao;
 
import javax.ejb.Stateless;
 
import com.model.AddressInfo;
 
@Stateless
public class AddressInfoDAO extends GenericDAO<AddressInfo> {
 	
    public AddressInfoDAO() {
        super(AddressInfo.class);
    }
    
}