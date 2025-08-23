package io.altar.jseproject.models.converters;

import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.jseproject.models.DTOs.ProductDTO;
import io.altar.jseproject.models.entities.Product;
import io.altar.jseproject.models.entities.Shelf;
import io.altar.jseproject.services.ShelfService;

@RequestScoped
public class ProductConverter extends EntityConverter<Product, ProductDTO> {
	@Inject
	private ShelfService SS;

	public Product toEntity(ProductDTO productDTO) {
		Product product = new Product();
		if (productDTO.getId() > 0) {
			product.setId(productDTO.getId());
		}
		product.setName(productDTO.getName());
		product.setIva(productDTO.getIva());
		product.setPvp(productDTO.getPvp());
		product.setDiscount(productDTO.getDiscount());
		if (productDTO.getShelfIds() != null) {
			product.setShelves(productDTO.getShelfIds().stream().map(entityId -> {
				Shelf shelf = SS.getEntityById(entityId);
				shelf.setProduct(product);
				return shelf;
			}).collect(Collectors.toList()));
		}
		else {
			product.setShelves(new ArrayList<Shelf>());
		}

		return product;
	}

	public ProductDTO toDTO(Product product) {
		return new ProductDTO(
				product.getName(),
				product.getId(),
				product.getShelves().stream().map(Shelf::getId).collect(Collectors.toList()),
				product.getDiscount(),
				product.getIva(),
				product.getPvp());
	}
	
	public ArrayList<ProductDTO> toDTOArray(Collection<Product> products) {
		ArrayList<ProductDTO> DTOArray = new ArrayList<ProductDTO>();
		for (Product product : products) {
			ProductDTO DTO = new ProductDTO(
					product.getName(),
					product.getId(),
					product.getShelves().stream().map(Shelf::getId).collect(Collectors.toList()),
					product.getDiscount(),
					product.getIva(),
					product.getPvp());

			DTOArray.add(DTO);
		}
		return DTOArray;
	}
}