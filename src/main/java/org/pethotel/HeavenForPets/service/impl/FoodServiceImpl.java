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
import org.pethotel.HeavenForPets.utils.Generator;
import org.pethotel.HeavenForPets.utils.Writer;
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
    private Generator generatorPdf;
    private Generator generatorCsv;
    private Writer writerPdf;
    private Writer writerCsv;

    @Autowired
    private FoodServiceImpl(FoodMap foodMap, FoodRepository foodRepository,
                            Generator generatorPdf, Generator generatorCsv,
                            Writer writerPdf, Writer writerCsv){
        this.foodMap = foodMap;
        this.foodRepository = foodRepository;
        this.generatorPdf = generatorPdf;
        this.generatorCsv = generatorCsv;
        this.writerPdf = writerPdf;
        this.writerCsv = writerCsv;
    }

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

//        generator.generate(foodDetails);

        return foodDetails;
    }

    public void getFile(HttpServletRequest request,
                        HttpServletResponse response,
                        Integer id,
                        String file) {
        FoodDetails foodDetails = getFoodById(id);

        if ("PDF".equals(file.toUpperCase())){
            generatorPdf.generate(foodDetails);
            writerPdf.writer(request, response, foodDetails);
        }
        else {
            generatorCsv.generate(foodDetails);
            writerCsv.writer(request, response, foodDetails);
        }
    }
}
