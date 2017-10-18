package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Address;
import org.pethotel.HeavenForPets.entity.AddressEntity;

/**
 * Created by Paulina on 2017-10-13.
 */
public interface AddressMap {
    AddressEntity mapAddress(Address address);
}
