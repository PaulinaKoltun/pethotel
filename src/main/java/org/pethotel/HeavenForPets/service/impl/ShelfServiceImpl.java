package org.pethotel.HeavenForPets.service.impl;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.repository.ShelfRepository;
import org.pethotel.HeavenForPets.service.RoomService;
import org.pethotel.HeavenForPets.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShelfServiceImpl implements ShelfService {

    private static final Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);

    @Autowired
    private ShelfRepository shelfRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ShelfMap shelfMap;

    @Autowired
    private RoomService roomService;

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
    public List<Shelf> getFreeShelves(int id) {
        RoomEntity roomEntity = roomService.getRoomByNumber(id);
        List<ShelfEntity> shelfEntities = roomEntity.getShelfEntities();
        List<Shelf> shelves = new ArrayList<>();

        for (ShelfEntity shelfEntity : shelfEntities) {
            if (shelfEntity.isFree()){
                Shelf shelf = shelfMap.map(shelfEntity);
                shelves.add(shelf);
            }
        }

        return shelves;
    }
}
