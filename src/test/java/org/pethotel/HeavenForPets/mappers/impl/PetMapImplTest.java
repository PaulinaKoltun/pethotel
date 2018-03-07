package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.FoodMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;

import java.util.Date;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class PetMapImplTest {

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private FoodMap foodMap;

    @InjectMocks
    private PetMapImpl petMap;

    @Test
    public void shouldCheckIfNameIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setName("Azor");

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getName(), petEntity.getName());

    }

    @Test
    public void shouldCheckIfCommentIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setComment("Je wszystko");

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getComment(), petEntity.getComment());
    }

    @Test
    public void shouldCheckIfPetTypeIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setPetType(PetType.MAMMAL);

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getPetType(), petEntity.getPetType());
    }

    @Test
    public void shouldCheckIfDateInIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setDateIn(new Date(12,12,2017));

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getDateIn(), petEntity.getDateIn());
    }

    @Test
    public void shouldCheckIfDateOutIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setDateOut(new Date(13,12,2017));

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getDateOut(), petEntity.getDateOut());
    }

    @Test
    public void shouldCheckIfBreakfastIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        Food food = new Food();
        pet.setBreakfast(food);

        FoodEntity foodEntity = new FoodEntity();

        Mockito.when(foodMap.map(pet.getBreakfast())).thenReturn(foodEntity);

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getBreakfast().getFoodType(), petEntity.getBreakfast().getFoodType());
    }

    @Test
    public void shouldCheckIfDinnerIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        Food food = new Food();
        pet.setDinner(food);

        FoodEntity foodEntity = new FoodEntity();

        Mockito.when(foodMap.map(pet.getDinner())).thenReturn(foodEntity);

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getDinner().getName(), petEntity.getDinner().getName());
    }

    @Test
    public void shouldCheckIfSupperIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        Food food = new Food();
        pet.setSupper(food);

        Mockito.when(foodMap.map(pet.getSupper())).thenReturn(new FoodEntity());

        PetEntity petEntity = petMap.map(pet, null);
        assertEquals(pet.getSupper().getAmount(), petEntity.getSupper().getAmount());
    }

    @Test
    public void shouldCheckIfRoomIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
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