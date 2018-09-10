package org.pethotel.HeavenForPets.domein;


import lombok.Data;
import org.pethotel.HeavenForPets.domein.Pet.Pet;
import org.pethotel.HeavenForPets.domein.Pet.Plant;
import org.pethotel.HeavenForPets.enums.OwnerCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */
public @Data class Owner implements Serializable {
    private String firstName;
    private String lastName;
    private OwnerCategory ownerCategory;
    private int discount;
    private List<Pet> petList = new ArrayList<>();
    private Address address;
}
