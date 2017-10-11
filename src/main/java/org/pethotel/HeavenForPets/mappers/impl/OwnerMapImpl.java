package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.mappers.OwnerMap;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Paulina on 2017-10-04.
 */
@Service
public class OwnerMapImpl implements OwnerMap {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public OwnerEntity map(Owner owner) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setFirstName(owner.getFirstName());
        ownerEntity.setLastName(owner.getLastName());
        ownerEntity.setCity(owner.getCity());
        ownerEntity.setStreet(owner.getStreet());
        ownerEntity.setNumberofHouse(owner.getNumberofHouse());
        ownerEntity.setNumberofFlat(owner.getNumberofFlat());
        ownerEntity.setZipCode(owner.getZipCode());
        ownerEntity.setOwnerCategory(owner.getOwnerCategory());

        List<PetEntity> petEntitys = new ArrayList<>();
        List<Pet> pets = owner.getPetList();

        for (Pet pet : pets) {
            PetEntity petEntity = new PetEntity();
            petEntity.setName(pet.getName());
            petEntity.setComment(pet.getComment());
            petEntity.setPetType(pet.getPetType());
            petEntity.setRoomEntity(roomRepository.getRoomByNumber(String.valueOf(pet.getRoomNumber())));
            petEntitys.add(petEntity);
        }

        ownerEntity.setPetList(petEntitys);

        return ownerEntity;
    }
}
