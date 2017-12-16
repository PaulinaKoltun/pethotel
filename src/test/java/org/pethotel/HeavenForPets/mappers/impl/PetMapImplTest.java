package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.FoodMap;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;

import java.util.Date;

import static org.junit.Assert.*;
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

        PetEntity petEntity = petMap.map(pet);
        assertEquals(pet.getName(), petEntity.getName());


    }

    @Test
    public void shouldCheckIfCommentIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setComment("Je wszystko");

        PetEntity petEntity = petMap.map(pet);
        assertEquals(pet.getComment(), petEntity.getComment());
    }

    @Test
    public void shouldCheckIfPetTypeIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setPetType(PetType.MAMMAL);

        PetEntity petEntity = petMap.map(pet);
        assertEquals(pet.getPetType(), petEntity.getPetType());
    }

    @Test
    public void shouldCheckIfDateInIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setDateIn(new Date(12,12,2017));

        PetEntity petEntity = petMap.map(pet);
        assertEquals(pet.getDateIn(), petEntity.getDateIn());
    }

    @Test
    public void shouldCheckIfDateOutIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setDateOut(new Date(13,12,2017));

        PetEntity petEntity = petMap.map(pet);
        assertEquals(pet.getDateOut(), petEntity.getDateOut());
    }

    @Test
    public void shouldCheckIfBreakfastIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        Food food = new Food();
        pet.setBreakfast(food);

        FoodEntity foodEntity = new FoodEntity();

        Mockito.when(foodMap.map(pet.getBreakfast())).thenReturn(foodEntity);

        PetEntity petEntity = petMap.map(pet);
        assertEquals(pet.getBreakfast().getFoodType(), petEntity.getBreakfast().getFoodType());
    }

    @Test
    public void shouldCheckIfDinnerIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        Food food = new Food();
        pet.setDinner(food);

        FoodEntity foodEntity = new FoodEntity();

        Mockito.when(foodMap.map(pet.getDinner())).thenReturn(foodEntity);

        PetEntity petEntity = petMap.map(pet);
        assertEquals(pet.getDinner().getName(), petEntity.getDinner().getName());
    }

    @Test
    public void shouldCheckIfSupperIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        Food food = new Food();
        pet.setSupper(food);

        FoodEntity foodEntity = new FoodEntity();

        Mockito.when(foodMap.map(pet.getSupper())).thenReturn(foodEntity);

        PetEntity petEntity = petMap.map(pet);
        assertEquals(pet.getSupper().getAmount(), petEntity.getSupper().getAmount());
    }

    @Test
    public void shouldCheckIfRoomIsCorrectlyMappedToPetEntity(){
        Pet pet = new Pet();
        pet.setPetType(PetType.MAMMAL);
        // pet.setRoomNumber(2);

        Room room = new Room();
        room.setRoomNumber(2);
        pet.setRoomNumber(room.getRoomNumber());
        System.out.println(pet);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPetType(PetType.MAMMAL);
        roomRepository.save(roomEntity);

        //    roomEntity.setRoomNumber(2);

        Mockito.when(roomRepository.getRoomByNumber(pet.getRoomNumber()))
              .thenReturn(roomEntity);

        PetEntity petEntity = petMap.map(pet);
        System.out.println(petEntity);
        assertEquals(pet.getRoomNumber(), petEntity.getRoomEntity().getRoomNumber());


    }
}