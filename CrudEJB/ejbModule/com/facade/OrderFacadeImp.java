package com.facade;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.ejb.Stateless;
 
import com.dao.OrderDAO;
import com.model.Order;
 
@Stateless
public class OrderFacadeImp implements OrderFacade {
 
    @EJB
    private OrderDAO orderDAO;
 
    @Override
    public void save(Order order) {
        validate(order);
 
        orderDAO.save(order);
    }
 
    @Override
    public Order update(Order order) {
        validate(order);
 
        return orderDAO.update(order);
    }
 
    @Override
    public void delete(Order order) {
        orderDAO.delete(order.getId());
    }
 
    @Override
    public Order find(int entityID) {
        return orderDAO.find(entityID);
    }
    
    @Override
    public Order findReference(int entityID) {
        return orderDAO.findReference(entityID);
    }
 
    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }
 
    private void validate(Order order){
        String error = null;
 
        if (order == null){
            error = "order == null";
        }
 
        if (invalidStatus(order)){
            error = "order.getStatus() is invalid";
        }
        
        if (order.getPrice() < 0){
            error = "dish.getPrice() < 0";
        }
 
        if (error != null){
            throw new IllegalArgumentException("Validation failed: " + error);
        }
    }
    
    private boolean invalidStatus(Order order) {
    	String status = order.getStatus();
    	boolean invalid = true;
    	
    	for (int i = 0; i < Order.STATUSES.length; i++) {
    		if (Order.STATUSES[i].equals(status)) {
    			invalid = false;
    			break;
    		}
    	}
    	
    	return invalid;
    }

}