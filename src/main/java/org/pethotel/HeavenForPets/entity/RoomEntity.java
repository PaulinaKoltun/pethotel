package org.pethotel.HeavenForPets.entity;

/**
 * Created by Paulina on 2017-10-07.
 */

import org.pethotel.HeavenForPets.enums.PetType;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "ROOM")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "room_number")
    private int roomNumber;
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
            name="SHELVES_ROOMs_LIST",
            joinColumns = @JoinColumn(name="room_entity", referencedColumnName="id", foreignKey = @ForeignKey(name =
                    "FK_ROOM_AND_SHELF")),
            inverseJoinColumns = @JoinColumn(name="shelves_entity", referencedColumnName="id", foreignKey = @ForeignKey(name =
                    "FK_ROOM_AND_SHELF_ID"))
    )
    private List<ShelfEntity> shelfEntities;

    public RoomEntity() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomEntity that = (RoomEntity) o;

        if (roomNumber != that.roomNumber) return false;
        if (numberOfPlaces != that.numberOfPlaces) return false;
        if (freePlaces != that.freePlaces) return false;
        if (petType != that.petType) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        return shelfEntities != null ? shelfEntities.equals(that.shelfEntities) : that.shelfEntities == null;
    }

    @Override
    public int hashCode() {
        int result = roomNumber;
        result = 31 * result + numberOfPlaces;
        result = 31 * result + freePlaces;
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (shelfEntities != null ? shelfEntities.hashCode() : 0);
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
                '}';
    }
}
