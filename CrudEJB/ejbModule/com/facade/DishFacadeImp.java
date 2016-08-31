package com.facade;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.ejb.Stateless;
 
import com.dao.DishDAO;
import com.model.Dish;
 
@Stateless
public class DishFacadeImp implements DishFacade {
	
	private static final int MAX_DESCRIPTION_LENGTH = 300;
	private static final int MAX_NAME_LENGTH = 30;
 
    @EJB
    private DishDAO dishDAO;
 
    @Override
    public void save(Dish dish) {
        validate(dish);
 
        dishDAO.save(dish);
    }
 
    @Override
    public Dish update(Dish dish) {
        validate(dish);
 
        return dishDAO.update(dish);
    }
 
    @Override
    public void delete(Dish dish) {
        dishDAO.delete(dish.getId());
    }
 
    @Override
    public Dish find(int entityID) {
        return dishDAO.find(entityID);
    }
 
    @Override
    public List<Dish> findAll() {
        return dishDAO.findAll();
    }

    private void validate(Dish dish){
        String error = null;
 
        if (dish == null){
            error = "dish == null";
        }
 
        if (dish.getName() == null || "".equals(dish.getName().trim())){
            error = "dish.getName() == null || \"\".equals(dish.getName().trim())";
        }
        
        if (dish.getName().length() > MAX_NAME_LENGTH) {
        	error = "dish.getName().length() > MAX_NAME_LENGTH";
        }
        
        if (dish.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            error = "dish.getDescription().length() > MAX_DESCRIPTION_LENGTH";
        }
        
        if (dish.getCategory() == null) {
        	error = "dish.getCategory() == null";
        }
 
        if (dish.getQuantity() < 0){
            error = "dish.getQuantity() < 0";
        }
 
        if (error != null){
            throw new IllegalArgumentException("Validation failed: " + error);
        }
    }
}