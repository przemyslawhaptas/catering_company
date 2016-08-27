package com.facade;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.ejb.Stateless;
 
import com.dao.CategoryDAO;
import com.model.Category;
 
@Stateless
public class CategoryFacadeImp implements CategoryFacade {
	
	private static final int MAX_NAME_LENGTH = 30;
 
    @EJB
    private CategoryDAO categoryDAO;
 
    @Override
    public void save(Category category) {
        validate(category);
 
        categoryDAO.save(category);
    }
 
    @Override
    public Category update(Category category) {
        validate(category);
 
        return categoryDAO.update(category);
    }
 
    @Override
    public void delete(Category category) {
        categoryDAO.delete(category.getId());
    }
 
    @Override
    public Category find(int entityID) {
        return categoryDAO.find(entityID);
    }
 
    @Override
    public List<Category> findAll() {
        return categoryDAO.findAll();
    }
 
    private void validate(Category category){
        String error = null;
 
        if (category == null){
            error = "category == null";
        }
 
        if (category.getName() == null || "".equals(category.getName().trim())){
            error = "category.getName() == null || \"\".equals(category.getName().trim())";
        }
        
        if (category.getName().length() > MAX_NAME_LENGTH) {
        	error = "category.getName().length() > MAX_NAME_LENGTH";
        }
 
        if (error != null){
            throw new IllegalArgumentException("Validation failed: " + error);
        }
    }
}