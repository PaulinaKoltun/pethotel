package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Pet.Animal;
import org.pethotel.HeavenForPets.domein.Pet.Pet;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.service.FoodService;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class PetMapImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private FoodService foodService;

    @InjectMocks
    private PetMapImpl petMap;

    @Test
    public void shouldCheckIfNameIsCorrectlyMappedToPetEntity(){
        Pet pet = new Animal();
        pet.setName("Azor");

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getName(), petEntity.getName());

    }

    @Test
    public void shouldCheckIfCommentIsCorrectlyMappedToPetEntity(){
        Pet pet = new Animal();
        pet.setComment("Je wszystko");

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getComment(), petEntity.getComment());
    }

    @Test
    public void shouldCheckIfPetTypeIsCorrectlyMappedToPetEntity(){
        Animal pet = new Animal();
        pet.setPetType(PetType.MAMMAL);

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getPetType(), petEntity.getPetType());
    }

    @Test
    public void shouldCheckIfDateInIsCorrectlyMappedToPetEntity(){
        Pet pet = new Animal();
        pet.setDateIn(new Date(12,12,2017));

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getDateIn(), petEntity.getDateIn());
    }

    @Test
    public void shouldCheckIfDateOutIsCorrectlyMappedToPetEntity(){
        Pet pet = new Animal();
        pet.setDateOut(new Date(13,12,2017));

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getDateOut(), petEntity.getDateOut());
    }

    @Test
    public void shouldCheckIfBreakfastIsCorrectlyMappedToPetEntity(){
        Animal pet = new Animal();
        pet.setBreakfast(1);

        FoodEntity foodEntity = new FoodEntity();

        Mockito.when(foodService.getFoodById(pet.getBreakfast())).thenReturn(foodEntity);

        PetEntity petEntity = petMap.map(pet, null);
        assertNotNull(petEntity.getBreakfast());
    }

    @Test
    public void shouldCheckIfDinnerIsCorrectlyMappedToPetEntity(){
        Animal pet = new Animal();
        pet.setDinner(1);

        FoodEntity foodEntity = new FoodEntity();

        Mockito.when(foodService.getFoodById(pet.getDinner())).thenReturn(foodEntity);

        PetEntity petEntity = petMap.map(pet, null);
        assertNotNull(petEntity.getDinner());
    }

    @Test
    public void shouldCheckIfSupperIsCorrectlyMappedToPetEntity(){
        Animal pet = new Animal();
        pet.setSupper(1);

        Mockito.when(foodService.getFoodById(pet.getSupper())).thenReturn(new FoodEntity());

        PetEntity petEntity = petMap.map(pet, null);
        assertNotNull(petEntity.getSupper().getAmount());
    }

    @Test
    public void shouldCheckIfRoomIsCorrectlyMappedToPetEntity(){
        Animal pet = new Animal();
        pet.setPetType(PetType.MAMMAL);
        pet.setRoomNumber(2);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPetType(PetType.MAMMAL);
        roomEntity.setRoomNumber(2);

        Mockito.when(roomRepository.getRoomByNumber(pet.getRoomNumber()))
              .thenReturn(roomEntity);

        PetEntity petEntity = petMap.map(pet, roomEntity);

        assertEquals(pet.getRoomNumber(), petEntity.getRoomEntity().getRoomNumber());
    }
}