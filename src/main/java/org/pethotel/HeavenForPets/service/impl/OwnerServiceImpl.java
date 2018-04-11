package org.pethotel.HeavenForPets.service.impl;

import org.apache.logging.log4j.LogManager;
import org.pethotel.HeavenForPets.domein.Client;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;
import org.pethotel.HeavenForPets.mappers.OwnerMap;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.AddressRepository;
import org.pethotel.HeavenForPets.repository.OwnerRepository;
import org.pethotel.HeavenForPets.repository.PetRepository;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    private PetRepository petRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private OwnerMap ownerMap;

    @Autowired
    private PetMap petMap;

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

        return ownerEntityList.stream()
                .map(r->map(r))
                .collect(Collectors.toList());
    }

    private Client map(OwnerEntity ownerEntity) {
        Client client = new Client();
        client.setId(ownerEntity.getId());
        client.setFirstName(ownerEntity.getFirstName());
        client.setLastName(ownerEntity.getLastName());
        client.setPetNumbers(ownerEntity.getPetList().size());
        client.setWholePrice(getWholePriceAfterDiscount(ownerEntity));
        return client;
    }

    private BigDecimal getWholePriceAfterDiscount(OwnerEntity ownerEntity) {
        BigDecimal generalPrice = countWholePrice(ownerEntity.getPetList());
        BigDecimal priceOfDiscount = generalPrice.multiply(new BigDecimal(ownerEntity.getDiscount()));
        BigDecimal finalPrice = generalPrice.subtract(priceOfDiscount);
        return finalPrice;
    }

    private BigDecimal countWholePrice(List<PetEntity> petList) {
        BigDecimal wholePrice = BigDecimal.ZERO;
        for (PetEntity petEntity : petList) {
            if (null!=petEntity.getRoomEntity()) {
                BigDecimal daysOfVisit = new BigDecimal(getDaysOfVisit(petEntity));
                wholePrice = (wholePrice.add(petEntity.getRoomEntity().getPrice().multiply(daysOfVisit)));
            }
        }
        return wholePrice;
    }

    private long getDaysOfVisit(PetEntity petEntity) {
        long difference = Math.abs(petEntity.getDateOut().getTime() - petEntity.getDateIn().getTime());
        return  difference / (24 * 60 * 60 * 1000);
    }

    @Override
    public List<Pet> showAllPets(int id) {
        OwnerEntity ownerEntity = ownerRepository.findOne(Long.valueOf(id));
        List<PetEntity> petEntities = ownerEntity.getPetList();

        //for (PetEntity petEntity : petEntities)
        List<Pet> pets = petEntities.stream()
                .map(e -> getPet(e))
                .collect(Collectors.toList());

        return pets;
    }

    private Pet getPet(PetEntity e) {
        Pet pet = new Pet();
        pet.setName(e.getName());
        pet.setComment(e.getComment());
        pet.setPetType(e.getPetType());
        pet.setDateIn(e.getDateIn());
        pet.setDateOut(e.getDateOut());
        pet.setDinner((int) e.getDinner().getId());
        pet.setSupper((int) e.getSupper().getId());
        pet.setBreakfast((int) e.getBreakfast().getId());

        RoomEntity roomEntity = e.getRoomEntity();
        pet.setRoomNumber(roomEntity != null ? roomEntity.getRoomNumber() : 0);

        return pet;
    }

    @Override
    @Transactional
    public void pickupAllPets(int id) {
        OwnerEntity ownerEntity = ownerRepository.findOne(Long.valueOf(id));
        List<PetEntity> petEntities = ownerEntity.getPetList();

        for (PetEntity petEntity : petEntities) {
            petEntity.setPresent(0);

            RoomEntity roomEntity = petEntity.getRoomEntity();
            int newFreePlaces = roomEntity.getFreePlaces()+1;
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
    public void bringPetAgain(Pet pet) {
        PetEntity petEntity = petRepository.findOne(pet.getId());
        petEntity.setPresent(1);
        RoomEntity roomEntity = roomRepository.getRoomByNumber(pet.getRoomNumber());
        petMap.map(pet, petEntity, roomEntity);
        petRepository.save(petEntity);
    }

    @Override
    public void pickupOnePet(int id) {
        PetEntity petEntity = petRepository.findOne((long) id);

        petEntity.setPresent(0);
        RoomEntity roomEntity = petEntity.getRoomEntity();
        int newFreePlaces = roomEntity.getFreePlaces()+1;
        roomEntity.setFreePlaces(newFreePlaces);

        petEntity.setRoomEntity(null);
        petRepository.save(petEntity);
    }

    @Override
    public void addPetToOwner(Long id, Pet pet) {
        OwnerEntity ownerEntity = ownerRepository.findOne(id);
        List<PetEntity> pets = ownerEntity.getPetList();
        PetEntity petEntity = petMap.map(pet, roomRepository.getRoomByNumber(pet.getRoomNumber()));
        pets.add(petEntity);
        ownerEntity.setPetList(pets);
        petRepository.save(petEntity);
    }

}