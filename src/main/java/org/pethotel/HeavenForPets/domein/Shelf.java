package org.pethotel.HeavenForPets.domein;

import org.pethotel.HeavenForPets.enums.PlantInsolation;

public class Shelf {
    private int number;
    private boolean free;
    private PlantInsolation plantInsolation;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public PlantInsolation getPlantInsolation() {
        return plantInsolation;
    }

    public void setPlantInsolation(PlantInsolation plantInsolation) {
        this.plantInsolation = plantInsolation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shelf shelf = (Shelf) o;

        if (number != shelf.number) return false;
        if (free != shelf.free) return false;
        return plantInsolation == shelf.plantInsolation;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (free ? 1 : 0);
        result = 31 * result + (plantInsolation != null ? plantInsolation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "number=" + number +
                ", free=" + free +
                ", plantInsolation=" + plantInsolation +
                '}';
    }
}


