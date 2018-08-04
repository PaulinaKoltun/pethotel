package org.pethotel.HeavenForPets.domein.Pet;

public class Plant extends Pet{
    private long shelf;
    private int toWater;
    private int minTemperature;
    private int maxTemperature;

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

    public int getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public int getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Plant plant = (Plant) o;

        if (shelf != plant.shelf) return false;
        if (toWater != plant.toWater) return false;
        if (minTemperature != plant.minTemperature) return false;
        return maxTemperature == plant.maxTemperature;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) (shelf ^ (shelf >>> 32));
        result = 31 * result + toWater;
        result = 31 * result + minTemperature;
        result = 31 * result + maxTemperature;
        return result;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "shelf=" + shelf +
                ", toWater=" + toWater +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                '}';
    }
}
