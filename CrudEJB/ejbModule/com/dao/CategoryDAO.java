package com.dao;
 
import java.util.List;

import javax.ejb.Stateless;
 
import com.model.Category;
 
@Stateless
public class CategoryDAO extends GenericDAO<Category> {
 	
    public CategoryDAO() {
        super(Category.class);
    }
    
    public List<Category> getMenu() {
        return super.findManyResults(Category.MENU_QUERY, null);
    }
}