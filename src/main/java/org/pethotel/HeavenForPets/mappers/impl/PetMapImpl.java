package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.springframework.stereotype.Service;

/**
 * Created by Paulina on 2017-10-25.
 */
@Service
public class PetMapImpl implements PetMap {

    @Override
    public PetEntity map(Pet pet) {
        PetEntity petEntity = new PetEntity();
        petEntity.setName(pet.getName());
        petEntity.setComment(pet.getComment());
        petEntity.setPetType(pet.getPetType());
        return petEntity;
    }
}
