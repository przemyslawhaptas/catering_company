package com.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.facade.CategoryFacade;
import com.google.gson.Gson;
import com.marshaller.GsonFactory;
import com.marshaller.Marshaller;
import com.model.Category;

@Path("category")
public class CategoryService {
	
	@EJB
	private CategoryFacade categoryFacade;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/")
	public Response index() {
		Gson gson = new GsonFactory().build();
		Marshaller marshaller = new Marshaller(gson);
		List<Category> menu = categoryFacade.getMenu();
		String menuJSON = marshaller.toJson(menu);
				
		return Response.ok(menuJSON).build();
	}

}
