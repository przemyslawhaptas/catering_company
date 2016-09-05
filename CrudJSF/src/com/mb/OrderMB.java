package com.mb;
 
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
 
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
 
import com.facade.DishFacade;
import com.facade.OrderFacade;
import com.model.Dish;
import com.model.Order;
import com.model.OrderedDish;
 
@ManagedBean
@RequestScoped
public class OrderMB {
    
	@EJB
	private DishFacade dishFacade;
	
	@EJB
	private OrderFacade orderFacade;
	
    private Map<Integer, Boolean> selectedDishIds = new HashMap<Integer, Boolean>();
    
    private Order order;
        
    // Property access methods
	
	public Map<Integer, Boolean> getSelectedDishIds() {
		return selectedDishIds;
	}

	public void setSelectedDishIds(Map<Integer, Boolean> selectedDishIds) {
		this.selectedDishIds = selectedDishIds;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
    
    // Controller methods

	public String create() {
		order = new Order();
		List<OrderedDish> orderedDishes= buildOrderedDishes(order, selectedDishIds);
		order.setOrderedDishes(orderedDishes);
		order.setPrice(countPrice(order));
		
		orderFacade.save(order);
		
		return null;
	}
	
	private List<OrderedDish> buildOrderedDishes(Order order, Map<Integer, Boolean> selectedDishIds) {
		List<OrderedDish> orderedDishes = new ArrayList<OrderedDish>();
		
		for (Map.Entry<Integer, Boolean> entry : selectedDishIds.entrySet()) {
			if (entry.getValue() == false) {
				continue;						
			}
			
			int dishId = entry.getKey();
			OrderedDish orderedDish = buildOrderedDish(dishId, order);
			orderedDishes.add(orderedDish);						
		}
		
		return orderedDishes;
	}
	
	private OrderedDish buildOrderedDish(int dishId, Order order) {
		Dish dish = dishFacade.find(dishId);
		int quantity = 1;
		
		OrderedDish orderedDish = new OrderedDish();
		orderedDish.setDish(dish);
		orderedDish.setQuantity(quantity);
		orderedDish.setOrder(order);
		
		return orderedDish;
	}
	
	private double countPrice(Order order) {
		double price = 0;
		List<OrderedDish> orderedDishes = order.getOrderedDishes();
		
		for(OrderedDish orderedDish: orderedDishes) {
			price = price + orderedDish.getDish().getPrice();
		}
		
		return price;
	}
}