package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.springframework.stereotype.Service;

@Service
public class ShelfMapImpl implements ShelfMap {
    @Override
    public ShelfEntity map(Shelf shelf) {
        ShelfEntity shelfEntity = new ShelfEntity();
        shelfEntity.setNumber(shelf.getNumber());
        shelfEntity.setFree(shelf.isFree());
        return shelfEntity;
    }

    @Override
    public Shelf map(ShelfEntity shelfEntity) {
        Shelf shelf = new Shelf();
        shelf.setFree(shelfEntity.isFree());
        shelf.setNumber(shelfEntity.getNumber());
        return shelf;
    }
}
