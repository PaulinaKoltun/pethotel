package org.pethotel.HeavenForPets.entity;

/**
 * Created by Paulina on 2017-10-07.
 */

import com.sun.istack.internal.NotNull;
import org.pethotel.HeavenForPets.enums.PetType;

import javax.persistence.*;

@Entity
@Table(name = "room")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull
    private int roomNumber;
    private int numberOfPlaces;
    private int freePlaces;
    @Enumerated(EnumType.STRING)
    private PetType petType;

    public RoomEntity() {
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumberOfPlaces() {
        return numberOfPlaces;
    }

    public void setNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
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

        RoomEntity that = (RoomEntity) o;

        if (roomNumber != that.roomNumber) return false;
        if (numberOfPlaces != that.numberOfPlaces) return false;
        if (freePlaces != that.freePlaces) return false;
        return petType == that.petType;
    }

    @Override
    public int hashCode() {
        int result = roomNumber;
        result = 31 * result + numberOfPlaces;
        result = 31 * result + freePlaces;
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "roomNumber=" + roomNumber +
                ", numberOfPlaces=" + numberOfPlaces +
                ", freePlaces=" + freePlaces +
                ", petType=" + petType +
                '}';
    }
}
