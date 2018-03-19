package org.pethotel.HeavenForPets.utils.fasade;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.pethotel.HeavenForPets.domein.FoodDetails;

import javax.servlet.http.HttpServletResponse;

public interface Writer {
    void writer(HttpServletRequest request,
                HttpServletResponse response,
                FoodDetails foodDetails);
}
