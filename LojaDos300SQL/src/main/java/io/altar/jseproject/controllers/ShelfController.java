package io.altar.jseproject.controllers;

import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

import io.altar.jseproject.models.DTOs.ShelfDTO;
import io.altar.jseproject.models.converters.ShelfConverter;
import io.altar.jseproject.models.entities.Shelf;
import io.altar.jseproject.services.ShelfService;

@RequestScoped
@Path("shelves")
public class ShelfController {
	
	@Inject
	ShelfService service;
	
	@Inject
	ShelfConverter converter;

	@Context
	protected UriInfo context;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts() {
		Collection<Shelf> shelves = service.getAllEntities();
		if (shelves.size() > 0) {
			return Response.status(200).entity(converter.toDTOArray(shelves)).build();
		}
		else {
			return Response.status(200).entity("There are no Shelves yet").build();
		}
	}
	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getShelfById(@PathParam("id") int id) {
		Shelf shelf = service.getEntityById(id);
		if (shelf != null) {
			return Response.status(200).entity(converter.toDTO(shelf)).build();
		}
		else {
			return Response.status(404).entity("Shelf " + id + " not found").build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response update(ShelfDTO shelfDTO) {
		Shelf oldShelf = service.getEntityById(shelfDTO.getId());
		Shelf newShelf = converter.toEntity(shelfDTO);
		if (oldShelf != null) {
			service.updateEntity(newShelf, oldShelf);
			return Response.status(200).entity("Shelf " + newShelf.getId() + " updated").build();
		}
		else {
			return Response.status(404).entity("Shelf " + newShelf.getId() + " not found").build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response create(ShelfDTO shelfDTO) {
		Shelf shelf = converter.toEntity(shelfDTO);
		int created = service.addEntity(shelf);
		switch (created) {
		case -1:
			return Response.status(404).entity("Product " + shelf.getProduct().getId() + " not found").build();
		default :
			return Response.status(200).entity("Shelf " + created + " created").build();
		}
	}
	
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		if(service.getEntityById(id) == null) {
			return Response.status(404).entity("Shelf " + id + " not found").build();
		}
	
		try {
			service.deleteEntity(id);
			return Response.status(200).entity("Shelf " + id + " removed").build();
		}
		catch (UnsupportedOperationException  e) {
			return Response.status(409).entity(e.getMessage()).build();
		}
	}
}