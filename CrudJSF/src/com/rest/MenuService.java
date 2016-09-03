package com.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.facade.CategoryFacade;
import com.model.Category;

@Path("/menuService")
public class MenuService {
	
	@EJB
	private CategoryFacade categoryFacade;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("menu")
	public Response getMenu() {
		Marshaller marshaller = new Marshaller();
		List<Category> menu = categoryFacade.getMenu();
		String menuJSON = marshaller.toJSONString(menu);
				
		return Response.ok(menuJSON).build();
	}
}
