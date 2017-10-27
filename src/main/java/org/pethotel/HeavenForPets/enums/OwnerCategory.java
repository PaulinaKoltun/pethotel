package org.pethotel.HeavenForPets.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Paulina on 2017-10-07.
 */
public enum OwnerCategory {
    VIP(1,"V"),
    GOLD(2,"G"),
    SILVER(3,"S"),
    NORMAL(4,"N"),
    NO_SERVICE(5,"NS"),
    ;

    private final int numberCategory;
    private final String shortCategory;

    OwnerCategory(int numberCategory, String shortCategory) {
        this.numberCategory = numberCategory;
        this.shortCategory = shortCategory;
    }

    public int getNumberCategory() {
        return numberCategory;
    }

    public String getShortCategory() {
        return shortCategory;
    }

    @Override
    public String toString() {
        return "OwnerCategory{" +
                "numberCategory=" + numberCategory +
                ", shortCategory='" + shortCategory + '\'' +
                '}';
    }

    @JsonCreator
    public static OwnerCategory create (String value) {
        for(OwnerCategory v : values()) {
            if(value.toUpperCase().equals(v.getShortCategory())) {
                return v;
            }
        }
        return OwnerCategory.NORMAL;
    }
}
