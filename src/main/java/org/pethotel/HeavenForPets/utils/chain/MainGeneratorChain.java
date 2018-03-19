package org.pethotel.HeavenForPets.utils.chain;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.utils.chain.impl.ChainGeneratorCsv;
import org.pethotel.HeavenForPets.utils.chain.impl.ChainGeneratorPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Component
public class MainGeneratorChain {

    private List<ChainGenerator> chainGenerator = new ArrayList<>();

    @Autowired
    private MainGeneratorChain(ChainGenerator chainGeneratorPdf,
                               ChainGenerator chainGeneratorCsv) {
        chainGenerator.add(chainGeneratorPdf);
        chainGenerator.add(chainGeneratorCsv);
    }

    public void makeResponse(HttpServletRequest request, HttpServletResponse response,
                             String file, FoodDetails foodDetails) {
        for (ChainGenerator generator : chainGenerator) {
            if (generator.isGenerate(request, response, file, foodDetails)) break;
        }
    }
}
