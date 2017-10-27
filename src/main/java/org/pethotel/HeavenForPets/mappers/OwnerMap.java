package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;

/**
 * Created by Paulina on 2017-10-04.
 */
public interface OwnerMap {
    OwnerEntity map(Owner owner) throws InvalidPetTypeException;
    Owner map(OwnerEntity ownerEntity);
}
