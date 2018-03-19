package org.pethotel.HeavenForPets.utils.impl;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.service.FoodService;
import org.pethotel.HeavenForPets.service.impl.FoodServiceImpl;
import org.pethotel.HeavenForPets.utils.Generator;
import org.pethotel.HeavenForPets.utils.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class WriterPdf implements Writer {

    private static final Logger LOGGER = LogManager.getLogger(FoodServiceImpl.class);

    @Autowired
    public Generator generatorPdf;

    @Override
    public void writer(HttpServletRequest request,
                       HttpServletResponse response,
                       FoodDetails foodDetails) {

        String fileName = generatorPdf.generate(foodDetails);

        String dataDirectory = request.getServletContext().getRealPath("/WEB-INF/downloads");
        Path file = Paths.get(dataDirectory, fileName);
        LOGGER.info("File " + dataDirectory);
        if (Files.exists(file)) {
            response.setContentType("application/pdf");
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

