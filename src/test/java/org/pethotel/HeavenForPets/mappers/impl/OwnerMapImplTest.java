package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.*;
import org.pethotel.HeavenForPets.entity.*;
import org.pethotel.HeavenForPets.enums.OwnerCategory;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;
import org.pethotel.HeavenForPets.mappers.AddressMap;
import org.pethotel.HeavenForPets.mappers.FoodMap;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.OwnerRepository;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.service.OwnerService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Paulina on 2017-10-04.
 */

@RunWith(MockitoJUnitRunner.class)
public class OwnerMapImplTest {

    @Mock
    private PetMap petMap;
    @Mock
    private FoodMap foodMap;
    @Mock
    private AddressMap addressMap;
    @Mock
    private OwnerService ownerService;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private OwnerRepository ownerRepository;

    @InjectMocks
    private OwnerMapImpl ownerMap;

    @Test(expected = InvalidPetTypeException.class)
    public void shouldThrowExceptionWhenThePetTypeInRoomIsDifferentThanReal() throws InvalidPetTypeException {
        // give
        Owner owner = new Owner();
        Pet pet = new Pet();
        preparePet(pet, "Ara", PetType.BIRD);
        owner.setPetList(Arrays.asList(pet));

        PetEntity petEntity = new PetEntity();
        petEntity.setPetType(PetType.MAMMAL);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPetType(PetType.FISH);

        // when
        Mockito.when(petMap.map(pet)).thenReturn(petEntity);
        Mockito.when(roomRepository.getRoomByNumber(Mockito.anyInt()))
                .thenReturn(roomEntity);

        OwnerEntity ownerEntity = ownerMap.map(owner);

        // then
        assertEquals(owner.getFirstName(),ownerEntity.getFirstName());
    }

    @Test
    public void shouldCheckAddressMapper() throws InvalidPetTypeException{
        // give
        Owner owner = new Owner();
        Address address = new Address();
        String city = "Wroclaw";
        address.setCity(city);
        owner.setAddress(address);
        owner.setOwnerCategory(OwnerCategory.NORMAL);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(city);

        // when
        Mockito.when(addressMap.mapAddress(address)).thenReturn(addressEntity);

        OwnerEntity ownerEntity = ownerMap.map(owner);

        // then
        assertEquals(owner.getAddress().getCity(),ownerEntity.getAddressEntity().getCity());
    }

    @Test
    public void checkIfOnePlaceIsFreeWhenPetIsAddToRoom() throws InvalidPetTypeException{
        // give
        Owner owner = new Owner();
        Pet pet = new Pet();
        preparePet(pet, "Ara", PetType.BIRD);

        pet = new Pet();

        pet.setName("bbbbbbbb");

        owner.setPetList(Arrays.asList(pet, pet));
        owner.setOwnerCategory(OwnerCategory.NORMAL);

        PetEntity petEntity = new PetEntity();
        petEntity.setPetType(PetType.BIRD);

        RoomEntity roomEntity = prepareRoom(PetType.BIRD, 5);

        // when
        Mockito.when(petMap.map(pet)).thenReturn(petEntity);
        Mockito.when(roomRepository.getRoomByNumber(pet.getRoomNumber()))
                .thenReturn(roomEntity);

        // then
        ownerMap.map(owner);

        assertEquals(roomEntity.getFreePlaces(), 3);
    }

    private RoomEntity prepareRoom(PetType petType, int freePlaces) {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPetType(petType);
        roomEntity.setFreePlaces(freePlaces);
        return roomEntity;
    }

    private void preparePet(final Pet pet, String name, PetType petType) {
        pet.setRoomNumber(1);
        pet.setPetType(petType);
        pet.setName(name);
        pet.setComment("komentarz");
        pet.setDateIn(new Date(2017,02,12));
        pet.setDateOut(new Date(2017,02,19));
    }

}