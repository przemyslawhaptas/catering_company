package com.dao;
 
import javax.ejb.Stateless;
 
import com.model.Order;
 
@Stateless
public class OrderDAO extends GenericDAO<Order> {
 	
    public OrderDAO() {
        super(Order.class);
    }
    
}