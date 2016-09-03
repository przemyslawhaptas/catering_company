package com.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
 
@Entity
@Table(name = "CATEGORIES")
@NamedQuery(name = "Category.getMenu", query = "select distinct c from Category c right outer join fetch c.dishes")
public class Category {
	    
    public static final String MENU_QUERY = "Category.getMenu";

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @Column(unique = true)
    private String name;
    
    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private List<Dish> dishes;
 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Dish> getDishes() {
		return dishes;
	}

	public void setDishes(List<Dish> dishes) {
		this.dishes = dishes;
	}

    @Override
    public int hashCode() {
        return getId();
    }

	@Override
    public boolean equals(Object obj) {
 
        if(obj instanceof Category){
            Category category = (Category) obj;
            return category.getId() == getId();
        }
 
        return false;
    }
}
