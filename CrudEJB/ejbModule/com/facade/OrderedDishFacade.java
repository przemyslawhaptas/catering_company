package com.facade;
 
import java.util.List;
 
import javax.ejb.Local;
 
import com.model.OrderedDish;;

@Local
public interface OrderedDishFacade {
 
    public abstract void save(OrderedDish orderedDish);
 
    public abstract OrderedDish update(OrderedDish orderedDish);
 
    public abstract void delete(OrderedDish orderedDish);
 
    public abstract OrderedDish find(int entityID);
 
    public abstract List<OrderedDish> findAll();
    
    public abstract List<OrderedDish> getBestsellers();

}