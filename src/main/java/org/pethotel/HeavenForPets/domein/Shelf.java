package org.pethotel.HeavenForPets.domein;

import lombok.Data;
import org.pethotel.HeavenForPets.enums.PlantInsolation;

public @Data class Shelf {
    private int number;
    private boolean free;
    private PlantInsolation plantInsolation;

}


