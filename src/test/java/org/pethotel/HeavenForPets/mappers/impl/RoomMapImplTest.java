package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Test;
import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.RoomMap;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class RoomMapImplTest {

    private RoomMap roomMap = new RoomMapImpl();

    @Test
    public void shouldCheckIfRoomNumberIsCorrectlyMappedInRoom(){
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber(2);

        Room room = roomMap.map(roomEntity);
        assertEquals(roomEntity.getRoomNumber(), room.getRoomNumber());
    }

    @Test
    public void shouldCheckIfPriceIsCorrectlyMappedInRoom(){
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPrice(new BigDecimal("2"));

        Room room = roomMap.map(roomEntity);
        assertEquals(roomEntity.getPrice(), room.getPrice());
    }

    @Test
    public void shouldCheckIfFreePlacesIsCorrectlyMappedInRoom(){
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setFreePlaces(10);

        Room room = roomMap.map(roomEntity);
        assertEquals(roomEntity.getFreePlaces(), room.getFreePlaces());
    }

    @Test
    public void shouldCheckIfPetTypeIsCorrectlyMappedInRoom(){
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPetType(PetType.FISH);

        Room room = roomMap.map(roomEntity);
        assertEquals(roomEntity.getPetType(), room.getPetType());
    }

    @Test
    public void shouldCheckIfNumberOfPlacesIsCorrectlyMappedInRoom(){
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setNumberOfPlaces(12);

        Room room = roomMap.map(roomEntity);
        assertEquals(roomEntity.getNumberOfPlaces(), room.getNumberOfPlaces());
    }
}