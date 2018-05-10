package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Rooms.PetRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.mappers.RoomMap;
import org.springframework.stereotype.Service;

/**
 * Created by Paulina on 2017-11-12.
 */
@Service
public class RoomMapImpl implements RoomMap {

    @Override
    public Room map(RoomEntity roomEntity) {
        PetRoom room = new PetRoom();
        room.setFreePlaces(roomEntity.getFreePlaces());
        room.setNumberOfPlaces(roomEntity.getNumberOfPlaces());
        room.setPetType(roomEntity.getPetType());
        room.setRoomNumber(roomEntity.getRoomNumber());
        room.setPrice(roomEntity.getPrice());
        return room;
    }

    @Override
    public RoomEntity map(RoomEntity entity, PetRoom room){
        entity.setFreePlaces(room.getFreePlaces());
        entity.setPetType(room.getPetType());
        entity.setNumberOfPlaces(room.getNumberOfPlaces());
        entity.setPrice(room.getPrice());
        return entity;
    }
}
