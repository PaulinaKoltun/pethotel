package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.domein.FoodDetails;

import java.util.List;

public interface FoodService {
    void saveFood(List<Food> foodlist);
    List<Food> getFoodByPet(String petType);
    FoodDetails getFoodById(Integer id);
}
