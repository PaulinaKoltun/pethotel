package org.pethotel.HeavenForPets.service.impl;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.FoodMap;
import org.pethotel.HeavenForPets.repository.FoodRepository;
import org.pethotel.HeavenForPets.service.FoodService;
import org.pethotel.HeavenForPets.utils.chain.MainGeneratorChain;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class FoodServiceImplTest {

    @Mock
    private FoodMap foodMap;

    @Mock
    private FoodRepository foodRepository;

    @Mock
    private MainGeneratorChain mainGeneratorChain;

    @InjectMocks
    private FoodServiceImpl foodService;

    @Test
    public void saveFoodWhenNull() throws Exception{
        List<Food> foodlist = new ArrayList<>();
        Food food = new Food();
        foodlist.add(food);
        FoodEntity foodEntity = null;
        FoodEntity newFoodEntity = new FoodEntity();

        when(foodRepository.getFoodWeAlreadyHave(food.getName(),
                food.getFoodType(), food.getPetType(), food.getTaste())).thenReturn(foodEntity);
        when(foodMap.map(food)).thenReturn(newFoodEntity);
        when(foodRepository.save(newFoodEntity)).thenReturn(newFoodEntity);

        foodService.saveFood(foodlist);

        verify(foodRepository, times(1)).save(newFoodEntity);
    }

    @Test
    public void saveFoodWhenNotNull() throws Exception{
        List<Food> foodlist = new ArrayList<>();
        Food food = new Food();
        food.setName("Azor");
        foodlist.add(food);
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName("Azor");

        when(foodRepository.getFoodWeAlreadyHave(food.getName(),
                food.getFoodType(), food.getPetType(), food.getTaste())).thenReturn(foodEntity);
        when(foodRepository.save(foodEntity)).thenReturn(foodEntity);

        foodService.saveFood(foodlist);

        verify(foodRepository, times(1)).save(foodEntity);
    }

    @Test
    public void getFoodByPet() throws Exception{
        PetType petType = PetType.FISH;
        List<FoodEntity> foodEntityList = new ArrayList<>();
        FoodEntity foodEntity = new FoodEntity();
        foodEntityList.add(foodEntity);
        Food food = new Food();

        when(foodRepository.getFoodByPetType(petType)).thenReturn(foodEntityList);
        when(foodMap.map(foodEntity)).thenReturn(food);

        foodService.getFoodByPet("FISH");

        verify(foodMap, times(1)).map(foodEntity);
    }

    @Test
    public void getFoodDetailsById() throws Exception{
        Integer id = 2;
        FoodEntity foodEntity = new FoodEntity();

        when(foodRepository.findOne((long) id)).thenReturn(foodEntity);

        FoodDetails foodDetails = foodService.getFoodDetailsById(id);

        assertEquals(foodEntity.getDeliveryAmount(), foodDetails.getDeliveryAmount());
    }

    @Ignore
    @Test
    public void getFile(){
        Integer id = 1;
        String file = "file";
        HttpServletRequest request = null;
        HttpServletResponse response = null;

        FoodDetails foodDetails = foodService.getFoodDetailsById(id);

        mainGeneratorChain.makeResponse(request, response, file, foodDetails);

    }

    @Test
    public void getFoodById(){
        int id = 1;
        FoodEntity foodEntity = new FoodEntity();

        when(foodRepository.findOne((long) id)).thenReturn(foodEntity);

        foodService.getFoodById(id);

        verify(foodRepository, times(1)).findOne((long) id);
    }
}