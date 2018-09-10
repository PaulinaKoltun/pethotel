package org.pethotel.HeavenForPets.domein;

import lombok.Data;

import java.util.Date;

public @Data class FoodDetails {
    private Food food;
    private int deliveryAmount;
    private Date deliveryDate;
}
