package org.pethotel.HeavenForPets.domein;

import lombok.Data;
import org.pethotel.HeavenForPets.enums.FoodType;
import org.pethotel.HeavenForPets.enums.PetType;

import java.math.BigDecimal;

public @Data
class Food {
    private String name;
    private FoodType foodType;
    private int amount;
    private PetType petType;
    private String taste;
    private BigDecimal price;
    private long id;
}
