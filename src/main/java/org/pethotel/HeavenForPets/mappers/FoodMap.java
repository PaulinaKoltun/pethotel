package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.entity.FoodEntity;

public interface FoodMap {
    FoodEntity map(Food food);
}
