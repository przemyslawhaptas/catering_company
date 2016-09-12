package com.facade;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.ejb.Stateless;
 
import com.dao.OrderedDishDAO;
import com.model.OrderedDish;
 
@Stateless
public class OrderedDishFacadeImp implements OrderedDishFacade {
 
    @EJB
    private OrderedDishDAO orderedDishDAO;
 
    @Override
    public void save(OrderedDish orderedDish) {
        validate(orderedDish);
 
        orderedDishDAO.save(orderedDish);
    }
 
    @Override
    public OrderedDish update(OrderedDish orderedDish) {
        validate(orderedDish);
 
        return orderedDishDAO.update(orderedDish);
    }
 
    @Override
    public void delete(OrderedDish orderedDish) {
        orderedDishDAO.delete(orderedDish.getId());
    }
 
    @Override
    public OrderedDish find(int entityID) {
        return orderedDishDAO.find(entityID);
    }
 
    @Override
    public List<OrderedDish> findAll() {
        return orderedDishDAO.findAll();
    }
    
    @Override
    public List<OrderedDish> getBestsellers() {
    	return orderedDishDAO.getBestsellers();
    }

    private void validate(OrderedDish orderedDish){
        String error = null;
 
        if (orderedDish == null){
            error = "orderedDish == null";
        }
 
        if (orderedDish.getDish() == null) {
        	error = "orderedDish.getDish() == null";
        }
        
        if (orderedDish.getOrder() == null) {
        	error = "orderedDish.getOrder() == null";
        }
 
        if (orderedDish.getQuantity() < 0){
            error = "orderedDish.getQuantity() < 0";
        }
 
        if (error != null){
            throw new IllegalArgumentException("Validation failed: " + error);
        }
    }
}