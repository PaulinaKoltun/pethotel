package org.pethotel.HeavenForPets.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Created by Paulina on 2017-10-07.
 */
public enum OwnerCategory {
    VIP(1,"V", 20),
    GOLD(2,"G", 15),
    SILVER(3,"S", 10),
    NORMAL(4,"N", 0),
    NO_SERVICE(5,"NS", 0),
    ;

    private final int numberCategory;
    private final String shortCategory;
    private final int discount;

    OwnerCategory(int numberCategory, String shortCategory, int discount) {
        this.numberCategory = numberCategory;
        this.shortCategory = shortCategory;
        this.discount = discount;
    }


    public int getNumberCategory() {
        return numberCategory;
    }

    public String getShortCategory() {
        return shortCategory;
    }

    public int getDiscount() {
        return discount;
    }

    @Override
    public String toString() {
        return "OwnerCategory{" +
                "numberCategory=" + numberCategory +
                ", shortCategory='" + shortCategory + '\'' +
                ", discount=" + discount +
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
