package org.pethotel.HeavenForPets.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.RoomMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Paulina on 2017-10-07.
 */
@Service
public class RoomServiceImpl implements RoomService {

    private static final Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);
    @Autowired
    RoomRepository roomRepository;

    @Autowired
    RoomMap roomMap;

    @Override
    public void saveRoom(Room room) {
        if (roomRepository.getRoomByNumber(room.getRoomNumber()) == null) {
            RoomEntity roomEntity = new RoomEntity();
            roomEntity.setRoomNumber(room.getRoomNumber());
            roomEntity.setFreePlaces(room.getFreePlaces());
            roomEntity.setNumberOfPlaces(room.getNumberOfPlaces());
            roomEntity.setPetType(room.getPetType());
            roomEntity.setPrice(room.getPrice());
            roomRepository.save(roomEntity);
        }
        else {
            LOGGER.info("Room already exists");
        }
    }

    @Override
    public List<Integer> getAllNumbers() {
        List<RoomEntity> rooms = (List<RoomEntity>) roomRepository.findAll();
        return rooms.stream()
                .filter(r -> r.getFreePlaces() > 0)
                .map(r -> r.getRoomNumber())
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getAllRooms(String sorted) {
        List<RoomEntity> roomEntityList;

        if ("D".equals(sorted.toUpperCase())) {
            roomEntityList = roomRepository.sortedRoomEntitiesDESC();
        }
        else {
            roomEntityList = roomRepository.sortedRoomEntitiesASC();
        }
        return roomEntityList.stream()
                .map(r -> roomMap.map(r))
                .collect(Collectors.toList());
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

        return roomEntityList.stream()
                .filter(r -> isProperRoomType(petType, r))
                .map(r -> roomMap.map(r))
                .collect(Collectors.toList());
    }

    @Override
    public Map<PetType, Integer> freePlacesForPetType() {
        Iterable<RoomEntity> roomEntityList = roomRepository.findAll();
        Map<PetType, Integer> freePlacesMap = new HashMap<>();

        for (RoomEntity roomEntity : roomEntityList) {
            if (freePlacesMap.containsKey(roomEntity.getPetType())){
                int previousFreePlaces = freePlacesMap.get(roomEntity.getPetType());
                int freePlaces = previousFreePlaces + roomEntity.getFreePlaces();
                freePlacesMap.put(roomEntity.getPetType(), freePlaces);
            }
            else{
                freePlacesMap.put(roomEntity.getPetType(), roomEntity.getFreePlaces());
            }
        }
        return freePlacesMap;
    }

    @Override
    public int getNumberOfRooms() {
        List<RoomEntity> roomEntityList = (List<RoomEntity>) roomRepository.findAll();
        return roomEntityList.size();
    }

    private boolean isProperRoomType(String petType, RoomEntity roomEntity) {
        return roomEntity.getPetType().getShortType().equals(petType)
                || roomEntity.getPetType().name().equals(petType)
                || (StringUtils.isNumeric(petType)
                    && roomEntity.getPetType().getNumberType() == Integer.valueOf(petType));
    }
}
