package org.pethotel.HeavenForPets.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Client;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet.Pet;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.mappers.ClientMap;
import org.pethotel.HeavenForPets.mappers.OwnerMap;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.OwnerRepository;
import org.pethotel.HeavenForPets.service.AddressService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceImplTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private ClientMap clientMap;

    @Mock
    private OwnerMap ownerMap;

    @Mock
    private PetMap petMap;

    @Mock
    private AddressService addressService;

    @InjectMocks
    private OwnerServiceImpl ownerService;

    @Test
    public void checkIfSaveOwnerIsCalled() throws Exception{
        Owner owner = new Owner();
        OwnerEntity ownerEntity = new OwnerEntity();

        when(ownerMap.map(owner)).thenReturn(ownerEntity);
        when(ownerRepository.save(ownerEntity)).thenReturn(ownerEntity);

        ownerService.saveOwner(owner);

        verify(ownerRepository, times(1)).save(ownerEntity);
    }

    @Test
    public void checkIfRepositoryGivesAllClients(){
        List<OwnerEntity> ownerEntityList = new ArrayList<>();
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntityList.add(ownerEntity);
        Client client = new Client();

        when(ownerRepository.findAll()).thenReturn(ownerEntityList);
        when(clientMap.map(ownerEntity)).thenReturn(client);

        ownerService.getAllClients();

        verify(clientMap, times(1)).map(ownerEntity);
    }

    @Test
    public void checkIfRepositoryShowsAllPets(){
        Integer id = 1;
        OwnerEntity ownerEntity = new OwnerEntity();
        PetEntity petEntity = new PetEntity();
        Pet pet = new Pet();
        ownerEntity.setAnimalList(Arrays.asList(petEntity));

        when(ownerRepository.findOne((long)id)).thenReturn(ownerEntity);
        when(petMap.map(petEntity)).thenReturn(pet);

        ownerService.showAllPets(id);

        verify(petMap, times(1)).map(petEntity);
    }

    @Test
    public void checkIfPickupAllPetsIsCalled(){
        Integer id = 2;
        OwnerEntity ownerEntity = new OwnerEntity();
        PetEntity petEntity = new PetEntity();
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setFreePlaces(3);
        petEntity.setRoomEntity(roomEntity);
        ownerEntity.setAnimalList(Arrays.asList(petEntity));

        when(ownerRepository.findOne(Long.valueOf(id))).thenReturn(ownerEntity);

        ownerService.pickupAllPets(id);

        assertEquals(null, petEntity.getRoomEntity());
    }

    @Test
    public void checkIfUpdateDiscounIsDone(){
        String lastName = "lastname";
        int discount = 2;
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setLastName(lastName);

        when(ownerRepository.getOwnerByLastName(lastName)).thenReturn(ownerEntity);
        when(ownerRepository.save(ownerEntity)).thenReturn(ownerEntity);

        ownerService.updateDiscountAtOwner(lastName, discount);

        verify(ownerRepository, times(1)).save(ownerEntity);
    }

    @Test
    public void checkIfRepositoryFindOneIsCalled(){
        int id = 2;
        OwnerEntity ownerEntity = new OwnerEntity();

        when(ownerRepository.findOne((long) id)).thenReturn(ownerEntity);

        ownerService.getOwnerById((long) id);

        verify(ownerRepository, times(1)).findOne((long) id);
    }
}