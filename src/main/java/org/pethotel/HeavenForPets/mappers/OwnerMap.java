package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.entity.OwnerEntity;

/**
 * Created by Paulina on 2017-10-04.
 */
public interface OwnerMap {
    OwnerEntity map(Owner owner);
}