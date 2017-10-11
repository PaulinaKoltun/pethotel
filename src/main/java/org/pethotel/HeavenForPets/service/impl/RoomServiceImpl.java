package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina on 2017-10-07.
 */
@Service
public class RoomServiceImpl implements RoomService {
    @Autowired
    RoomRepository roomRepository;

    @Override
    public void saveRoom(Room room) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber(room.getRoomNumber());
        roomEntity.setFreePlaces(room.getFreePlaces());
        roomEntity.setNumberOfPlaces(room.getNumberOfPlaces());
        roomEntity.setPetType(room.getPetType());
        roomRepository.save(roomEntity);
    }

    @Override
    public List<Integer> getAllNumbers() {
        List<Integer> allRooms = new ArrayList<>();
        Iterable<RoomEntity> rooms = roomRepository.findAll();
        for (RoomEntity room : rooms) {
            if (room.getFreePlaces()>0) {
                allRooms.add(room.getRoomNumber());
            }
        }
        return allRooms;
    }
}
