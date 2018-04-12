package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Client;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.entity.PetEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */
public interface OwnerService {
    void saveOwner(Owner owner);
    List<Client> getAllClients();
    List<Pet> showAllPets(int id);
    void pickupAllPets(int id);
    void updateDiscountAtOwner(String lastName, int newDiscount);
    void addPetToOwner(Long id, Pet pet);
    void bringPetAgain(Pet pet);
    BigDecimal pickupPets(List<Integer> idList);
}
