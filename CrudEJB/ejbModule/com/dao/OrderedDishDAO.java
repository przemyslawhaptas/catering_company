package com.dao;
 
import java.util.List;

import javax.ejb.Stateless;
 
import com.model.OrderedDish;
 
@Stateless
public class OrderedDishDAO extends GenericDAO<OrderedDish> {
 	
    public OrderedDishDAO() {
        super(OrderedDish.class);
    }
    
    public List<OrderedDish> getBestsellers() {
    	return super.findManyResults(OrderedDish.GET_BESTSELLERS, null);
    }
}