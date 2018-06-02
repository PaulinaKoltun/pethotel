package org.pethotel.HeavenForPets.enums;

public enum RoomType {
    PLANT(1, "plant"),
    PET(2, "pet"),
    ;

    private final int numberType;
    private final String stringType;

    RoomType(int numberType, String stringType) {
        this.numberType = numberType;
        this.stringType = stringType;
    }

    public int getNumberType(){
        return numberType;
    }

    public String getStringType(){
        return stringType;
    }

    @Override
    public String toString() {
        return "RoomType{" +
                "numberType=" + numberType +
                ", stringType='" + stringType + '\'' +
                '}';
    }
}
