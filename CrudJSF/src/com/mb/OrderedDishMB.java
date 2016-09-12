package com.mb;

import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import com.facade.OrderedDishFacade;
import com.model.OrderedDish;

@ManagedBean
@RequestScoped
public class OrderedDishMB {
	
	@EJB
	private OrderedDishFacade orderedDishFacade;
	
	public List<OrderedDish> getBestsellers() {
		return orderedDishFacade.getBestsellers();
	}
}