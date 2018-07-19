package org.pethotel.HeavenForPets.domein.Pet;

import org.pethotel.HeavenForPets.enums.PetType;


public class Animal extends Pet {
    private PetType petType;
    private long breakfast;
    private long dinner;
    private long supper;

    public void setBreakfast(long breakfast) {
        this.breakfast = breakfast;
    }

    public void setDinner(long dinner) {
        this.dinner = dinner;
    }

    public void setSupper(long supper) {
        this.supper = supper;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public long getBreakfast() {
        return breakfast;
    }

    public long getDinner() {
        return dinner;
    }

    public long getSupper() {
        return supper;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Animal animal = (Animal) o;

        if (breakfast != animal.breakfast) return false;
        if (dinner != animal.dinner) return false;
        if (supper != animal.supper) return false;
        return petType == animal.petType;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + (int) (breakfast ^ (breakfast >>> 32));
        result = 31 * result + (int) (dinner ^ (dinner >>> 32));
        result = 31 * result + (int) (supper ^ (supper >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "petType=" + petType +
                ", breakfast=" + breakfast +
                ", dinner=" + dinner +
                ", supper=" + supper +
                '}';
    }
}
