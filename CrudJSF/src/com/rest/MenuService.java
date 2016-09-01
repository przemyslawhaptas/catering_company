package com.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/menuService")
public class MenuService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("status")
	public Response getStatus() {
		Marshaller marshaller = new Marshaller();
		return Response.ok(marshaller.toJSONString("OKEY")).build();
	}
}
