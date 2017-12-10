package org.pethotel.HeavenForPets.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.mappers.RoomMap;
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

    @Autowired
    RoomMap roomMap;

    @Override
    public void saveRoom(Room room) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setRoomNumber(room.getRoomNumber());
        roomEntity.setFreePlaces(room.getFreePlaces());
        roomEntity.setNumberOfPlaces(room.getNumberOfPlaces());
        roomEntity.setPetType(room.getPetType());
        roomEntity.setPrice(room.getPrice());
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

    @Override
    public List<Room> getAllRooms() {
        List<RoomEntity> roomEntityList = (List<RoomEntity>) roomRepository.findAll();
        List<Room> rooms = new ArrayList<>();
        for (RoomEntity roomEntity : roomEntityList) {
            Room room = roomMap.map(roomEntity);
            rooms.add(room);
        }
        return rooms;
    }

    @Override
    public RoomEntity findByRoomNumber(int roomNumber) {
        return roomRepository.getRoomByNumber(roomNumber);

    }

    @Override
    public void deleteRoom(int roomNumber) {
        RoomEntity roomEntity = roomRepository.getRoomByNumber(roomNumber);
        roomRepository.delete(roomEntity);
    }

    @Override
    public void updateRoom(Room room){
        RoomEntity entity = roomRepository.getRoomByNumber(room.getRoomNumber());

        entity.setFreePlaces(room.getFreePlaces());
        entity.setPetType(room.getPetType());
        entity.setNumberOfPlaces(room.getNumberOfPlaces());
        entity.setPrice(room.getPrice());

        roomRepository.save(entity);
    }

    @Override
    public List<Room> getAllRoomsByType(String petType) {
        List<RoomEntity> roomEntityList = (List<RoomEntity>) roomRepository.findAll();
        List<Room> rooms = new ArrayList<>();
        for (RoomEntity roomEntity : roomEntityList) {
            if (isProperRoomType(petType, roomEntity)) {
                Room room = roomMap.map(roomEntity);
                rooms.add(room);
            }
        }
        return rooms;
    }

    private boolean isProperRoomType(String petType, RoomEntity roomEntity) {
        return roomEntity.getPetType().getShortType().equals(petType)
                || roomEntity.getPetType().name().equals(petType)
                || (StringUtils.isNumeric(petType) && roomEntity.getPetType().getNumberType() == Integer.valueOf
                (petType));
    }
}
