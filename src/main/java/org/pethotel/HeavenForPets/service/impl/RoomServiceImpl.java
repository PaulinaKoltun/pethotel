package org.pethotel.HeavenForPets.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.pethotel.HeavenForPets.domein.Rooms.PetRoom;
import org.pethotel.HeavenForPets.domein.Rooms.PlantRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.RoomEntityBuilder;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.RoomMap;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.repository.ShelfRepository;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    ShelfRepository shelfRepository;

    @Autowired
    RoomMap roomMap;

    @Autowired
    ShelfMap shelfMap;

    @Override
    public void saveRoom(Room room) {
        LOGGER.info("saveRoom: " + room);
        if (roomRepository.getRoomByNumber(room.getRoomNumber()) == null) {
            RoomEntity roomEntity;
            if (room instanceof PetRoom) {
                LOGGER.info("PetRoom");
                roomEntity = RoomEntityBuilder.fromRoom((PetRoom)room);
            }
            else {
                LOGGER.info("PlantRoom");
                PlantRoom plantRoom = (PlantRoom)room;
                List<ShelfEntity> shelfEntities = shelfMap.map(plantRoom.getPlantShelves());
                for (ShelfEntity shelfEntity : shelfEntities) {
                    shelfRepository.save(shelfEntity);
                }
                roomEntity = RoomEntityBuilder
                        .fromRoom(plantRoom, shelfEntities);
                LOGGER.info("room entity" + roomEntity);
            }
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
    public List<Room> getAllRooms(Pageable pageable) {
        Page<RoomEntity> roomEntityList = roomRepository.findAll(pageable);

        return roomEntityList.getContent().stream()
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
    public void updateRoom(PetRoom room){
        RoomEntity entity = roomRepository.getRoomByNumber(room.getRoomNumber());

        entity = roomMap.map(entity, room);

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

    @Override
    public RoomEntity getRoomByNumber(int number){
        return roomRepository.getRoomByNumber(number);

    }
}
