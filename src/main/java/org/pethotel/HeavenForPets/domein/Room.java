package org.pethotel.HeavenForPets.domein;

import org.pethotel.HeavenForPets.enums.PetType;

import java.io.Serializable;

/**
 * Created by Paulina on 2017-10-07.
 */
public class Room implements Serializable {
    private int roomNumber;
    private int numberOfPlaces;
    private int freePlaces;
    private PetType petType;

    public Room() {
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

        Room room = (Room) o;

        if (roomNumber != room.roomNumber) return false;
        if (numberOfPlaces != room.numberOfPlaces) return false;
        if (freePlaces != room.freePlaces) return false;
        return petType == room.petType;
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
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", numberOfPlaces=" + numberOfPlaces +
                ", freePlaces=" + freePlaces +
                ", petType=" + petType +
                '}';
    }
}