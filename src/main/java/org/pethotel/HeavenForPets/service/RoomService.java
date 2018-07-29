package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Pet.Plant;
import org.pethotel.HeavenForPets.domein.Rooms.PetRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * Created by Paulina on 2017-10-07.
 */
public interface RoomService {
    void saveRoom(Room room);
    void saveRoomEntity(RoomEntity roomEntity);
    List<Long> getAllNumbers();
    List getAllRooms(String type, Pageable pageable);
    RoomEntity findByRoomNumber(int roomNumber);
    void deleteRoom(int roomNumber);
    void updateRoom(PetRoom room);
    List<Room> getAllRoomsByType(String petType);
    Map<PetType, Integer> freePlacesForPetType();
    int getNumberOfRooms();
    RoomEntity getRoomByNumber(long number);
    int getNumberOfRoomsFromQuery();
    List<Room> getAllRoomsInTheRangeForPlant(int id);
}
