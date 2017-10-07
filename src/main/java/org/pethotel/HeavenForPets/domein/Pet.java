package org.pethotel.HeavenForPets.domein;

import org.pethotel.HeavenForPets.enums.PetType;

import java.io.Serializable;

/**
 * Created by Paulina on 2017-09-27.
 */
public class Pet implements Serializable {
    private String name;
    private String comment;
    private PetType petType;

    public Pet(String name, String comment, PetType petType) {
        this.name = name;
        this.comment = comment;
        this.petType = petType;
    }

    public Pet(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", petType=" + petType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (name != null ? !name.equals(pet.name) : pet.name != null) return false;
        if (comment != null ? !comment.equals(pet.comment) : pet.comment != null) return false;
        return petType == pet.petType;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        return result;
    }
}
