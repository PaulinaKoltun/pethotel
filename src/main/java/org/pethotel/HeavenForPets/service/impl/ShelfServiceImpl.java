package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.pethotel.HeavenForPets.repository.ShelfRepository;
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

    @Override
    public ShelfEntity findShelfById(int id){
        return shelfRepository.findOne((long) id);
    }
}
