package org.pethotel.HeavenForPets.domein.Rooms;

import org.pethotel.HeavenForPets.enums.PetType;

public class PetRoom extends Room {
    private PetType petType;

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PetRoom petRoom = (PetRoom) o;

        return petType == petRoom.petType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return super.toString()+"PetRoom{" +
                "petType=" + petType +
                '}';
    }
}
