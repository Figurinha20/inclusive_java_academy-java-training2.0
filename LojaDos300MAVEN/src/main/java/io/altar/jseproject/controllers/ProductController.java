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

import io.altar.jseproject.model.Product;
import io.altar.jseproject.services.ProductService;
	
@Path("products")
public class ProductController {
	private ProductService ps = new ProductService();

	@Context
	protected UriInfo context;
	
	@GET
	@Path("status")
	@Produces(MediaType.TEXT_PLAIN)
	public String status() {
		return "Url : " + context.getRequestUri().toString() + " is Ok";
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllProducts() {
		Collection<Product> products = ps.getAllEntities();
		if (products.size() > 0) {
			return Response.status(200).entity(products).build();
		}
		else {
			return Response.status(200).entity("There are no Products yet").build();
		}
	}
	
	@GET																																		
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductById(@PathParam("id") int id) {
		Product product = ps.getEntityById(id);
		if (product != null) {
			return Response.status(200).entity(product).build();
		}
		else {
			return Response.status(404).entity("Product " + id + " not found").build();
		}
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response update(Product newProduct) {
		Product product = ps.getEntityById(newProduct.getId());
		if (product != null) {
//			newProduct.setId(id);
			ps.updateEntity(newProduct);
			return Response.status(200).entity("Product " + newProduct.getId() + " updated").build();
		}
		else {
			return Response.status(404).entity("Product " + newProduct.getId() + " not found").build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response create(Product product) {
		int created = ps.addEntity(product);
		switch (created) {
		case -1:
			return Response.status(404).entity("One of the shelves was not found").build();
		case -2:
			return Response.status(409).entity("One of the shelves is not empty").build();
		default:
			return Response.status(200).entity("Product " + created + " created").build();
		}
	}
	
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") int id) {
		if(ps.getEntityById(id) == null) {
			return Response.status(404).entity("Product " + id + " not found").build();
		}
		
		Product product = ps.deleteEntity(id);
		if (product != null) {
			return Response.status(200).entity("Product " + id + " removed").build();
		}
		else {
			return Response.status(409).entity("The Product " + id + " is in shelfs, you need to make it empty first.").build();
		}
	}
}