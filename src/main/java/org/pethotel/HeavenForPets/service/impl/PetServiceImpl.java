package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Pet.Animal;
import org.pethotel.HeavenForPets.domein.Pet.Pet;
import org.pethotel.HeavenForPets.domein.Pet.Plant;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.exceptions.DifferentOwnerException;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.PetRepository;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.pethotel.HeavenForPets.service.PetService;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.pethotel.HeavenForPets.utils.Calculator.getDaysOfVisit;

/**
 * Created by Paulina on 2017-10-04.
 */
@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private RoomService roomService;

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PetMap petMap;

    @Override
    public List<Animal> getPets() {
        List<PetEntity> petEntityList = petRepository.findAllPets(); //query dla animals
        List<Animal> pets = new ArrayList<>();
        for (PetEntity petEntity : petEntityList) {
            Pet pet = new Animal();
            pet.setName(petEntity.getName());
            pet.setComment(petEntity.getComment());
            pets.add((Animal) pet);
        }
        return pets;
    }

    @Override
    public void bringPetAgain(Pet pet) {
        PetEntity petEntity = petRepository.findOne(pet.getId());
        petEntity.setPresent(1);
        RoomEntity roomEntity = roomService.getRoomByNumber(pet.getRoomNumber());
        petMap.map(pet, petEntity, roomEntity);
        petRepository.save(petEntity);
    }

    private BigDecimal pickupOnePet(int id) {
        PetEntity petEntity = petRepository.findOne((long) id);

        petEntity.setPresent(0);
        RoomEntity roomEntity = petEntity.getRoomEntity();
        BigDecimal price = roomEntity.getPrice();
        price = price.multiply(BigDecimal.valueOf(getDaysOfVisit(petEntity)));

        int newFreePlaces = roomEntity.getFreePlaces()+1;
        roomEntity.setFreePlaces(newFreePlaces);

        petEntity.setRoomEntity(null);
        petRepository.save(petEntity);

        return price;
    }

    @Override
    public BigDecimal pickupPets(List<Integer> idList){
        BigDecimal wholePrice = BigDecimal.ZERO;

        try {
            validateTheOwnersForPets(idList);
        }catch (DifferentOwnerException e) {
            e.printStackTrace();
        }

        for (Integer id : idList) {
            BigDecimal price = pickupOnePet(id);
            wholePrice = wholePrice.add(price);
        }

        return wholePrice;
    }

    private void validateTheOwnersForPets(List<Integer> idList) throws DifferentOwnerException {
        List<Integer> ownerList = petRepository.ownerEntityList(idList);
        if (ownerList.size()!=1){
            throw new DifferentOwnerException();
        }
    }

    @Override
    public void addPetToOwner(Long id, List<Pet> pets) {
        OwnerEntity ownerEntity = ownerService.getOwnerById(id);
        List<PetEntity> ownerEntities = ownerEntity.getPetList();

        for (Pet pet : pets) {
            PetEntity petEntity =
                    petMap.map(pet,
                            roomService.getRoomByNumber(pet.getRoomNumber()));
            petRepository.save(petEntity);
            ownerEntities.add(petEntity);
        }

        ownerEntity.setPetList(ownerEntities);
    }

    @Override
    public List<Plant> getPlants() {
        List<PetEntity> plantsEntityList = petRepository.findAllPlants(); //query dla plants
        List<Plant> plants = new ArrayList<>();
        for (PetEntity petEntity : plantsEntityList) {
            Pet pet = new Plant();
            pet.setName(petEntity.getName());
            pet.setComment(petEntity.getComment());
            plants.add((Plant) pet);
        }
        return plants;
    }
}
