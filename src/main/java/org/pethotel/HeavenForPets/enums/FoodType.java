package org.pethotel.HeavenForPets.enums;

public enum FoodType {
    CAN(1, "c"),
    DRY(2, "d"),
    ;

    private final int numbetType;
    private final String stringType;

    FoodType(int numbetType, String stringType) {
        this.numbetType = numbetType;
        this.stringType = stringType;
    }

    public int getNumbetType() {
        return numbetType;
    }

    public String getStringType() {
        return stringType;
    }

    @Override
    public String toString() {
        return "FoodType{" +
                "numbetType=" + numbetType +
                ", stringType='" + stringType + '\'' +
                '}';
    }
}
