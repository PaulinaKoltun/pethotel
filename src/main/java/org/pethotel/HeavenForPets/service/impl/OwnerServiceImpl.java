package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Client;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;
import org.pethotel.HeavenForPets.mappers.OwnerMap;
import org.pethotel.HeavenForPets.repository.AddressRepository;
import org.pethotel.HeavenForPets.repository.OwnerRepository;
import org.pethotel.HeavenForPets.repository.PetRepository;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */

@Service
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OwnerMap ownerMap;

    @Override
    public void saveOwner(Owner owner) {
        try {
            OwnerEntity ownerEntity = ownerMap.map(owner);
            ownerRepository.save(ownerEntity);
            addressRepository.save(ownerEntity.getAddressEntity());
        } catch (InvalidPetTypeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Client> getAllClients() {
        List<OwnerEntity> ownerEntityList = (List<OwnerEntity>) ownerRepository.findAll();
        List<Client> clients = new ArrayList<>();
        for (OwnerEntity ownerEntity : ownerEntityList) {
            Client client = new Client();
            client.setId(ownerEntity.getId());
            client.setFirstName(ownerEntity.getFirstName());
            client.setLastName(ownerEntity.getLastName());
            client.setPetNumbers(ownerEntity.getPetList().size());
            client.setWholePrice(getWholePriceAfterDiscount(ownerEntity));
            clients.add(client);
        }
        return clients;
    }

    private int getWholePriceAfterDiscount(OwnerEntity ownerEntity) {
        return countWholePrice(ownerEntity.getPetList()) -
                (ownerEntity.getDiscount()*
                countWholePrice(ownerEntity.getPetList())/100);
    }

    private int countWholePrice(List<PetEntity> petList) {
        int wholePrice = 0;
        for (PetEntity petEntity : petList) {
            long daysOfVisit = getDaysOfVisit(petEntity);
            wholePrice = (int) (wholePrice + petEntity.getRoomEntity().getPrice()* daysOfVisit);
        }
        return wholePrice;
    }

    private long getDaysOfVisit(PetEntity petEntity) {
        long difference = Math.abs(petEntity.getDateOut().getTime() - petEntity.getDateIn().getTime());
        return difference / (24 * 60 * 60 * 1000);
    }

    @Override
    public List<Pet> showAllPets(int id) {
        OwnerEntity ownerEntity = ownerRepository.findOne(Long.valueOf(id));
        List<PetEntity> petEntities = ownerEntity.getPetList();
        List<Pet> pets = new ArrayList<>();

        for (PetEntity petEntity : petEntities) {
            Pet pet = new Pet();
            pet.setName(petEntity.getName());
            pet.setComment(petEntity.getComment());
            pet.setPetType(petEntity.getPetType());
            pet.setDateIn(petEntity.getDateIn());
            pet.setDateOut(petEntity.getDateOut());

            RoomEntity roomEntity = petEntity.getRoomEntity();
            pet.setRoomNumber(roomEntity.getRoomNumber());

            pets.add(pet);
        }

        return pets;
    }

    @Override
    @Transactional
    public void deleteAllPets(int id) {
        OwnerEntity ownerEntity = ownerRepository.findOne(Long.valueOf(id));
        List<PetEntity> petEntities = ownerEntity.getPetList();
        ownerEntity.setPetList(Collections.emptyList());
        ownerRepository.save(ownerEntity);
        for (PetEntity petEntity : petEntities) {
            petRepository.delete(petEntity);
        }
    }

    @Override
    public void updateDiscountAtOwner(String lastName, int newDiscount) {
        OwnerEntity ownerEntity = ownerRepository.getOwnerByLastName(lastName);
        ownerEntity.setDiscount(newDiscount);
        ownerRepository.save(ownerEntity);
    }

}