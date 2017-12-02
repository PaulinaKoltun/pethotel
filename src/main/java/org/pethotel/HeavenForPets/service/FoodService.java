package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Food;

import java.util.List;

public interface FoodService {
    void saveFood(List<Food> foodlist);
}
