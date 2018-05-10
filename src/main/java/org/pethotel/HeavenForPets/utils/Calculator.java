package org.pethotel.HeavenForPets.utils;

import org.pethotel.HeavenForPets.entity.PetEntity;

public class Calculator {

    public static long getDaysOfVisit(PetEntity petEntity) {
        long difference = Math.abs(petEntity.getDateOut().getTime() - petEntity.getDateIn().getTime());
        return  difference / (24 * 60 * 60 * 1000);
    }
}
