package org.pethotel.HeavenForPets.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Paulina on 2017-10-07.
 */
public enum PetType {
    BIRD(1,"b"),
    FISH(2,"f"),
    MAMMAL(3,"m"),
    ;

    private final int numberType;
    private final String shortType;

    PetType(int numberType, String shortType) {
        this.numberType = numberType;
        this.shortType = shortType;
    }

    public int getNumberType() {
        return numberType;
    }

    public String getShortType() {
        return shortType;
    }

    @Override
    public String toString() {
        return "PetType{" +
                "numberType=" + numberType +
                ", shortType='" + shortType + '\'' +
                '}';
    }

    @JsonCreator
    public static PetType create (String value) {
        for(PetType v : values()) {
            if(value.toLowerCase().equals(v.getShortType())) {
                return v;
            }
        }
        return PetType.MAMMAL;
    }
}
