package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.ShelfEntity;

import java.util.List;

public interface ShelfMap {
    ShelfEntity map(Shelf shelf);

    Shelf map(ShelfEntity shelfEntity);

    List<ShelfEntity> mapToEntity(List<Shelf> shelves);

    List<Shelf> mapToDto(List<ShelfEntity> shelfEntities);
}
