package org.pethotel.HeavenForPets.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Rooms.PetRoom;
import org.pethotel.HeavenForPets.domein.Rooms.PlantRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.mappers.RoomMap;
import org.pethotel.HeavenForPets.mappers.ShelfMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.repository.ShelfRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)

public class RoomServiceImplTest{

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private ShelfRepository shelfRepository;

    @Mock
    private RoomMap roomMap;

    @Mock
    private ShelfMap shelfMap;

    @InjectMocks
    private RoomServiceImpl roomService;

    @Test
    public void checkIfSaveRoomIsCalledWhenPetRoom() throws Exception{
        PetRoom room = new PetRoom();
        RoomEntity roomEntity = new RoomEntity();

        when(roomRepository.getRoomByNumber(room.getRoomNumber())).thenReturn(null);
        when(roomRepository.save(roomEntity)).thenReturn(roomEntity);

        roomService.saveRoom(room);

        verify(roomRepository, times(1)).save(roomEntity);
    }

    @Test
    public void checkIfSaveRoomIsCalledWhenPlantRoom() throws Exception{
        PlantRoom plantRoom = new PlantRoom();
        List<ShelfEntity> shelfEntities = new ArrayList<>();
        ShelfEntity shelfEntity = new ShelfEntity();
        shelfEntities.add(shelfEntity);
        RoomEntity roomEntity = new RoomEntity();

        when(shelfMap.mapToEntity(plantRoom.getPlantShelves())).thenReturn(shelfEntities);
        when(shelfRepository.save(shelfEntity)).thenReturn(shelfEntity);
        when(roomRepository.save(roomEntity)).thenReturn(roomEntity);

        roomService.saveRoom(plantRoom);

        verify(shelfRepository, times(1)).save(shelfEntity);
    }

    @Test
    public void checkIfSaveRoomIsCalledWhenNotExist() throws Exception{
        PetRoom room = new PetRoom();
        RoomEntity roomEntity = new RoomEntity();

        when(roomRepository.getRoomByNumber(room.getRoomNumber())).thenReturn(roomEntity);

        roomService.saveRoom(room);
    }

    @Test
    public void checkIfShowsAllRoomNumbers() throws Exception{
        List<RoomEntity> roomEntityList = new ArrayList<>();

        when(roomRepository.findAll()).thenReturn(roomEntityList);

        roomService.getAllNumbers();

        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void checkIfShowsAllPlantRooms() throws Exception{
        String type = "plant";
        Pageable pageable = new PageRequest(0,10);
        RoomEntity roomEntity = new RoomEntity();
        Page<RoomEntity> roomEntityList = new PageImpl<RoomEntity>(Arrays.asList(roomEntity));
//        List<RoomEntity> roomEntityList = new ArrayList<>();
        Room room = new PetRoom();

        //when(roomRepository.findAll(pageable)).thenReturn(roomEntityList);
        when(roomMap.map(roomEntity)).thenReturn(room);
        when(roomRepository.findAllPlantRooms(pageable)).thenReturn(roomEntityList);

        roomService.getAllRooms(type, pageable);

        verify(roomRepository, times(1)).findAllPlantRooms(pageable);
    }

    @Test
    public void checkIfShowsAllPetRooms() throws Exception{
        String type = "pet";
        Pageable pageable = new PageRequest(0,10);
        RoomEntity roomEntity = new RoomEntity();
        Page<RoomEntity> roomEntityList = new PageImpl<RoomEntity>(Arrays.asList(roomEntity));
//        List<RoomEntity> roomEntityList = new ArrayList<>();
        Room room = new PetRoom();

        //when(roomRepository.findAll(pageable)).thenReturn(roomEntityList);
        when(roomMap.map(roomEntity)).thenReturn(room);
        when(roomRepository.findAllPetRooms(pageable)).thenReturn(roomEntityList);

        roomService.getAllRooms(type, pageable);

        verify(roomRepository, times(1)).findAllPetRooms(pageable);
    }


    @Test
    public void checkIfRepositoryGivesRoomByNumber() throws Exception{
        int roomNumber = 2;
        RoomEntity roomEntity = new RoomEntity();

        when(roomRepository.getRoomByNumber(roomNumber)).thenReturn(roomEntity);

        roomService.findByRoomNumber(roomNumber);

        verify(roomRepository, times(1)).getRoomByNumber(roomNumber);
    }

    @Test
    public void checkIfRepositoryDeleteRoom() throws Exception{
        int roomNumber = 1;
        RoomEntity roomEntity = new RoomEntity();

        when(roomRepository.getRoomByNumber(roomNumber)).thenReturn(roomEntity).thenReturn(null);

        roomService.deleteRoom(roomNumber);

        verify(roomRepository, times(1)).delete(roomEntity);
    }

    @Test
    public void checkIfRoomWasUpdated() throws Exception{
        PetRoom room = new PetRoom();
        RoomEntity roomEntity = new RoomEntity();

        when(roomRepository.getRoomByNumber(room.getRoomNumber())).thenReturn(roomEntity);
        when(roomMap.map(roomEntity, room)).thenReturn(roomEntity);
        when(roomRepository.save(roomEntity)).thenReturn(roomEntity);

        roomService.updateRoom(room);

        verify(roomMap, times(1)).map(roomEntity, room);
    }

    @Test
    public void checkIfShowsAllRoomsByPetType() throws Exception{
        String petType = "FISH";
        List<RoomEntity> roomEntityList = new ArrayList<>();
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPetType(PetType.FISH);
        roomEntityList.add(roomEntity);
        Room room = new PetRoom();

        when(roomRepository.findAll()).thenReturn(roomEntityList);
        when(roomMap.map(roomEntity)).thenReturn(room);

        roomService.getAllRoomsByType(petType);

        verify(roomMap, times(1)).map(roomEntity);
    }

    @Test
    public void checkIfFreePlacesForPetTypeIsCalled() throws Exception{
        Iterable<RoomEntity> roomEntityList;

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPetType(PetType.FISH);
        roomEntity.setFreePlaces(4);
        roomEntityList = Arrays.asList(roomEntity, roomEntity);

        when(roomRepository.findAll()).thenReturn(roomEntityList);

        Map<PetType, Integer> freePlacesMap = roomService.freePlacesForPetType();

        //assertEquals(freePlacesMap.get(PetType.FISH),4);
    }

    @Test
    public void checkNumberOfRooms() throws Exception{
        List<RoomEntity> roomEntityList = new ArrayList<>();

        when(roomRepository.findAll()).thenReturn(roomEntityList);

        roomService.getNumberOfRooms();

        verify(roomRepository, times(1)).findAll();
    }

    @Test
    public void checkIfRepositoryShowsRoomByNumber() throws Exception{
        int number = 1;
        RoomEntity roomEntity = new RoomEntity();

        when(roomRepository.getRoomByNumber(number)).thenReturn(roomEntity);

        roomService.getRoomByNumber(number);

        verify(roomRepository, times(1)).getRoomByNumber(number);

    }

}