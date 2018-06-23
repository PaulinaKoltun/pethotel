package org.pethotel.HeavenForPets.service.impl;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.FoodMap;
import org.pethotel.HeavenForPets.repository.FoodRepository;
import org.pethotel.HeavenForPets.service.FoodService;
import org.pethotel.HeavenForPets.utils.chain.MainGeneratorChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodServiceImpl implements FoodService{

    private static final Logger LOGGER = LogManager.getLogger(FoodServiceImpl.class);

    private FoodMap foodMap;
    private FoodRepository foodRepository;
    private MainGeneratorChain mainGeneratorChain;

    @Autowired
    private FoodServiceImpl(FoodMap foodMap,
                            FoodRepository foodRepository,
                            MainGeneratorChain mainGeneratorChain){
        this.foodMap = foodMap;
        this.foodRepository = foodRepository;
        this.mainGeneratorChain = mainGeneratorChain;
    }

    @Override
    public void saveFood(List<Food> foodlist) {
        for (Food food : foodlist) {
            FoodEntity foodEntity = foodRepository.getFoodWeAlreadyHave(food.getName(),
                    food.getFoodType(), food.getPetType(), food.getTaste());
            if (foodEntity == null) {
                FoodEntity newFoodEntity = foodMap.map(food);
                foodRepository.save(newFoodEntity);
            }
            else {
                updateFood(food, foodEntity);
                foodRepository.save(foodEntity);
            }
        }
    }

    private void updateFood(Food food, FoodEntity foodEntity) {
        foodEntity.setAmount(foodEntity.getAmount() + food.getAmount());
        foodEntity.setPrice(food.getPrice());
        foodEntity.setDeliveryAmount(food.getAmount());
        foodEntity.setDeliveryDate(new Date());
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
    public FoodDetails getFoodDetailsById(Integer id) {
        FoodEntity foodEntity = foodRepository.findOne(Long.valueOf(id));
        FoodDetails foodDetails = new FoodDetails();

        return prepareFoodDetails(foodEntity, foodDetails);
    }

    private FoodDetails prepareFoodDetails(FoodEntity foodEntity, FoodDetails foodDetails) {
        Food food = foodMap.map(foodEntity);

        foodDetails.setFood(food);
        foodDetails.setDeliveryAmount(foodEntity.getDeliveryAmount());
        foodDetails.setDeliveryDate(foodEntity.getDeliveryDate());
        return foodDetails;
    }

    public void getFile(HttpServletRequest request,
                        HttpServletResponse response,
                        Integer id,
                        String file) {
        FoodDetails foodDetails = getFoodDetailsById(id);

        mainGeneratorChain.makeResponse(request,response, file, foodDetails);
    }

    @Override
    public FoodEntity getFoodById(long id) {
        return foodRepository.findOne((long) id);
    }
}
