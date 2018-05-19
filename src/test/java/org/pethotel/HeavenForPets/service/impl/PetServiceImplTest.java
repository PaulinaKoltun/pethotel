package org.pethotel.HeavenForPets.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.PetRepository;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private RoomService roomService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private PetMap petMap;

    @InjectMocks
    private PetServiceImpl petService;


    @Test
    public void getPets() throws Exception{
        List<PetEntity> petEntities = new ArrayList<>();
        PetEntity petEntity = new PetEntity();
        petEntities.add(petEntity);

        when(petRepository.findAll()).thenReturn(petEntities);

        petService.getPets();

        verify(petRepository, times(1)).findAll();
    }

    @Test
    public void bringPetAgain() throws Exception{
        Pet pet = new Pet();
        PetEntity petEntity = new PetEntity();
        RoomEntity roomEntity = new RoomEntity();

        when(petRepository.findOne(pet.getId())).thenReturn(petEntity);
        when(roomService.getRoomByNumber(pet.getRoomNumber())).thenReturn(roomEntity);
        when(petMap.map(pet, petEntity, roomEntity)).thenReturn(petEntity);
        when(petRepository.save(petEntity)).thenReturn(petEntity);

        petService.bringPetAgain(pet);

        verify(petRepository, times(1)).save(petEntity);
    }

    @Test
    public void pickUpPets() throws Exception{
        List<Integer> idList = new ArrayList<>();
        Integer id = new Integer(2);
        idList.add(id);
        PetEntity petEntity = new PetEntity();
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPrice(BigDecimal.TEN);
        roomEntity.setFreePlaces(12);
        petEntity.setRoomEntity(roomEntity);
        petEntity.setDateIn(new Date(12/12/2012));
        petEntity.setDateOut(new Date(14/12/2012));

        when(petRepository.ownerEntityList(idList)).thenReturn(idList);
        when(petRepository.findOne((long) id)).thenReturn(petEntity);
        when(petRepository.save(petEntity)).thenReturn(petEntity);

        petService.pickupPets(idList);

        verify(petRepository,times(1)).save(petEntity);
    }

    @Test
    public void addPettoOwner() throws Exception{
        Long id = 1l;
        Pet pet = new Pet();
        OwnerEntity ownerEntity = new OwnerEntity();
        RoomEntity roomEntity = new RoomEntity();
        PetEntity petEntity = new PetEntity();

        when(ownerService.getOwnerById(id)).thenReturn(ownerEntity);
        when(roomService.getRoomByNumber(pet.getRoomNumber())).thenReturn(roomEntity);
        when(petMap.map(pet, roomService.getRoomByNumber(pet.getRoomNumber()))).thenReturn(petEntity);
        when(petRepository.save(petEntity)).thenReturn(petEntity);

        petService.addPetToOwner(id, pet);

        verify(petRepository, times(1)).save(petEntity);
    }
}