package org.pethotel.HeavenForPets.utils.fasade.impl;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.service.impl.FoodServiceImpl;
import org.pethotel.HeavenForPets.utils.fasade.Generator;
import org.pethotel.HeavenForPets.utils.fasade.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class WriterCsv implements Writer {

    private static final Logger LOGGER = LogManager.getLogger(FoodServiceImpl.class);

    @Autowired
    public Generator generatorCsv;

    @Override
    public void writer(HttpServletRequest request, HttpServletResponse response, FoodDetails foodDetails) {

            String fileName = "iTextHelloWorld.csv";

            LOGGER.info("File " + fileName);
            String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads");
            Path file = Paths.get(dataDirectory, fileName);
            LOGGER.info("File1 " + dataDirectory);
            if (Files.exists(file)) {
                response.setContentType("text/csv");
                response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
                try {
                    LOGGER.info("Start send");
                    Files.copy(file, response.getOutputStream());
                    response.getOutputStream().flush();
                    LOGGER.info("end send");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
}
