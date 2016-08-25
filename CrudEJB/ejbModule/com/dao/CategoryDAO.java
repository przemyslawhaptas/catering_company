package com.dao;
 
import javax.ejb.Stateless;
 
import com.model.Category;
 
@Stateless
public class CategoryDAO extends GenericDAO<Category> {
 
    public CategoryDAO() {
        super(Category.class);
    }
}