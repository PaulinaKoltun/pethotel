package org.pethotel.HeavenForPets.utils.fasade;

import org.pethotel.HeavenForPets.domein.FoodDetails;

public interface Generator {
    String generate(FoodDetails foodDetails);
}
