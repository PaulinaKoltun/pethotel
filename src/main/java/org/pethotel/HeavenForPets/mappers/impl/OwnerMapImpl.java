package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Address;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet.Animal;
import org.pethotel.HeavenForPets.domein.Pet.Pet;
import org.pethotel.HeavenForPets.domein.Pet.Plant;
import org.pethotel.HeavenForPets.entity.AddressEntity;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;
import org.pethotel.HeavenForPets.mappers.AddressMap;
import org.pethotel.HeavenForPets.mappers.OwnerMap;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Paulina on 2017-10-04.
 */
@Service
public class OwnerMapImpl implements OwnerMap {

    @Autowired
    private PetMap petMap;

    @Autowired
    private AddressMap addressMap;

    @Autowired
    private RoomService roomService;


    @Override
    public OwnerEntity map(Owner owner) throws InvalidPetTypeException {
        Map<RoomEntity, Integer> roomMap = new HashMap<>();
        List<PetEntity> petEntities = new ArrayList<>();

        for (Pet pet : owner.getPetList()) {
            RoomEntity roomByNumber =
                    roomService.getRoomByNumber(pet.getRoomNumber());
            validatePetType(pet, roomByNumber);

            PetEntity petEntity = petMap.map(pet, roomByNumber);

            countPetsInRoom(roomMap, roomByNumber); //zmienić

            petEntities.add(petEntity);
        }

        updateFreeRoomsInDB(roomMap);

        OwnerEntity ownerEntity = getOwnerEntity(owner);
        ownerEntity.setPetList(petEntities);

        Address address = owner.getAddress();
        AddressEntity addressEntity = addressMap.mapAddress(address);
        ownerEntity.setAddressEntity(addressEntity);

        return ownerEntity;
    }

    private OwnerEntity getOwnerEntity(Owner owner) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setFirstName(owner.getFirstName());
        ownerEntity.setLastName(owner.getLastName());
        ownerEntity.setOwnerCategory(owner.getOwnerCategory());
        ownerEntity.setDiscount(owner.getOwnerCategory().getDiscount());
        return ownerEntity;
    }

    private void countPetsInRoom(Map<RoomEntity, Integer> roomMap, RoomEntity roomByNumber) {
        if (roomMap.containsKey(roomByNumber)) {
            Integer newFreePlaces = roomMap.get(roomByNumber);
            roomMap.replace(roomByNumber, ++newFreePlaces);
        } else {
            roomMap.put(roomByNumber, 1);
        }
    }

    private void validatePetType(Pet pet, RoomEntity roomByNumber) throws InvalidPetTypeException {
        if (roomByNumber.getPetType() != null &&
                roomByNumber.getPetType() != ((Animal) pet).getPetType()){
            throw new InvalidPetTypeException();
        }
    }

    @Override
    public Owner map(OwnerEntity ownerEntity) {
        return null;
    }

    private void updateFreeRoomsInDB(Map<RoomEntity, Integer> roomMap) {
        for (Map.Entry<RoomEntity, Integer> roomEntityIntegerEntry : roomMap.entrySet()) {

            RoomEntity roomEntity = roomEntityIntegerEntry.getKey();
            Integer oldValue = roomEntity.getFreePlaces();
            Integer newValue = oldValue - roomEntityIntegerEntry.getValue();
            roomEntity.setFreePlaces(newValue);

            roomService.saveRoomEntity(roomEntity);
        }
    }



}
