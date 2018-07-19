package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Pet.Animal;
import org.pethotel.HeavenForPets.domein.Pet.Pet;
import org.pethotel.HeavenForPets.domein.Pet.Plant;
import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.pethotel.HeavenForPets.service.FoodService;
import org.pethotel.HeavenForPets.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paulina on 2017-10-25.
 */
@Service
public class PetMapImpl implements PetMap {

    @Autowired
    private FoodService foodService;

    @Autowired
    private ShelfMap shelfMap;

    @Autowired
    private ShelfService shelfService;


    @Override
    public PetEntity map(Pet pet, RoomEntity roomByNumber) {
        PetEntity petEntity = new PetEntity();

        if (pet instanceof Animal) {
            petEntity.setPetType(((Animal) pet).getPetType());
            petEntity.setBreakfast(foodService.getFoodById(((Animal) pet).getBreakfast()));
            petEntity.setDinner(foodService.getFoodById(((Animal) pet).getDinner()));
            petEntity.setSupper(foodService.getFoodById(((Animal) pet).getSupper()));
        } else {
            long shelfId = ((Plant) pet).getShelf();
            ShelfEntity shelf = shelfService.findShelfById(shelfId);
            petEntity.setShelfEntity(shelf);
            petEntity.setToWater(((Plant) pet).getToWater());
        }

        petEntity.setName(pet.getName());
        petEntity.setComment(pet.getComment());
        petEntity.setDateIn(pet.getDateIn());
        petEntity.setDateOut(pet.getDateOut());
        petEntity.setRoomEntity(roomByNumber);

        return petEntity;
    }

    @Override
    public PetEntity map(Pet pet, PetEntity petEntity, RoomEntity roomByNumber) {
        if (pet instanceof Animal) {
            petEntity.setBreakfast(foodService.getFoodById(((Animal) pet).getBreakfast()));
            petEntity.setDinner(foodService.getFoodById(((Animal) pet).getDinner()));
            petEntity.setSupper(foodService.getFoodById(((Animal) pet).getSupper()));
        } else {
            long shelfId = ((Plant) pet).getShelf();
            ShelfEntity shelf = shelfService.findShelfById(shelfId);
            petEntity.setShelfEntity(shelf);
            petEntity.setToWater(((Plant) pet).getToWater());
        }

        petEntity.setComment(pet.getComment());
        petEntity.setDateIn(pet.getDateIn());
        petEntity.setDateOut(pet.getDateOut());
        petEntity.setRoomEntity(roomByNumber);

        return petEntity;
    }

    @Override
    public Pet map(PetEntity e) {
        Pet pet;

        if (e.getPetType() != null) {
            pet = new Animal();
            ((Animal) pet).setPetType(e.getPetType());
            ((Animal) pet).setDinner((int) e.getDinner().getId());
            ((Animal) pet).setSupper((int) e.getSupper().getId());
            ((Animal) pet).setBreakfast((int) e.getBreakfast().getId());
        } else {
            pet = new Plant();
            ((Plant) pet).setShelf(e.getShelfEntity().getId());
            ((Plant) pet).setToWater(e.getToWater());
        }

        pet.setName(e.getName());
        pet.setComment(e.getComment());
        pet.setDateIn(e.getDateIn());
        pet.setDateOut(e.getDateOut());

        RoomEntity roomEntity = e.getRoomEntity();
        pet.setRoomNumber(roomEntity != null ? roomEntity.getRoomNumber() : 0);

        return pet;
    }
}
