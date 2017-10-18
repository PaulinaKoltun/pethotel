package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Address;
import org.pethotel.HeavenForPets.entity.AddressEntity;
import org.pethotel.HeavenForPets.mappers.AddressMap;
import org.pethotel.HeavenForPets.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Paulina on 2017-10-13.
 */
public class AddressMapImpl implements AddressMap {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public AddressEntity mapAddress(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(address.getCity());
        addressEntity.setNumberofFlat(address.getNumberofFlat());
        addressEntity.setNumberofHouse(address.getNumberofHouse());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setZipCode(address.getZipCode());

        return addressEntity;
    }
}
