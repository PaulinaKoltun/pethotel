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
public class ChainGeneratorPdf implements ChainGenerator {

    @Autowired
    private Generator generatorPdf;

    @Autowired
    private Writer writerPdf;

    @Override
    public boolean isGenerate(HttpServletRequest request, HttpServletResponse response,
                              String file, FoodDetails foodDetails) {
        if ("PDF".equals(file.toUpperCase())) {
            preparePdf(request, response, foodDetails);
            return true;
        }
        return false;
    }

    private void preparePdf(HttpServletRequest request, HttpServletResponse response, FoodDetails foodDetails) {
        generatorPdf.generate(foodDetails);
        writerPdf.writer(request, response, foodDetails);
    }
}
