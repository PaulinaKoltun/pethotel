package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Address;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.AddressEntity;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;
import org.pethotel.HeavenForPets.mappers.AddressMap;
import org.pethotel.HeavenForPets.mappers.OwnerMap;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;
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
    private RoomRepository roomRepository;

    @Override
    public OwnerEntity map(Owner owner) throws InvalidPetTypeException {
        Map<RoomEntity, Integer> roomMap = new HashMap<>();
        List<PetEntity> petEntitys = new ArrayList<>();

        for (Pet pet : owner.getPetList()) {
            PetEntity petEntity = petMap.map(pet);

            RoomEntity roomByNumber =
                    roomRepository.getRoomByNumber(pet.getRoomNumber());
            validatePetType(petEntity, roomByNumber);
            petEntity.setRoomEntity(roomByNumber);

            countPetsInRoom(roomMap, roomByNumber);

            petEntitys.add(petEntity);
        }

        updateFreeRoomsInDB(roomMap);

        OwnerEntity ownerEntity = getOwnerEntity(owner);
        ownerEntity.setPetList(petEntitys);

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

    private void validatePetType(PetEntity petEntity, RoomEntity roomByNumber) throws InvalidPetTypeException {
        if (roomByNumber.getPetType() != petEntity.getPetType()){
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

            roomRepository.save(roomEntity);
        }
    }



}
