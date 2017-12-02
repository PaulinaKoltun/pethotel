package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.mappers.FoodMap;
import org.springframework.stereotype.Service;

@Service
public class FoodMapImpl implements FoodMap {

    @Override
    public FoodEntity map(Food food) {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName(food.getName());
        foodEntity.setPrice(food.getPrice());
        foodEntity.setPetType(food.getPetType());
        foodEntity.setFoodType(food.getFoodType());
        foodEntity.setTaste(food.getTaste());
        foodEntity.setAmount(food.getAmount());
        return foodEntity;
    }
}
