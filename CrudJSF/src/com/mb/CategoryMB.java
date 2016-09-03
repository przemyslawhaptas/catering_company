package com.mb;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
 
import com.facade.CategoryFacade;
import com.model.Category;
 
@ManagedBean
@RequestScoped
public class CategoryMB extends ApplicationMB {
 
    @EJB
    private CategoryFacade categoryFacade;
    
    private Category category;
    
    // Controller actions

	private static final String MENU = "menu";
	private static final String NEW = "newCategory";
	
	// Property access methods
	
    public Category getCategory() {
    	if (category == null) {
    		category = new Category();
    	}
    	
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
 
    // Decorator methods
    
    public List<Category> getAllCategories() {
        return categoryFacade.findAll();
    }
    
    public List<Category> getMenu() {
    	return categoryFacade.getMenu();
    }
    
    public List<String> getAllCategoryNames() {
    	return categoryFacade.getAllCategoryNames();
    }
    
    // Controller methods
 
    public String menu(){
        return MENU;
    }
    
    public String newCategory() {
    	return NEW;
    }
    
    public String createCategory() {
        try {
            categoryFacade.save(category);
        } catch (EJBException e) {
            sendErrorMessageToUser(e.getMessage());
 
            return null;
        }      
 
        sendInfoMessageToUser("Operation Complete: Create");
 
        return MENU;
    }
}