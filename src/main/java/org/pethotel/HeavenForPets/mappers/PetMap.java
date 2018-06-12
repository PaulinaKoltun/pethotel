package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Pet.Pet;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;

/**
 * Created by Paulina on 2017-10-25.
 */
public interface PetMap {
    PetEntity map(Pet pet, RoomEntity roomByNumber);
    PetEntity map(Pet pet, PetEntity petEntity, RoomEntity roomByNumber);
    Pet map(PetEntity e);
}
