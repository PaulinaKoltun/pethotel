package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Pet;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Paulina on 2017-10-04.
 */
public interface PetService {
    List<Pet> getPets();
    void bringPetAgain(Pet pet);
    BigDecimal pickupPets(List<Integer> idList);

    void addPetToOwner(Long id, Pet pet);
}
