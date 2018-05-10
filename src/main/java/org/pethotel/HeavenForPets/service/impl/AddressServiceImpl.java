package org.pethotel.HeavenForPets.service.impl;

import org.pethotel.HeavenForPets.domein.Address;
import org.pethotel.HeavenForPets.entity.AddressEntity;
import org.pethotel.HeavenForPets.mappers.AddressMap;
import org.pethotel.HeavenForPets.repository.AddressRepository;
import org.pethotel.HeavenForPets.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Paulina on 2017-10-13.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Autowired
    AddressMap addressMap;

    @Override
    public void saveAddress(Address address) {
        AddressEntity addressEntity = addressMap.mapAddress(address);
        addressRepository.save(addressEntity);
    }
}
