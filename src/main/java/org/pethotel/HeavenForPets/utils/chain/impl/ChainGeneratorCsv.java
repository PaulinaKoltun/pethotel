package org.pethotel.HeavenForPets.utils.chain.impl;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.utils.chain.ChainGenerator;
import org.pethotel.HeavenForPets.utils.fasade.Generator;
import org.pethotel.HeavenForPets.utils.fasade.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

@Component
public class ChainGeneratorCsv implements ChainGenerator {

    @Autowired
    private Generator generatorCsv;

    @Autowired
    private Writer writerCsv;

    @Override
    public boolean isGenerate(HttpServletRequest request, HttpServletResponse response,
                              String file, FoodDetails foodDetails) {
        if ("CSV".equals(file.toUpperCase())) {
            prepareCsv(request, response, foodDetails);
            return true;
        }
        return false;
    }

    private void prepareCsv(HttpServletRequest request, HttpServletResponse response, FoodDetails foodDetails) {
        generatorCsv.generate(foodDetails);
        writerCsv.writer(request, response, foodDetails);
    }
}
