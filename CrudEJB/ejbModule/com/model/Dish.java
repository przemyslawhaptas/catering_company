package com.model;
 
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
 
@Entity
@Table(name = "DISHES")
public class Dish {
	
	@Expose
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
	@Expose
    @Column(unique = true)
    private String name;
	
	@Expose
    private String description;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;
    
    @OneToMany(mappedBy = "dish", orphanRemoval = true)
    private List<OrderedDish> orderedDishes;
    
    @Expose
    private double price;
    
    @Expose
    private int quantity;
 
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
    
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
    public double getPrice() {
        return price;
    }
 
    public void setPrice(double price) {
        this.price = price;
    }
    
    public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
    public List<OrderedDish> getOrderedDishes() {
		return orderedDishes;
	}
    

	public void setOrderedDishes(List<OrderedDish> orderedDishes) {
		this.orderedDishes = orderedDishes;
	}
	

	@Override
    public int hashCode() {
        return getId();
    }

	@Override
    public boolean equals(Object obj) {
        if(obj instanceof Dish){
            Dish dish = (Dish) obj;
            return dish.getId() == getId();
        }
        return false;
    }
}