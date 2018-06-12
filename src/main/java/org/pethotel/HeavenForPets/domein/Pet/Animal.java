package org.pethotel.HeavenForPets.domein.Pet;

import org.pethotel.HeavenForPets.enums.PetType;


public class Animal extends Pet {
    private PetType petType;
    private int breakfast;
    private int dinner;
    private int supper;

    public int getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(int breakfast) {
        this.breakfast = breakfast;
    }

    public int getDinner() {
        return dinner;
    }

    public void setDinner(int dinner) {
        this.dinner = dinner;
    }

    public int getSupper() {
        return supper;
    }

    public void setSupper(int supper) {
        this.supper = supper;
    }

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
        result = 31 * result + breakfast;
        result = 31 * result + dinner;
        result = 31 * result + supper;
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
