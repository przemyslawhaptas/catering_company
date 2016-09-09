package com.dao;
 
import javax.ejb.Stateless;
 
import com.model.OrderedDish;
 
@Stateless
public class OrderedDishDAO extends GenericDAO<OrderedDish> {
 	
    public OrderedDishDAO() {
        super(OrderedDish.class);
    }
    
}