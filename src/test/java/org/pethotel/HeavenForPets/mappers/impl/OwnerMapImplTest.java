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
        pet.setRoomNumber(1);
        pet.setPetType(PetType.BIRD);
        pet.setName("Ara");
        pet.setComment("nn");
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
        address.setCity("Wroclaw");
        owner.setAddress(address);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity("Wroclaw");
        Mockito.when(addressMap.mapAddress(address)).thenReturn(addressEntity);

        OwnerEntity ownerEntity = ownerMap.map(owner);

        assertEquals(owner.getAddress().getCity(),ownerEntity.getAddressEntity().getCity());
    }

    @Test
    public void shouldCheckPetsCounter() throws InvalidPetTypeException{
        Owner owner = new Owner();
        Pet pet = new Pet();
        pet.setRoomNumber(1);
        pet.setPetType(PetType.BIRD);
        pet.setName("Ara");
        pet.setComment("Komentarz");

        owner.setPetList(Arrays.asList(pet));

        PetEntity petEntity = new PetEntity();
        petEntity.setPetType(PetType.BIRD);

        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setPetType(PetType.BIRD);
        roomEntity.setFreePlaces(5);

        Mockito.when(petMap.map(pet)).thenReturn(petEntity);
        Mockito.when(roomRepository.getRoomByNumber(Mockito.anyInt()))
                .thenReturn(roomEntity);

        OwnerEntity ownerEntity = ownerMap.map(owner);

        assertEquals(roomEntity.getFreePlaces(), 4);
    }
}