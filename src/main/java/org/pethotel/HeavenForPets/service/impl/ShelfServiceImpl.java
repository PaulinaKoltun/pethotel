package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Pet.Plant;
import org.pethotel.HeavenForPets.domein.Rooms.PlantRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.enums.PlantInsolation;
import org.pethotel.HeavenForPets.exceptions.TemperatureWrongRangeException;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.pethotel.HeavenForPets.repository.ShelfRepository;
import org.pethotel.HeavenForPets.service.PetService;
import org.pethotel.HeavenForPets.service.RoomService;
import org.pethotel.HeavenForPets.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService {

    @Autowired
    private ShelfMap shelfMap;

    @Autowired
    private RoomService roomService;

    @Autowired
    private PetService petService;

    @Autowired
    private ShelfRepository shelfRepository;

    @Override
    public void saveShelves(List<Shelf> shelves, int id) {
        RoomEntity roomEntity = roomService.getRoomByNumber(id);
        List<ShelfEntity> shelfEntities = roomEntity.getShelfEntities();

        for (Shelf shelf : shelves) {
            ShelfEntity shelfEntity = shelfMap.map(shelf);
            shelfEntities.add(shelfEntity);
        }

        roomEntity.setShelfEntities(shelfEntities);
    }

    @Override
    public List<Shelf> getShelvesFromRoom(int id) {
        RoomEntity roomEntity = roomService.getRoomByNumber(id);
        List<ShelfEntity> shelfEntities = roomEntity.getShelfEntities();
        List<Shelf> shelves = new ArrayList<>();

        for (ShelfEntity shelfEntity : shelfEntities) {
            Shelf shelf = shelfMap.map(shelfEntity);
            shelves.add(shelf);
        }

        return shelves;
    }

    @Override
    public List<Shelf> getFreeShelvesForPlant(int id) throws TemperatureWrongRangeException {
        PetEntity petEntity = petService.getPetById((long) id);
        PlantInsolation plantInsolation = petEntity.getPlantInsolation();
        List<Room> roomList = roomService.getAllRoomsWithProperShelves(id);
        List<Shelf> properShelves = new ArrayList<>();

        for (Room room : roomList) {
            List<Shelf> shelves = ((PlantRoom) room).getPlantShelves();
            for (Shelf shelf : shelves) {
                if (shelf.isFree() && plantInsolation.equals(shelf.getPlantInsolation())){
                    properShelves.add(shelf);
                }
            }
        }
        return properShelves;
    }

    @Override
    public ShelfEntity findShelfById(long id){
        return shelfRepository.findOne((long) id);
    }
}
