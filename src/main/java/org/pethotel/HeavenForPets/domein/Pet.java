package org.pethotel.HeavenForPets.domein;

import org.pethotel.HeavenForPets.enums.PetType;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Paulina on 2017-09-27.
 */
public class Pet implements Serializable {
    private String name;
    private String comment;
    private PetType petType;
    private int roomNumber;
    private Date dateIn;
    private Date dateOut;
    private Food breakfast;
    private Food dinner;
    private Food supper;

    public Pet(){}

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

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

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public Food getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(Food breakfast) {
        this.breakfast = breakfast;
    }

    public Food getDinner() {
        return dinner;
    }

    public void setDinner(Food dinner) {
        this.dinner = dinner;
    }

    public Food getSupper() {
        return supper;
    }

    public void setSupper(Food supper) {
        this.supper = supper;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", petType=" + petType +
                ", roomNumber=" + roomNumber +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", Breakfast=" + breakfast +
                ", Dinner=" + dinner +
                ", Supper=" + supper +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (roomNumber != pet.roomNumber) return false;
        if (name != null ? !name.equals(pet.name) : pet.name != null) return false;
        if (comment != null ? !comment.equals(pet.comment) : pet.comment != null) return false;
        if (petType != pet.petType) return false;
        if (dateIn != null ? !dateIn.equals(pet.dateIn) : pet.dateIn != null) return false;
        if (dateOut != null ? !dateOut.equals(pet.dateOut) : pet.dateOut != null) return false;
        if (breakfast != null ? !breakfast.equals(pet.breakfast) : pet.breakfast != null) return false;
        if (dinner != null ? !dinner.equals(pet.dinner) : pet.dinner != null) return false;
        return supper != null ? supper.equals(pet.supper) : pet.supper == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + roomNumber;
        result = 31 * result + (dateIn != null ? dateIn.hashCode() : 0);
        result = 31 * result + (dateOut != null ? dateOut.hashCode() : 0);
        result = 31 * result + (breakfast != null ? breakfast.hashCode() : 0);
        result = 31 * result + (dinner != null ? dinner.hashCode() : 0);
        result = 31 * result + (supper != null ? supper.hashCode() : 0);
        return result;
    }
}
