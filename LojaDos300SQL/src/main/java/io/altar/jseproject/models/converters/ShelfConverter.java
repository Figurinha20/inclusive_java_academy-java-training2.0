package io.altar.jseproject.models.converters;

import java.util.ArrayList;
import java.util.Collection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import io.altar.jseproject.models.DTOs.ShelfDTO;
import io.altar.jseproject.models.entities.Shelf;
import io.altar.jseproject.services.ProductService;

@RequestScoped
public class ShelfConverter extends EntityConverter<Shelf, ShelfDTO> {

	@Inject
	ProductService PS;

	public Shelf toEntity(ShelfDTO shelfDTO) {
		Shelf shelf = new Shelf();
		if (shelfDTO.getId() > 0) {
			shelf.setId(shelfDTO.getId());
		}
		shelf.setCapacity(shelfDTO.getCapacity());
		shelf.setDailyPrice(shelfDTO.getDailyPrice());
		System.out.println("selfDTO.getProductId() :" + shelfDTO.getProductId());
		shelf.setProduct(!shelfDTO.isEmpty() ? PS.getEntityById(shelfDTO.getProductId()) : null);
		return shelf;
	}

	public ShelfDTO toDTO(Shelf shelf) {
		return new ShelfDTO(
				shelf.getId(), 
				shelf.getCapacity(), 
				shelf.getDailyPrice(),
				shelf.isEmpty() ? -1 : shelf.getProduct().getId());
	}
	
	public ArrayList<ShelfDTO> toDTOArray(Collection<Shelf> shelves) {
		ArrayList<ShelfDTO> DTOArray = new ArrayList<ShelfDTO>();
		for (Shelf shelf : shelves) {
			ShelfDTO DTO = new ShelfDTO(
					shelf.getId(), 
					shelf.getCapacity(), 
					shelf.getDailyPrice(),
					shelf.isEmpty() ? -1 : shelf.getProduct().getId());

			DTOArray.add(DTO);
		}
		return DTOArray;
	}
}