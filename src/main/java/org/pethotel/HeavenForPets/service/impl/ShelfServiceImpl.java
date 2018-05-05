package org.pethotel.HeavenForPets.service.impl;

import org.apache.log4j.LogManager;
import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.repository.ShelfRepository;
import org.pethotel.HeavenForPets.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class ShelfServiceImpl implements ShelfService {

    private static final org.apache.log4j.Logger LOGGER = LogManager.getLogger(RoomServiceImpl.class);

    @Autowired
    ShelfRepository shelfRepository;

    @Autowired
    RoomRepository roomRepository;

    @Autowired
    ShelfMap shelfMap;

    @Override
    public void saveShelves(List<Shelf> shelves, int id) {
        RoomEntity roomEntity = roomRepository.findOne((long) id);
        List<ShelfEntity> shelfEntities = roomEntity.getShelfEntities();
        for (Shelf shelf : shelves) {
            ShelfEntity shelfEntity = shelfMap.map(shelf);
            shelfEntities.add(shelfEntity);
        }
        roomEntity.setShelfEntities(shelfEntities);
    }

    @Override
    public List<Shelf> getShelvesFromRoom(int id) {
        RoomEntity roomEntity = roomRepository.findOne((long) id);
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
        RoomEntity roomEntity = roomRepository.findOne((long) id);
        List<ShelfEntity> shelfEntities = roomEntity.getShelfEntities();
        List<Shelf> shelves = new ArrayList<>();
        for (ShelfEntity shelfEntity : shelfEntities) {
            if (true == shelfEntity.isFree()){
                Shelf shelf = shelfMap.map(shelfEntity);
                shelves.add(shelf);
            }
        }
        return shelves;
    }
}
