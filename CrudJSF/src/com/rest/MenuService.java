package com.rest;

import java.util.List;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
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
	
	public MenuService() {
//	    try {
//	        String lookupName = "java:global/CrudEJB/ejbModule/com/facade/CategoryFacadeImp";
//	        categoryFacade = (CategoryFacade) InitialContext.doLookup(lookupName);
//	    } catch (NamingException e) {
//	        e.printStackTrace();
//	    }
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("menu")
	public Response getMenu() {
		Marshaller marshaller = new Marshaller();
//		List<Category> menu = categoryFacade.getMenu();
		List<String> categories = categoryFacade.getAllCategoryNames();
		
		return Response.ok(marshaller.toJSONString(categories)).build();
	}
}
