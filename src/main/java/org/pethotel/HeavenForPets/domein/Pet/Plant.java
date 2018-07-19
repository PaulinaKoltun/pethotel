package org.pethotel.HeavenForPets.domein.Pet;

import org.pethotel.HeavenForPets.domein.Shelf;

public class Plant extends Pet{
    private long shelf;
    private int toWater;

    public long getShelf() {
        return shelf;
    }

    public void setShelf(long shelf) {
        this.shelf = shelf;
    }

    public int getToWater() {
        return toWater;
    }

    public void setToWater(int toWater) {
        this.toWater = toWater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Plant plant = (Plant) o;

        if (shelf != plant.shelf) return false;
        return toWater == plant.toWater;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (shelf ^ (shelf >>> 32));
        result = 31 * result + toWater;
        return result;
    }

    @Override

    public String toString() {
        return "Plant{" +
                "shelf=" + shelf +
                ", toWater=" + toWater +
                '}';
    }
}
