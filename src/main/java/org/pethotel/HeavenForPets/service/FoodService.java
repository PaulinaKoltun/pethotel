package org.pethotel.HeavenForPets.service;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.entity.FoodEntity;


import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FoodService {
    void saveFood(List<Food> foodlist);
    List<Food> getFoodByPet(String petType);
    FoodDetails getFoodDetailsById(Integer id);
    void getFile(HttpServletRequest request,
                 HttpServletResponse response,
                 Integer id,
                 String file);

    FoodEntity getFoodById(long id);
}
