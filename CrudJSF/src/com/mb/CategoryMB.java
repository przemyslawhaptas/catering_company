package com.mb;
 
import java.util.List;
 
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
 
import com.facade.CategoryFacade;
import com.model.Category;
 
@ManagedBean
@RequestScoped
public class CategoryMB {
 
    @EJB
    private CategoryFacade categoryFacade;
    
    private static final String MENU = "menu";
 
    public List<Category> getAllCategories() {
        return categoryFacade.findAll();
    }
 
    public String menu(){
        return MENU;
    }
}