package org.pethotel.HeavenForPets.domein.Rooms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Paulina on 2017-10-07.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "orgin")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlantRoom.class, name = "PlantRoom"),

        @JsonSubTypes.Type(value = PetRoom.class, name = "PetRoom") }
)
public abstract class Room implements Serializable {
    private int roomNumber;
    private int numberOfPlaces;
    private int freePlaces;
    private BigDecimal price;

    public Room() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        if (roomNumber != room.roomNumber) return false;
        if (numberOfPlaces != room.numberOfPlaces) return false;
        if (freePlaces != room.freePlaces) return false;
        return price != null ? price.equals(room.price) : room.price == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (roomNumber ^ (roomNumber >>> 32));
        result = 31 * result + numberOfPlaces;
        result = 31 * result + freePlaces;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomNumber=" + roomNumber +
                ", numberOfPlaces=" + numberOfPlaces +
                ", freePlaces=" + freePlaces +
                ", price=" + price +
                '}';
    }
}
