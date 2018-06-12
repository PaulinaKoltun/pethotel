package org.pethotel.HeavenForPets.domein.Pet;

import org.pethotel.HeavenForPets.enums.PetType;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Paulina on 2017-09-27.
 */
public abstract class Pet implements Serializable {
    private long id;
    private String name;
    private String comment;
    private int roomNumber;
    private Date dateIn;
    private Date dateOut;

    public Pet(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pet pet = (Pet) o;

        if (id != pet.id) return false;
        if (roomNumber != pet.roomNumber) return false;
        if (name != null ? !name.equals(pet.name) : pet.name != null) return false;
        if (comment != null ? !comment.equals(pet.comment) : pet.comment != null) return false;
        if (dateIn != null ? !dateIn.equals(pet.dateIn) : pet.dateIn != null) return false;
        return dateOut != null ? dateOut.equals(pet.dateOut) : pet.dateOut == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + roomNumber;
        result = 31 * result + (dateIn != null ? dateIn.hashCode() : 0);
        result = 31 * result + (dateOut != null ? dateOut.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", roomNumber=" + roomNumber +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                '}';
    }

}
