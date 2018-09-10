package org.pethotel.HeavenForPets.domein.Pet;

import lombok.Data;
import org.pethotel.HeavenForPets.enums.PlantInsolation;

public @Data class Plant extends Pet{
    private long shelf;
    private int toWater;
    private int minTemperature;
    private int maxTemperature;
    private PlantInsolation plantInsolation;
}
