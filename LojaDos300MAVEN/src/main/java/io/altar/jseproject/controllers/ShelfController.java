package io.altar.jseproject.controllers;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import io.altar.jseproject.model.Shelf;
import io.altar.jseproject.services.ShelfService;
	
@Path("shelves")
public class ShelfController {
	private ShelfService ss = new ShelfService();

	@Context
	protected UriInfo context;
	
	@GET
	@Path("getAllShelves")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts() {
		Collection<Shelf> shelves = ss.getAllEntities();
		if (shelves.size() > 0) {
			return Response.status(200).entity(shelves).build();
		}
		else {
			return Response.status(200).entity("There are no Shelves yet").build();
		}
	}
	
	@GET
	@Path("get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShelfById(@PathParam("id") int id) {
		Shelf shelf = ss.getEntityById(id);
		if (shelf != null) {
			return Response.status(200).entity(shelf).build();
		}
		else {
			return Response.status(404).entity("Shelf " + id + " not found").build();
		}
	}
	
	@PUT
	@Path("update/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response update(@PathParam("id") int id, Shelf newShelf) {
		Shelf oldShelf = ss.getEntityById(id);
		if (oldShelf != null) {
			newShelf.setId(id);
			ss.updateEntity(newShelf, oldShelf);
			return Response.status(200).entity("Shelf " + id + " updated").build();
		}
		else {
			return Response.status(404).entity("Shelf " + id + " not found").build();
		}
	}
	
	@POST
	@Path("add")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response create(Shelf shelf) {
		int created = ss.addEntity(shelf);
		if (created != -1) {
			return Response.status(200).entity("Shelf " + created + " created").build();
		}
		else {
			return Response.status(404).entity("Product " + shelf.getProductId() + " not found").build();
		}
	}
	
	
	@DELETE
	@Path("delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		Shelf shelf = ss.deleteEntity(id);
		if (shelf != null) {
			return Response.status(200).entity("Shelf " + id + " removed").build();
		}
		else {
			return Response.status(404).entity("Shelf Product " + id + " was not found").build();
		}
	}
}