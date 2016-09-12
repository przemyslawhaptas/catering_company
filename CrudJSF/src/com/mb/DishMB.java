package com.mb;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
 
import com.facade.DishFacade;
import com.model.Dish;
 
@ManagedBean
@RequestScoped
public class DishMB extends ApplicationMB {
 
    @EJB
    private DishFacade dishFacade;
    
    private Dish dish;
    
    // Controller actions
    
    private static final String NEW = "newDish";
    private static final String MENU = "menu";
        
    // Property access methods
    
    public Dish getDish() {
        if (dish == null) {
            dish = new Dish();
        }
        
		return dish;
	}

	public void setDish(Dish dish) {
		this.dish = dish;
	}
	
	// Decorator methods

    public List<Dish> getAllDishes() {
        return dishFacade.findAll();
    }
    
    // Controller methods
    
    public String newDish() {
        return NEW;
    }
 
    public String createDish() {
        try {
            dishFacade.save(dish);
        } catch (EJBException e) {
            sendErrorMessageToUser(e.getMessage());
 
            return null;
        }      
 
        sendInfoMessageToUser("Operation Complete: Create");
 
        return MENU;
    }
    
    public void deleteDish(int dishId) {
    	Dish dish = dishFacade.find(dishId);
    	dishFacade.delete(dish);
    }
}