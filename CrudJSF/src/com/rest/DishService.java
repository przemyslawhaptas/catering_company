package com.rest;

import javax.ejb.EJB;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.facade.CategoryFacade;
import com.facade.DishFacade;
import com.google.gson.Gson;
import com.marshaller.DishDemarshaller;
import com.marshaller.GsonFactory;
import com.marshaller.Marshaller;
import com.model.Category;
import com.model.Dish;

@Path("dish")
public class DishService {
	
	@EJB
	private CategoryFacade categoryFacade;
	
	@EJB
	private DishFacade dishFacade;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response create(String dishJSON) {
		Gson gson = new GsonFactory().build();
		DishDemarshaller demarshaller = new DishDemarshaller(gson);
		
		Object[] result = demarshaller.fromJson(dishJSON);
		
		Dish dish = (Dish) result[0];
		int dishCategoryId = (Integer) result[1];
		
		Category category = categoryFacade.find(dishCategoryId);
		dish.setCategory(category);
		
		dishFacade.save(dish);
		
		Marshaller marshaller = new Marshaller(gson);
		
		return Response.status(201).entity(marshaller.toJson(dish)).build();
	}

}