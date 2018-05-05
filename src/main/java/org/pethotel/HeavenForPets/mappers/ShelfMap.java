package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.ShelfEntity;

public interface ShelfMap {
    ShelfEntity map(Shelf shelf);
    Shelf map(ShelfEntity shelfEntity);
}
