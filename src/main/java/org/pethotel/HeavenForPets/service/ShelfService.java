package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.ShelfEntity;

public interface ShelfService {

    ShelfEntity map(Shelf shelf);
}
