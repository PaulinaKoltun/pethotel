package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.ShelfEntity;

import java.util.List;

public interface ShelfService {

    void saveShelves(List<Shelf> shelves, int id);
    List<Shelf> getShelvesFromRoom(int id);
    List<Shelf> getFreeShelves(int id);
}
