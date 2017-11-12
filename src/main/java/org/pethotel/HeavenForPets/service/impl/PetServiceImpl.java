package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.repository.PetRepository;
import org.pethotel.HeavenForPets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina on 2017-10-04.
 */
@Service
public class PetServiceImpl implements PetService {
    @Autowired
    PetRepository petRepository;

    @Override
    public List<Pet> getPets() {
        List<PetEntity> petEntityList = (List<PetEntity>) petRepository.findAll();
        List<Pet> pets = new ArrayList<>();
        for (PetEntity petEntity : petEntityList) {
            Pet pet = new Pet();
            pet.setName(petEntity.getName());
            pet.setComment(petEntity.getComment());
            pets.add(pet);
        }
        return pets;
    }

}
