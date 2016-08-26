package com.mb;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
 
import com.facade.DishFacade;
import com.facade.DogFacade;
import com.model.Dish;
import com.model.Dog;
 
@ManagedBean
@RequestScoped
public class DishMB {
 
    @EJB
    private DishFacade dishFacade;
    private Dish dish;
    
    private static final String MENU = "menu";
 
    public List<Dish> getAllDishes() {
        return dishFacade.findAll();
    }
 
    public String menu(){
        return MENU;
    }
}