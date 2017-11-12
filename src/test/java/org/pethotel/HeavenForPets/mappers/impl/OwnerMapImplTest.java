package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Address;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.AddressEntity;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;
import org.pethotel.HeavenForPets.mappers.AddressMap;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.AddressRepository;
import org.pethotel.HeavenForPets.repository.RoomRepository;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Created by Paulina on 2017-10-04.
 */
@RunWith(MockitoJUnitRunner.class)
public class OwnerMapImplTest {

    @Mock
    private PetMap petMap;
    @Mock
    private AddressMap addressMap;
    @Mock
    private RoomRepository roomRepository;
    @Mock
    private AddressRepository addressRepository;

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

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(city);
        Mockito.when(addressMap.mapAddress(address)).thenReturn(addressEntity);

        OwnerEntity ownerEntity = ownerMap.map(owner);

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
    }
}