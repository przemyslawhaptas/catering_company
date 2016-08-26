package com.facade;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.ejb.Stateless;
 
import com.dao.DishDAO;
import com.model.Dish;
 
@Stateless
public class DishFacadeImp implements DishFacade {
	
	private static final int MAX_DESCRIPTION_LENGTH = 300;
 
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
        boolean hasError = false;
 
        if(dish == null){
            hasError = true;
        }
 
        if (dish.getName() == null || "".equals(dish.getName().trim())){
            hasError = true;
        }
        
        if (dish.getDescription().length() > MAX_DESCRIPTION_LENGTH) {
            hasError = true;
        }
 
        if (dish.getQuantity() < 0){
            hasError = true;
        }
 
        if (hasError){
            throw new IllegalArgumentException("The dog is missing data. Check the name and weight, they should have value.");
        }
    }
}