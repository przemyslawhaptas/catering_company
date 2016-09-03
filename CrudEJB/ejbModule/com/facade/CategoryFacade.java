package com.facade;
 
import java.util.List;
 
import javax.ejb.Local;
 
import com.model.Category;;

@Local
public interface CategoryFacade {
 
    public abstract void save(Category category);
 
    public abstract Category update(Category category);
 
    public abstract void delete(Category category);
 
    public abstract Category find(int entityID);
    
    public abstract Category findReference(int entityID);
 
    public abstract List<Category> findAll();
    
    public abstract List<String> getAllCategoryNames();
    
    public abstract List<Category> getMenu();
}