package org.pethotel.HeavenForPets.domein.Rooms;

import org.pethotel.HeavenForPets.domein.Shelf;

import java.util.List;

public class PlantRoom extends Room {
    List<Shelf> shelves;

    public List<Shelf> getShelves() {
        return shelves;
    }

    public void setShelves(List<Shelf> shelves) {
        this.shelves = shelves;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PlantRoom plantRoom = (PlantRoom) o;

        return shelves != null ? shelves.equals(plantRoom.shelves) : plantRoom.shelves == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (shelves != null ? shelves.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PlantRoom{" +
                "shelves=" + shelves +
                '}';
    }
}
