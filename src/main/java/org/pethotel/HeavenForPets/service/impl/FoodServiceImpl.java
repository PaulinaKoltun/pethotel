package org.pethotel.HeavenForPets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pethotel.HeavenForPets.controllers.FoodController;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.FoodMap;
import org.pethotel.HeavenForPets.repository.FoodRepository;
import org.pethotel.HeavenForPets.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService{

    private static final Logger LOGGER = LogManager.getLogger(FoodServiceImpl.class);

    @Autowired
    private FoodMap foodMap;

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public void saveFood(List<Food> foodlist) {

        for (Food food : foodlist) {
            FoodEntity foodEntity = foodRepository.getFoodWeAlreadyHave(food.getName(), food.getFoodType(), food.getPetType(), food.getTaste());
            if (foodEntity == null) {
                FoodEntity newFoodEntity = foodMap.map(food);
                foodRepository.save(newFoodEntity);
            }
            else {
                foodEntity.setAmount(foodEntity.getAmount() + food.getAmount());
                foodEntity.setPrice(food.getPrice());
                foodEntity.setDeliveryAmount(food.getAmount());
                foodEntity.setDeliveryDate(new Date());
                foodRepository.save(foodEntity);
            }
        }
    }

    @Override
    public List<Food> getFoodByPet(String petType) {
        PetType petTypeEnum = PetType.valueOf(petType);
        LOGGER.info("Szukam dla enuma: {}", petTypeEnum);
        List<FoodEntity> foodEntityList = foodRepository.getFoodByPetType(petTypeEnum);

        return foodEntityList.stream()
                .map(r -> foodMap.map(r))
                .collect(Collectors.toList());

    }

    @Override
    public FoodDetails getFoodById(Integer id) {
        FoodEntity foodEntity = foodRepository.findOne(Long.valueOf(id));
        FoodDetails foodDetails = new FoodDetails();
        Food food = foodMap.map(foodEntity);
        foodDetails.setFood(food);
        foodDetails.setDeliveryAmount(foodEntity.getDeliveryAmount());
        foodDetails.setDeliveryDate(foodEntity.getDeliveryDate());

        return foodDetails;
    }
}
