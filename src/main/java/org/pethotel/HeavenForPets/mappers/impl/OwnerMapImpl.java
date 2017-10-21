package org.pethotel.HeavenForPets.mappers.impl;

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
import org.pethotel.HeavenForPets.mappers.OwnerMap;
import org.pethotel.HeavenForPets.repository.AddressRepository;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina on 2017-10-04.
 */
@Service
public class OwnerMapImpl implements OwnerMap {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public OwnerEntity map(Owner owner) throws InvalidPetTypeException {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setFirstName(owner.getFirstName());
        ownerEntity.setLastName(owner.getLastName());
        ownerEntity.setOwnerCategory(owner.getOwnerCategory());

        List<PetEntity> petEntitys = new ArrayList<>();
        List<Pet> pets = owner.getPetList();

        for (Pet pet : pets) {
            PetEntity petEntity = new PetEntity();
            petEntity.setName(pet.getName());
            petEntity.setComment(pet.getComment());
            PetType petType = pet.getPetType();
            RoomEntity roomByNumber = roomRepository.getRoomByNumber(pet.getRoom().getRoomNumber());
            if (petType != roomByNumber.getPetType()) {
                throw new InvalidPetTypeException();
            }
            petEntity.setPetType(pet.getPetType());

            // FIXME jeżeli przyjdzie json więcej zwierząt niż miejsc w pokoju, bedzie wartość ujemna
            petEntity.setRoomEntity(roomByNumber);
            roomByNumber.setFreePlaces(roomByNumber.getFreePlaces()-1);
            roomRepository.save(roomByNumber);

            petEntitys.add(petEntity);
        }

        ownerEntity.setPetList(petEntitys);

        AddressEntity addressEntity = new AddressEntity();
        Address address = owner.getAddress();
        addressEntity.setCity(address.getCity());
        addressEntity.setNumberofFlat(address.getNumberofFlat());
        addressEntity.setNumberofHouse(address.getNumberofHouse());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setZipCode(address.getZipCode());
        ownerEntity.setAddressEntity(addressEntity);

        return ownerEntity;
    }
}
