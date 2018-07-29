package org.pethotel.HeavenForPets.domein.Rooms;

import org.pethotel.HeavenForPets.domein.Shelf;

import java.util.List;

public class PlantRoom extends Room {
    private List<Shelf> shelves;
    private int temperature;

    public List<Shelf> getPlantShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PlantRoom plantRoom = (PlantRoom) o;

        if (temperature != plantRoom.temperature) return false;
        return shelves != null ? shelves.equals(plantRoom.shelves) : plantRoom.shelves == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (shelves != null ? shelves.hashCode() : 0);
        result = 31 * result + temperature;
        return result;
    }

    @Override
    public String toString() {
        return "PlantRoom{" +
                "shelves=" + shelves +
                ", temperature=" + temperature +
                '}';
    }
}
