package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;
import org.pethotel.HeavenForPets.mappers.OwnerMap;
import org.pethotel.HeavenForPets.repository.OwnerRepository;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */
@Service
@Transactional( rollbackFor = InvalidPetTypeException.class)
public class OwnerServiceImpl implements OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private OwnerMap ownerMap;

    @Override
    public void saveOwner(Owner owner) {
        try {
            OwnerEntity ownerEntity = ownerMap.map(owner);
            ownerRepository.save(ownerEntity);
        } catch (InvalidPetTypeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getAllClients() {
        List<OwnerEntity> ownerEntityList = (List<OwnerEntity>) ownerRepository.findAll();
        List<String[]> owners = new ArrayList<String[]>();
        for (OwnerEntity ownerEntity : ownerEntityList) {
            List<String> list = new ArrayList<>();
            String petListSize = String.valueOf(ownerEntity.getPetList().size());
            list.set(0, ownerEntity.getFirstName());
            list.set(1, ownerEntity.getLastName());
            list.set(2, petListSize);
            owners.add(list);
        }
        return owners;
    }
}
