package com.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ORDERED_DISHES")
public class OrderedDish {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
	
	private int quantity;
	
    @ManyToOne
    @JoinColumn(name = "order_id")
	private Order order;
    
    @ManyToOne
    @JoinColumn(name = "dish_id")
    private Dish dish;
	 
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Order getOrder() {
		return order;
	}
	

	public void setOrder(Order order) {
		this.order = order;
	}
	

	public Dish getDish() {
		return dish;
	}
	

	public void setDish(Dish dish) {
		this.dish = dish;
	}
	

	@Override
    public int hashCode() {
        return getId();
    }

	@Override
    public boolean equals(Object obj) {
 
        if(obj instanceof OrderedDish){
            OrderedDish orderedDish = (OrderedDish) obj;
            return orderedDish.getId() == getId();
        }
 
        return false;
    }
}