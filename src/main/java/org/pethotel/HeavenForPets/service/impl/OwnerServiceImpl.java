package org.pethotel.HeavenForPets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.pethotel.HeavenForPets.domein.Client;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet.Pet;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;
import org.pethotel.HeavenForPets.mappers.ClientMap;
import org.pethotel.HeavenForPets.mappers.OwnerMap;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.OwnerRepository;
import org.pethotel.HeavenForPets.service.AddressService;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Paulina on 2017-09-30.
 */

@Service
public class OwnerServiceImpl implements OwnerService {
    private static final org.apache.logging.log4j.Logger LOGGER = LogManager.getLogger(OwnerServiceImpl.class);

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private ClientMap clientMap;

    @Autowired
    private OwnerMap ownerMap;

    @Autowired
    private PetMap petMap;

    @Autowired
    private AddressService addressService;

    @Override
    public void saveOwner(Owner owner) {
        try {
            OwnerEntity ownerEntity = ownerMap.map(owner);
            ownerRepository.save(ownerEntity);
            addressService.saveAddress(owner.getAddress());
        } catch (InvalidPetTypeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<OwnerEntity> ownerEntityList = (List<OwnerEntity>) ownerRepository.findAll();

        return ownerEntityList.stream()
                .map(r -> clientMap.map(r))
                .collect(Collectors.toList());
    }

    @Override
    public List<Pet> showAllPets(int id) {
        OwnerEntity ownerEntity = ownerRepository.findOne((long) id);
        List<PetEntity> petEntities = ownerEntity.getPetList();

        List<Pet> pets = petEntities.stream()
                .map(e -> petMap.map(e))
                .collect(Collectors.toList());

        return pets;
    }

    @Override
    @Transactional
    public void pickupAllPets(int id) {
        OwnerEntity ownerEntity = ownerRepository.findOne(Long.valueOf(id));
        List<PetEntity> petEntities = ownerEntity.getPetList();

        for (PetEntity petEntity : petEntities) {
            petEntity.setPresent(0);

            RoomEntity roomEntity = petEntity.getRoomEntity();
            int newFreePlaces = roomEntity.getFreePlaces() + 1;
            roomEntity.setFreePlaces(newFreePlaces);

            petEntity.setRoomEntity(null);
        }
    }

    @Override
    public void updateDiscountAtOwner(String lastName, int newDiscount) {
        OwnerEntity ownerEntity = ownerRepository.getOwnerByLastName(lastName);
        ownerEntity.setDiscount(newDiscount);
        ownerRepository.save(ownerEntity);
    }

    @Override
    public OwnerEntity getOwnerById(Long id) {
        return ownerRepository.findOne(id);
    }
}