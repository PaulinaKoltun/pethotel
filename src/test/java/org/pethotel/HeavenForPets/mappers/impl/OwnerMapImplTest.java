package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Ignore;
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
import org.pethotel.HeavenForPets.repository.AddressRepository;
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
        Owner owner = new Owner();
        Pet pet = new Pet();
        preparePet(pet, "Ara", PetType.BIRD);
        owner.setPetList(Arrays.asList(pet));

        PetEntity petEntity = new PetEntity();
        petEntity.setPetType(PetType.MAMMAL);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPetType(PetType.FISH);

        Mockito.when(petMap.map(pet)).thenReturn(petEntity);
        Mockito.when(roomRepository.getRoomByNumber(Mockito.anyInt()))
                .thenReturn(roomEntity);

        OwnerEntity ownerEntity = ownerMap.map(owner);

        assertEquals(owner.getFirstName(),ownerEntity.getFirstName());
    }

    @Test
    public void shouldCheckAddressMapper() throws InvalidPetTypeException{
        Owner owner = new Owner();
        Address address = new Address();
        String city = "Wroclaw";
        address.setCity(city);
        owner.setAddress(address);
        owner.setOwnerCategory(OwnerCategory.NORMAL);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(city);
        Mockito.when(addressMap.mapAddress(address)).thenReturn(addressEntity);
        System.out.printf(owner.toString());
        OwnerEntity ownerEntity = ownerMap.map(owner);
        System.out.printf(ownerEntity.toString());
        assertEquals(owner.getAddress().getCity(),ownerEntity.getAddressEntity().getCity());
    }
    @Test
    public void shouldCheckPetMapper() throws InvalidPetTypeException{
        Pet pet = new Pet();

        preparePet(pet, "Ara", PetType.BIRD);

        PetEntity petEntity = new PetEntity();
        preparePetEntity(petEntity, "Ara", PetType.BIRD);

        Mockito.when(petMap.map(pet)).thenReturn(petEntity);

        assertEquals(pet.getName(), petEntity.getName());
    }

    private void preparePetEntity(final PetEntity petEntity, String name, PetType petType) {;
        petEntity.setPetType(petType);
        petEntity.setName(name);
        petEntity.setComment("komentarz");
    }

    @Test
    public void shouldCheckPetsCounter() throws InvalidPetTypeException{
        Owner owner = new Owner();
        Pet pet = new Pet();
        Pet pet1 = pet;
        preparePet(pet, "Ara", PetType.BIRD);

        pet = new Pet();

        pet1.setName("AAAAAA");
        pet.setName("bbbbbbbb");

        System.out.println(pet1);

        owner.setPetList(Arrays.asList(pet));
        owner.setOwnerCategory(OwnerCategory.NORMAL);

        PetEntity petEntity = new PetEntity();
        petEntity.setPetType(PetType.BIRD);

        RoomEntity roomEntity = prepareRoom(PetType.BIRD, 5);
        RoomEntity roomEntity1 = prepareRoom(PetType.MAMMAL, 2);

        Mockito.when(petMap.map(pet)).thenReturn(petEntity);
        Mockito.when(roomRepository.getRoomByNumber(pet.getRoomNumber()))
                .thenReturn(roomEntity);
        Mockito.when(roomRepository.getRoomByNumber(pet1.getRoomNumber()))
                .thenReturn(roomEntity1);

        OwnerEntity ownerEntity = ownerMap.map(owner);

        assertEquals(roomEntity.getFreePlaces(), 4);
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
    @Test
    public void shouldCheckFoodMapper(){
        Food food = new Food();
        food.setName("jedzonko");
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setName("jedzonko");
        Mockito.when(foodMap.map(food)).thenReturn(foodEntity);

        assertEquals(food.getName(), foodEntity.getName());
    }

    @Test
    public void shouldCheckPriceCounter(){
        OwnerEntity ownerEntity = new OwnerEntity();
        PetEntity petEntity = new PetEntity();
        RoomEntity roomEntity = new RoomEntity();

        roomEntity.setPrice(BigDecimal.TEN);
        petEntity.setRoomEntity(roomEntity);
        petEntity.setDateIn(new Date(2017,10,10));
        petEntity.setDateOut(new Date(2017,10,11));
        ownerEntity.setPetList(Arrays.asList(petEntity));
        ownerEntity.setOwnerCategory(OwnerCategory.VIP);
        Client client = new Client();
        client.setPetNumbers(1);
        client.setWholePrice(BigDecimal.TEN);

        List<Client> clientList = new ArrayList<>();
        clientList.add(client);

        Mockito.when(ownerService.getAllClients()).thenReturn(clientList);
        assertEquals(clientList.get(0).getWholePrice(), BigDecimal.TEN);
    }
}