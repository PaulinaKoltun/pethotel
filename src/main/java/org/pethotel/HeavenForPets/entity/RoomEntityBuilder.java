package org.pethotel.HeavenForPets.entity;

import org.pethotel.HeavenForPets.domein.Rooms.PetRoom;
import org.pethotel.HeavenForPets.domein.Rooms.PlantRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.enums.PetType;

import java.math.BigDecimal;
import java.util.List;

public final class RoomEntityBuilder {
    private long roomNumber;
    private int numberOfPlaces;
    private int freePlaces;
    private PetType petType;
    private BigDecimal price;
    private List<ShelfEntity> shelfEntities;

    private RoomEntityBuilder() {
    }


    public static RoomEntity fromRoom(Room room){
        return aRoomEntity()
                .withRoomNumber(room.getRoomNumber())
                .withFreePlaces(room.getFreePlaces())
                .withNumberOfPlaces(room.getNumberOfPlaces())
                .withPrice(room.getPrice())
                .build();
    }

    public static RoomEntity fromRoom(PetRoom room){
        return aRoomEntity()
                .withRoomNumber(room.getRoomNumber())
                .withFreePlaces(room.getFreePlaces())
                .withNumberOfPlaces(room.getNumberOfPlaces())
                .withPrice(room.getPrice())
                .withPetType(room.getPetType())
                .build();
    }


    public static RoomEntity fromRoom(PlantRoom room, List<ShelfEntity> shelfEntities){
        return aRoomEntity()
                .withRoomNumber(room.getRoomNumber())
                .withFreePlaces(room.getFreePlaces())
                .withNumberOfPlaces(room.getNumberOfPlaces())
                .withPrice(room.getPrice())
                .withShelfEntities(shelfEntities)
                .build();
    }

    public static RoomEntityBuilder aRoomEntity() {
        return new RoomEntityBuilder();
    }

    public RoomEntityBuilder withRoomNumber(long roomNumber) {
        this.roomNumber = roomNumber;
        return this;
    }

    public RoomEntityBuilder withNumberOfPlaces(int numberOfPlaces) {
        this.numberOfPlaces = numberOfPlaces;
        return this;
    }

    public RoomEntityBuilder withFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
        return this;
    }

    public RoomEntityBuilder withPetType(PetType petType) {
        this.petType = petType;
        return this;
    }

    public RoomEntityBuilder withPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public RoomEntityBuilder withShelfEntities(List<ShelfEntity> shelfEntities) {
        this.shelfEntities = shelfEntities;
        return this;
    }

    public RoomEntity build() {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber(roomNumber);
        roomEntity.setNumberOfPlaces(numberOfPlaces);
        roomEntity.setFreePlaces(freePlaces);
        roomEntity.setPetType(petType);
        roomEntity.setPrice(price);
        roomEntity.setShelfEntities(shelfEntities);
        return roomEntity;
    }
}
