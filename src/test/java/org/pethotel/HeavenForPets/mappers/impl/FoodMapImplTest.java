package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Test;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.enums.FoodType;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.FoodMap;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class FoodMapImplTest {

    FoodMap foodMap = new FoodMapImpl();

    @Test
    public void shouldCheckIfNameIsCorrectlyMappedInFoodEntity(){
        Food food = new Food();
        food.setName("Karma");

        FoodEntity foodEntity = foodMap.map(food);
        assertEquals(foodEntity.getName(), food.getName());
    }


    @Test
    public void shouldCheckIfPriceIsCorrectlyMappedInFoodEntity(){
        Food food = new Food();
        food.setPrice(new BigDecimal(10));

        FoodEntity foodEntity = foodMap.map(food);
        assertEquals(foodEntity.getPrice(), food.getPrice());
    }

    @Test
    public void shouldCheckIfTasteIsCorrectlyMappedInFoodEntity(){
        Food food = new Food();
        food.setTaste("Kurczak");

        FoodEntity foodEntity = foodMap.map(food);
        assertEquals(foodEntity.getTaste(), food.getTaste());
    }


    @Test
    public void shouldCheckIfPetTypeIsCorrectlyMappedInFoodEntity(){
        Food food = new Food();
        food.setPetType(PetType.MAMMAL);

        FoodEntity foodEntity = foodMap.map(food);
        assertEquals(foodEntity.getPetType(), food.getPetType());
    }

    @Test
    public void shouldCheckIfFoodTypeIsCorrectlyMappedInFoodEntity(){
        Food food = new Food();
        food.setFoodType(FoodType.DRY);

        FoodEntity foodEntity = foodMap.map(food);
        assertEquals(foodEntity.getFoodType(), food.getFoodType());
    }

    @Test
    public void shouldCheckIfAmountIsCorrectlyMappedInFoodEntity(){
        Food food = new Food();
        food.setAmount(2);

        FoodEntity foodEntity = foodMap.map(food);
        assertEquals(foodEntity.getAmount(), food.getAmount());
    }

    @Test
    public void shouldCheckIfNameIsCorrectlyMappedInFood(){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName("Karma");

        Food food = foodMap.map(foodEntity);
        assertEquals(foodEntity.getName(), food.getName());
    }


    @Test
    public void shouldCheckIfPriceIsCorrectlyMappedInFood(){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setPrice(new BigDecimal(10));

        Food food = foodMap.map(foodEntity);
        assertEquals(foodEntity.getPrice(), food.getPrice());
    }

    @Test
    public void shouldCheckIfTasteIsCorrectlyMappedInFood(){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setTaste("Kurczak");

        Food food = foodMap.map(foodEntity);
        assertEquals(foodEntity.getTaste(), food.getTaste());
    }


    @Test
    public void shouldCheckIfPetTypeIsCorrectlyMappedInFood(){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setPetType(PetType.MAMMAL);

        Food food = foodMap.map(foodEntity);
        assertEquals(foodEntity.getPetType(), food.getPetType());
    }

    @Test
    public void shouldCheckIfFoodTypeIsCorrectlyMappedInFood(){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setFoodType(FoodType.DRY);

        Food food = foodMap.map(foodEntity);
        assertEquals(foodEntity.getFoodType(), food.getFoodType());
    }

    @Test
    public void shouldCheckIfAmountIsCorrectlyMappedInFood(){
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setAmount(2);
        System.out.println(foodEntity);

        Food food = foodMap.map(foodEntity);
        assertEquals(foodEntity.getAmount(), food.getAmount());
    }
}