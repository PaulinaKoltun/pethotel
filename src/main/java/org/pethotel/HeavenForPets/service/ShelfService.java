package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.enums.PlantInsolation;
import org.pethotel.HeavenForPets.exceptions.TemperatureWrongRangeException;

import java.util.List;

public interface ShelfService {

    void saveShelves(List<Shelf> shelves, int id);

    List<Shelf> getShelvesFromRoom(int id);

    List<Shelf> getFreeShelvesForPlant(int id) throws TemperatureWrongRangeException;

    ShelfEntity findShelfById(long id);
}
