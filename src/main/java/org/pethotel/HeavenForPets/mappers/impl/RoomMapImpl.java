package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Room;
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
        Room room = new Room();
        room.setFreePlaces(roomEntity.getFreePlaces());
        room.setNumberOfPlaces(roomEntity.getNumberOfPlaces());
        room.setPetType(roomEntity.getPetType());
        room.setRoomNumber(roomEntity.getRoomNumber());
        room.setPrice(roomEntity.getPrice());
        return room;
    }
}
