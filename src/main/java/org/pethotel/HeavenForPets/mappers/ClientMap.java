package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Client;
import org.pethotel.HeavenForPets.entity.OwnerEntity;

public interface ClientMap {
    Client map(OwnerEntity ownerEntity);
}
