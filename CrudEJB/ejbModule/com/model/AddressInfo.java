package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS_INFOS")
public class AddressInfo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private String street;
	private String buildingAndApartmentNumber;
    
    @OneToOne(mappedBy = "addressInfo")
    private User user;
	 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }	

	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getBuildingAndApartmentNumber() {
		return buildingAndApartmentNumber;
	}	

	public void setBuildingAndApartmentNumber(String buildingAndApartmentNumber) {
		this.buildingAndApartmentNumber = buildingAndApartmentNumber;
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
 
        if(obj instanceof AddressInfo){
            AddressInfo addressInfo = (AddressInfo) obj;
            return addressInfo.getId() == getId();
        }
 
        return false;
    }
}