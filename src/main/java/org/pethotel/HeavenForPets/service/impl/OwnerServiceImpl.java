package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.repository.OwnerRepository;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */
@Service
public class OwnerServiceImpl implements OwnerService{

    @Autowired
    private OwnerRepository ownerRepository;

    @Override
    public void saveOwner(Owner owner) {
        OwnerEntity ownerEntity = new OwnerEntity();
        ownerEntity.setFirstName(owner.getFirstName());
        ownerEntity.setLastName(owner.getLastName());
        ownerEntity.setCity(owner.getCity());
        ownerEntity.setStreet(owner.getStreet());
        ownerEntity.setNumberofHouse(owner.getNumberofHouse());
        ownerEntity.setNumberofFlat(owner.getNumberofFlat());
        ownerEntity.setZipCode(owner.getZipCode());

        List<PetEntity> petEntitys = new ArrayList<>();
        List<Pet> pets = owner.getPetList();

        for (Pet pet : pets) {
            PetEntity petEntity = new PetEntity();
            petEntity.setName(pet.getName());
            petEntitys.add(petEntity);
        }

        ownerEntity.setPetList(petEntitys);
        ownerRepository.save(ownerEntity);
    }
}
