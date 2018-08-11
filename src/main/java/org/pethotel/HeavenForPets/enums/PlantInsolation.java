package org.pethotel.HeavenForPets.enums;

public enum PlantInsolation {
    FULL("f"),
    SHADOW("s"),
    MEDIUM("m"),
    DUSK("d"),
    NONE("n"),
    ;

    private final String shortType;

    PlantInsolation(String shortType) {
        this.shortType = shortType;
    }

    public String getShortType() {
        return shortType;
    }

    @Override
    public String toString() {
        return "PlantInsolation{" +
                "shortType='" + shortType + '\'' +
                '}';
    }
}
