package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.PetEntity;

/**
 * Created by Paulina on 2017-10-25.
 */
public interface PetMap {
    PetEntity map(Pet pet);
}
