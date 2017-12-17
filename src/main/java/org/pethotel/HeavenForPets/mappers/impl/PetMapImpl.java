package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.mappers.FoodMap;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paulina on 2017-10-25.
 */
@Service
public class PetMapImpl implements PetMap {

    @Autowired
    private FoodMap foodMap;

    @Override
    public PetEntity map(Pet pet, RoomEntity roomByNumber) {
        PetEntity petEntity = new PetEntity();
        petEntity.setName(pet.getName());
        petEntity.setComment(pet.getComment());
        petEntity.setPetType(pet.getPetType());
        petEntity.setDateIn(pet.getDateIn());
        petEntity.setDateOut(pet.getDateOut());
        petEntity.setBreakfast(foodMap.map(pet.getBreakfast()));
        petEntity.setDinner(foodMap.map(pet.getDinner()));
        petEntity.setSupper(foodMap.map(pet.getSupper()));
        petEntity.setRoomEntity(roomByNumber);
        return petEntity;
    }
}
