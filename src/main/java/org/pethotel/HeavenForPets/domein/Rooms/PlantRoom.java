package org.pethotel.HeavenForPets.domein.Rooms;

import lombok.Data;
import org.pethotel.HeavenForPets.domein.Shelf;

import java.util.List;

public @Data class PlantRoom extends Room {
    private List<Shelf> shelves;
    private int temperature;

}
