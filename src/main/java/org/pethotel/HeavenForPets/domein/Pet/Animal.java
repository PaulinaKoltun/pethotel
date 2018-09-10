package org.pethotel.HeavenForPets.domein.Pet;

import lombok.Data;
import org.pethotel.HeavenForPets.enums.PetType;


public @Data class Animal extends Pet {
    private PetType petType;
    private long breakfast;
    private long dinner;
    private long supper;
}
