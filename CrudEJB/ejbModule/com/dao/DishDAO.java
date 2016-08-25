package com.dao;
 
import javax.ejb.Stateless;
 
import com.model.Dish;
 
@Stateless
public class DishDAO extends GenericDAO<Dish> {
 
    public DishDAO() {
        super(Dish.class);
    }
}