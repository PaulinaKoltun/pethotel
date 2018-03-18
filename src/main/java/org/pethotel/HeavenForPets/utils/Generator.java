package org.pethotel.HeavenForPets.utils;

import com.itextpdf.text.Document;
import org.pethotel.HeavenForPets.domein.FoodDetails;

public interface Generator {
    String generate(FoodDetails foodDetails);
}
