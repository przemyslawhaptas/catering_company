package com.facade;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.ejb.Stateless;
 
import com.dao.AddressInfoDAO;
import com.model.AddressInfo;
 
@Stateless
public class AddressInfoFacadeImp implements AddressInfoFacade {
	
	private static final int STREET_MAX_LENGTH = 25;
	private static final int BUILDING_AND_APARTMENT_NUMBER_MAX_LENGTH = 10;
 
    @EJB
    private AddressInfoDAO addressInfoDAO;
 
    @Override
    public void save(AddressInfo addressInfo) {
        validate(addressInfo);
 
        addressInfoDAO.save(addressInfo);
    }
 
    @Override
    public AddressInfo update(AddressInfo addressInfo) {
        validate(addressInfo);
 
        return addressInfoDAO.update(addressInfo);
    }
 
    @Override
    public void delete(AddressInfo addressInfo) {
        addressInfoDAO.delete(addressInfo.getId());
    }
 
    @Override
    public AddressInfo find(int entityID) {
        return addressInfoDAO.find(entityID);
    }
 
    @Override
    public List<AddressInfo> findAll() {
        return addressInfoDAO.findAll();
    }

    private void validate(AddressInfo addressInfo){
        String error = null;
 
        if (addressInfo == null) {
            error = "addressInfo == null";
        }
 
        if (addressInfo.getUser() == null) {
        	error = "addressInfo.getUser() == null";
        }
        
        if (addressInfo.getStreet().length() > STREET_MAX_LENGTH) {
            error = "addressInfo.getStreet().length() > STREET_MAX_LENGTH";
        }
        
        if (addressInfo.getBuildingAndApartmentNumber().length() > BUILDING_AND_APARTMENT_NUMBER_MAX_LENGTH) {
            error = "addressInfo.getBuildingAndApartmentNumber().length() > BUILDING_AND_APARTMENT_NUMBER_MAX_LENGTH";
        }
 
        if (error != null) {
            throw new IllegalArgumentException("Validation failed: " + error);
        }
    }
}