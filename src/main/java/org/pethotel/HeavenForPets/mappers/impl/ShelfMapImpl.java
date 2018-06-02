package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ShelfEntity> mapToEntity(List<Shelf> shelves) {
        List<ShelfEntity> shelfEntities = new ArrayList<>();
        for (Shelf shelf: shelves) {
            ShelfEntity shelfEntity = map(shelf);
            shelfEntities.add(shelfEntity);
        }
        return shelfEntities;
    }

    @Override
    public List<Shelf> mapToDto(List<ShelfEntity> shelfEntities) {
        List<Shelf> shelves = new ArrayList<>();
        for (ShelfEntity shelfEntity : shelfEntities) {
            Shelf shelf = map(shelfEntity);
            shelves.add(shelf);
        }
        return shelves;
    }
}
