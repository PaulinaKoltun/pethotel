package org.pethotel.HeavenForPets.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.pethotel.HeavenForPets.domein.Rooms.PetRoom;
import org.pethotel.HeavenForPets.domein.Rooms.PlantRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.RoomEntityBuilder;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.enums.RoomType;
import org.pethotel.HeavenForPets.exceptions.TemperatureWrongRangeException;
import org.pethotel.HeavenForPets.mappers.RoomMap;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.repository.ShelfRepository;
import org.pethotel.HeavenForPets.service.PetService;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
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
    PetService petService;

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
                roomEntity = RoomEntityBuilder.fromRoom((PetRoom) room);
            } else {
                LOGGER.info("PlantRoom");
                PlantRoom plantRoom = (PlantRoom) room;
                List<ShelfEntity> shelfEntities = shelfMap.mapToEntity(plantRoom.getShelves());
                for (ShelfEntity shelfEntity : shelfEntities) {
                    shelfRepository.save(shelfEntity);
                }
                roomEntity = RoomEntityBuilder
                        .fromRoom(plantRoom, shelfEntities);
                LOGGER.info("room entity" + roomEntity);
            }
            roomRepository.save(roomEntity);
        } else {
            LOGGER.info("Room already exists");
        }
    }

    @Override
    public void saveRoomEntity(RoomEntity roomEntity) {
        roomRepository.save(roomEntity);
    }

    @Override
    public List<Long> getAllNumbers() {
        List<RoomEntity> rooms = (List<RoomEntity>) roomRepository.findAll();
        return rooms.stream()
                .filter(r -> r.getFreePlaces() > 0)
                .map(r -> r.getRoomNumber())
                .collect(Collectors.toList());
    }

    @Override
    public List<Room> getAllRooms(String type, Pageable pageable) {
        Page<RoomEntity> roomEntityList;

        if (RoomType.PLANT.getStringType().equals(type.toLowerCase())) {
            roomEntityList = roomRepository.findAllPlantRooms(pageable);
        } else if (RoomType.PET.getStringType().equals(type.toLowerCase())) {
            roomEntityList = roomRepository.findAllPetRooms(pageable);
        } else {
            LOGGER.info("Wrong type");
            return Collections.EMPTY_LIST;
        }

        return roomEntityList.getContent().stream()
                .map(r -> roomMap.map(r))
                .collect(Collectors.toList());
    }

    @Override
    public RoomEntity findById(int id) {
        return roomRepository.findOne((long) id);

    }

    @Override
    public void deleteRoom(int roomNumber) {
        RoomEntity roomEntity = roomRepository.getRoomByNumber(roomNumber);
        roomRepository.delete(roomEntity);
    }

    @Override
    public void updateRoom(PetRoom room) {
        RoomEntity entity = roomRepository.getRoomByNumber(room.getRoomNumber());

        entity = roomMap.map(entity, room);

        roomRepository.save(entity);
    }

    @Override
    public List<Room> getAllRoomsByType(String petType) {
        List<RoomEntity> roomEntityList = roomRepository.findAllPetRooms();

        if ("ALL".equals(petType.toUpperCase())) {
            return roomEntityList.stream()
                    .map(r -> roomMap.map(r))
                    .collect(Collectors.toList());
        } else {
            return roomEntityList.stream()
                    .filter(r -> isProperRoomType(petType, r))
                    .map(r -> roomMap.map(r))
                    .collect(Collectors.toList());
        }
    }

    @Override
    public Map<PetType, Integer> freePlacesForPetType() {
        Iterable<RoomEntity> roomEntityList = roomRepository.findAll();
        Map<PetType, Integer> freePlacesMap = new HashMap<>();

        for (RoomEntity roomEntity : roomEntityList) {
            if (freePlacesMap.containsKey(roomEntity.getPetType())) {
                int previousFreePlaces = freePlacesMap.get(roomEntity.getPetType());
                int freePlaces = previousFreePlaces + roomEntity.getFreePlaces();
                freePlacesMap.put(roomEntity.getPetType(), freePlaces);
            } else {
                freePlacesMap.put(roomEntity.getPetType(), roomEntity.getFreePlaces());
            }
        }
        return freePlacesMap;
    }

    @Override
    public int getNumberOfRooms(String type) {
        if (RoomType.PLANT.getStringType().equals(type.toLowerCase())) {
            return roomRepository.countNumbersOfPlantRooms();
        } else if (RoomType.PET.getStringType().equals(type.toLowerCase())) {
            return roomRepository.countNumbersOfPetRooms();
        } else {
            LOGGER.info("Wrong type");
            return 0;
        }
    }

    private boolean isProperRoomType(String petType, RoomEntity roomEntity) {
        if (roomEntity.getPetType() == null) {
            return false;
        } else {
            return roomEntity.getPetType().getShortType().equals(petType)
                    || roomEntity.getPetType().name().equals(petType)
                    || (StringUtils.isNumeric(petType)
                    && roomEntity.getPetType().getNumberType() == Integer.valueOf(petType));
        }
    }

    @Override
    public RoomEntity getRoomByNumber(long number) {
        return roomRepository.getRoomByNumber(number);

    }

    @Override
    public int getNumberOfRoomsFromQuery() {
        return roomRepository.getNumberOfRoomsFromQuery();
    }

    @Override
    public List<Room> getAllRoomsInTheRangeForPlant(int id)
            throws TemperatureWrongRangeException {

        PetEntity petEntity = petService.getPetById((long) id);

        if (petEntity.getMaxTemperature() < petEntity.getMinTemperature()) {
            throw new TemperatureWrongRangeException();
        }

        List<RoomEntity> roomEntityList = getAllPlantRoomsForTemperature(
                petEntity.getMinTemperature(),
                petEntity.getMaxTemperature());

        List<Room> rooms = new ArrayList<>();

        for (RoomEntity roomEntity : roomEntityList) {
                rooms.add(roomMap.map(roomEntity));
            }
        return rooms;
    }

    @Override
    public List<Room> getAllRoomsWithProperShelves(int id) {
        PetEntity petEntity = petService.getPetById((long) id);

        List<RoomEntity> roomEntityList =
                roomRepository.getAllRoomsWithProperShelves(
                        petEntity.getPlantInsolation(),
                        petEntity.getMinTemperature(),
                        petEntity.getMaxTemperature()
                );
        List<Room> rooms = new ArrayList<>();

        for (RoomEntity roomEntity : roomEntityList) {
            rooms.add(roomMap.map(roomEntity));
        }
        return rooms;
    }


    @Override
    public List<RoomEntity> getAllPlantRooms() {
        return roomRepository.findAllPlantRooms();
    }

    @Override
    public List<RoomEntity> getAllPlantRoomsForTemperature(int minTemerature, int maxTemperature){
        return roomRepository.getAllPlantRoomsForTemperature(minTemerature, maxTemperature);
    }

    @Override
    public List<Room> getRoomsForTemperatureRange(String plantInsolation, int minTemp, int maxTemp) {
        Optional<PlantInsolation> optional = Arrays.stream(PlantInsolation.values())
                .filter(pi -> pi.getShortType().equals(
                        plantInsolation.toLowerCase()))
                .findFirst();

        if (optional.isPresent()) {
            LOGGER.info("isolation " + optional.get());
            return roomRepository.getAllRoomsWithProperShelves(optional.get(), minTemp, maxTemp)
                    .stream()
                    .map(e -> roomMap.map(e))
                    .collect(Collectors.toList());

        }
        return Collections.emptyList();
    }
}
