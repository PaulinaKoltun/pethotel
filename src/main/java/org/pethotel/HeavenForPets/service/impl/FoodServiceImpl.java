package org.pethotel.HeavenForPets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pethotel.HeavenForPets.controllers.FoodController;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.repository.FoodRepository;
import org.pethotel.HeavenForPets.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{

    private static final Logger LOGGER = LogManager.getLogger(FoodServiceImpl.class);

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public void saveFood(List<Food> foodlist) {
        for (Food food : foodlist) {
            FoodEntity foodEntity = new FoodEntity();
            foodEntity.setName(food.getName());
            foodEntity.setFoodType(food.getFoodType());
            foodEntity.setAmount(food.getAmount());
            foodEntity.setPetType(food.getPetType());
            foodEntity.setTaste(food.getTaste());
            foodEntity.setPrice(food.getPrice());
            foodRepository.save(foodEntity);
        }
    }

    @Override
    public List<Food> getFoodByPet(String petType) {
        PetType petTypeEnum = PetType.valueOf(petType);
        LOGGER.info("Szukam dla enuma: {}", petTypeEnum);
        List<FoodEntity> foodEntityList = foodRepository.getFoodByPetType(petTypeEnum);
        List<Food> foodList = new ArrayList<>();
        for (FoodEntity foodEntity : foodEntityList) {
            Food food = new Food();
            food.setName(foodEntity.getName());
            food.setAmount(foodEntity.getAmount());
            food.setFoodType(foodEntity.getFoodType());
            food.setPetType(foodEntity.getPetType());
            food.setTaste(foodEntity.getTaste());
            food.setPrice(foodEntity.getPrice());
            foodList.add(food);
        }
        return foodList;
    }
}