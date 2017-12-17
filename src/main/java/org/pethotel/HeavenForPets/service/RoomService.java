package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;

import java.util.List;
import java.util.Map;

/**
 * Created by Paulina on 2017-10-07.
 */
public interface RoomService {
    void saveRoom(Room room);
    List<Integer> getAllNumbers();
    List<Room> getAllRooms();
    RoomEntity findByRoomNumber(int roomNumber);
    void deleteRoom(int roomNumber);
    void updateRoom(Room room);

    List<Room> getAllRoomsByType(String petType);

    Map<PetType, Integer> freePlacesForPetType();
}
