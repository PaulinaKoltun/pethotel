package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Owner;

import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */
public interface OwnerService {
    void saveOwner(Owner owner);
    List<String> getAllClients();
}
