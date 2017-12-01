package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.repository.FoodRepository;
import org.pethotel.HeavenForPets.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService{

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public void saveFood(Food food) {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName(food.getName());
        foodEntity.setFoodType(food.getFoodType());
        foodEntity.setAmount(food.getAmount());
        foodEntity.setPetType(food.getPetType());
        foodEntity.setTaste(food.getTaste());
        foodRepository.save(foodEntity);
    }
}
