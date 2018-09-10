package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Rooms.PetRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.enums.PlantInsolation;
import org.pethotel.HeavenForPets.exceptions.TemperatureWrongRangeException;
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

    RoomEntity findById(int id);

    void deleteRoom(int roomNumber);

    void updateRoom(PetRoom room);

    List<Room> getAllRoomsByType(String petType);

    Map<PetType, Integer> freePlacesForPetType();

    int getNumberOfRooms(String type);

    RoomEntity getRoomByNumber(long number);

    int getNumberOfRoomsFromQuery();

    List<Room> getAllRoomsInTheRangeForPlant(int id) throws TemperatureWrongRangeException;

    List<Room> getAllRoomsWithProperShelves(int id);

    List<RoomEntity> getAllPlantRooms();

    List<RoomEntity> getAllPlantRoomsForTemperature(int minTemerature, int maxTemperature);
}
