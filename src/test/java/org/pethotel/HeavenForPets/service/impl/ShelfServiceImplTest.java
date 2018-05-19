package org.pethotel.HeavenForPets.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.pethotel.HeavenForPets.service.RoomService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ShelfServiceImplTest {

    @Mock
    private ShelfMap shelfMap;

    @Mock
    private RoomService roomService;

    @InjectMocks
    private ShelfServiceImpl shelfService;


    @Test
    public void saveShelves() throws Exception{
        List<Shelf> shelves = new ArrayList<>();
        int id = 1;
        RoomEntity roomEntity = new RoomEntity();
        Shelf shelf = new Shelf();
        ShelfEntity shelfEntity = new ShelfEntity();
        ShelfEntity entity = new ShelfEntity();

        List<ShelfEntity> shelfEntities = new ArrayList<>();
        shelfEntities.add(shelfEntity);

        roomEntity.setShelfEntities(shelfEntities);
        shelves.add(shelf);

        when(roomService.getRoomByNumber(id)).thenReturn(roomEntity);
        when(shelfMap.map(shelf)).thenReturn(entity);

        shelfService.saveShelves(shelves, id);

        verify(shelfMap, times(1)).map(shelf);
    }

    @Test
    public void getShelvesFromRoom() throws Exception{
        int id = 1;
        RoomEntity roomEntity = new RoomEntity();
        ShelfEntity shelfEntity = new ShelfEntity();
        Shelf shelf = new Shelf();
        roomEntity.setShelfEntities(Arrays.asList(shelfEntity));

        when(roomService.getRoomByNumber(id)).thenReturn(roomEntity);
        when(shelfMap.map(shelfEntity)).thenReturn(shelf);

        shelfService.getShelvesFromRoom(id);

        verify(shelfMap, times(1)).map(shelfEntity);
    }

    @Test
    public void getFreeShelves() throws Exception{
        int id = 1;
        RoomEntity roomEntity = new RoomEntity();
        ShelfEntity shelfEntity = new ShelfEntity();
        shelfEntity.setFree(true);
        Shelf shelf = new Shelf();
        roomEntity.setShelfEntities(Arrays.asList(shelfEntity));

        when(roomService.getRoomByNumber(id)).thenReturn(roomEntity);
        when(shelfMap.map(shelfEntity)).thenReturn(shelf);

        shelfService.getFreeShelves(id);

        verify(shelfMap, times(1)).map(shelfEntity);
    }

}