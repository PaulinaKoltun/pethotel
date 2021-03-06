package org.pethotel.HeavenForPets.entity;

/**
 * Created by Paulina on 2017-10-07.
 */

import org.pethotel.HeavenForPets.enums.PetType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "room_number")
    private long roomNumber;

    @Column(name = "number_of_places")
    private int numberOfPlaces;

    @Column(name = "free_places")
    private int freePlaces;

    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type")
    private PetType petType;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="SHELVES_ROOMS_LIST",
            joinColumns = @JoinColumn(name="room_id", referencedColumnName="id",
                    foreignKey = @ForeignKey(name = "FK_ROOM_AND_SHELF")),
            inverseJoinColumns = @JoinColumn(name="shelf_id", referencedColumnName="id",
                    foreignKey = @ForeignKey(name = "FK_ROOM_AND_SHELF_ID"))
    )
    private List<ShelfEntity> shelfEntities;

    @Column(name = "temperature")
    private int temperature;

    public RoomEntity() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(long roomNumber) {
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

    public List<ShelfEntity> getShelfEntities() {
        return shelfEntities;
    }

    public void setShelfEntities(List<ShelfEntity> shelfEntities) {
        this.shelfEntities = shelfEntities;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (roomNumber != that.roomNumber) return false;
        if (numberOfPlaces != that.numberOfPlaces) return false;
        if (freePlaces != that.freePlaces) return false;
        if (temperature != that.temperature) return false;
        if (petType != that.petType) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return shelfEntities != null ? shelfEntities.equals(that.shelfEntities) : that.shelfEntities == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (roomNumber ^ (roomNumber >>> 32));
        result = 31 * result + numberOfPlaces;
        result = 31 * result + freePlaces;
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (shelfEntities != null ? shelfEntities.hashCode() : 0);
        result = 31 * result + temperature;
        return result;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "roomNumber=" + roomNumber +
                ", numberOfPlaces=" + numberOfPlaces +
                ", freePlaces=" + freePlaces +
                ", petType=" + petType +
                ", price=" + price +
                ", shelfEntities=" + shelfEntities +
                ", temperature=" + temperature +
                '}';
    }


}
