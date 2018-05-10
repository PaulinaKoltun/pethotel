package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paulina on 2017-10-25.
 */
@Service
public class PetMapImpl implements PetMap {

    @Autowired
    private FoodService foodService;

    @Override
    public PetEntity map(Pet pet, RoomEntity roomByNumber) {
        PetEntity petEntity = new PetEntity();
        petEntity.setName(pet.getName());
        petEntity.setComment(pet.getComment());
        petEntity.setPetType(pet.getPetType());
        petEntity.setDateIn(pet.getDateIn());
        petEntity.setDateOut(pet.getDateOut());
        petEntity.setBreakfast(foodService.getFoodById(pet.getBreakfast()));
        petEntity.setDinner(foodService.getFoodById(pet.getDinner()));
        petEntity.setSupper(foodService.getFoodById(pet.getSupper()));
        petEntity.setRoomEntity(roomByNumber);
        return petEntity;
    }

    @Override
    public PetEntity map(Pet pet, PetEntity petEntity, RoomEntity roomByNumber) {
        petEntity.setComment(pet.getComment());
        petEntity.setDateIn(pet.getDateIn());
        petEntity.setDateOut(pet.getDateOut());
        petEntity.setBreakfast(foodService.getFoodById(pet.getBreakfast()));
        petEntity.setDinner(foodService.getFoodById(pet.getDinner()));
        petEntity.setSupper(foodService.getFoodById(pet.getSupper()));
        petEntity.setRoomEntity(roomByNumber);
        return petEntity;
    }

    @Override
    public Pet map(PetEntity e) {
        Pet pet = new Pet();
        pet.setName(e.getName());
        pet.setComment(e.getComment());
        pet.setPetType(e.getPetType());
        pet.setDateIn(e.getDateIn());
        pet.setDateOut(e.getDateOut());
        pet.setDinner((int) e.getDinner().getId());
        pet.setSupper((int) e.getSupper().getId());
        pet.setBreakfast((int) e.getBreakfast().getId());

        RoomEntity roomEntity = e.getRoomEntity();
        pet.setRoomNumber(roomEntity != null ? roomEntity.getRoomNumber() : 0);

        return pet;
    }
}
