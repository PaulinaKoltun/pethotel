package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Pet.Animal;
import org.pethotel.HeavenForPets.domein.Pet.Pet;
import org.pethotel.HeavenForPets.domein.Pet.Plant;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Paulina on 2017-10-04.
 */
public interface PetService {
    List<Animal> getAnimals();
    void bringPetAgain(Pet pet);
    BigDecimal pickupPets(List<Integer> idList);
    void addPetsToOwner(Long id, List<Pet> pet);
    List<Plant> getPlants();
}
