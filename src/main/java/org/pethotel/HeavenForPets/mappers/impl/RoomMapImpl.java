package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Rooms.PetRoom;
import org.pethotel.HeavenForPets.domein.Rooms.PlantRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.mappers.RoomMap;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paulina on 2017-11-12.
 */
@Service
public class RoomMapImpl implements RoomMap {

    @Autowired
    private ShelfMap shelfMap;

    @Override
    public Room map(RoomEntity roomEntity) {
        Room room;
        if (roomEntity.getPetType() != null) {
            room = new PetRoom();
            ((PetRoom)room).setPetType(roomEntity.getPetType());
        } else {
            room = new PlantRoom();
            List<Shelf> shelves = shelfMap.mapToDto(roomEntity.getShelfEntities());
            ((PlantRoom)room).setShelves(shelves);
        }
        room.setFreePlaces(roomEntity.getFreePlaces());
        room.setNumberOfPlaces(roomEntity.getNumberOfPlaces());
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
