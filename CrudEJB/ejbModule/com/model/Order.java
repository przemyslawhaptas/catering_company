package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERS")
public class Order {
	
	public static String[] STATUSES = new String[] { "new", "paid", "delivered" };
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private double price;
	
	private String status = STATUSES[0];
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public double getPrice() {
		return price;
	}
    

	public void setPrice(double price) {
		this.price = price;
	}
	

	public String getStatus() {
		return status;
	}
	

	public void setStatus(String status) {
		this.status = status;
	}
	

	@Override
    public int hashCode() {
        return getId();
    }

	@Override
    public boolean equals(Object obj) {
 
        if(obj instanceof Order){
            Order order = (Order) obj;
            return order.getId() == getId();
        }
 
        return false;
    }
}