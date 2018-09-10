package org.pethotel.HeavenForPets.domein.Rooms;

import lombok.Data;
import org.pethotel.HeavenForPets.enums.PetType;

public @Data class PetRoom extends Room {
    private PetType petType;

}
