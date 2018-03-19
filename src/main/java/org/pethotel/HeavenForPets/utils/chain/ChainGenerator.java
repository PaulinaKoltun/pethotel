package org.pethotel.HeavenForPets.utils.chain;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.pethotel.HeavenForPets.domein.FoodDetails;

import javax.servlet.http.HttpServletResponse;

public interface ChainGenerator {
    boolean isGenerate(HttpServletRequest request,
                       HttpServletResponse response,
                       String file,
                       FoodDetails foodDetails);
}
