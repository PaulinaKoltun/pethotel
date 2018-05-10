package org.pethotel.HeavenForPets.utils.fasade.impl;

import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.utils.fasade.Generator;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class GeneratorCsv  implements Generator {

    private static final String filename = "iTextHelloWorld.csv";

    @Override
    public String generate(FoodDetails foodDetails) {
        try {
            FileWriter writer = new FileWriter("src/main/webapp/WEB-INF/downloads/" + filename);

            StringBuilder sb = new StringBuilder();

            sb.append(foodDetails.getFood().getName());
            sb.append(";");
            sb.append(foodDetails.getFood().getAmount());
            sb.append(";");
            sb.append(foodDetails.getFood().getFoodType());
            sb.append("\n");
            sb.append(foodDetails.getDeliveryAmount());
            sb.append("\n");
            sb.append(foodDetails.getDeliveryDate());
            writer.append(sb.toString());
            writer.flush();
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return filename;
    }
}
