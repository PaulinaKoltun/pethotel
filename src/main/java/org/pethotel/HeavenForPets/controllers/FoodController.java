package org.pethotel.HeavenForPets.controllers;

import com.itextpdf.text.Document;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pethotel.HeavenForPets.domein.FoodDetails;
import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/food")
public class FoodController implements ErrorController{
    private static final String PATH = "/error";

    private static final Logger LOGGER = LogManager.getLogger(FoodController.class);

    @Autowired
    private FoodService foodService;

    @PostMapping("/addfood")
    public void addNewFood(@RequestBody List<Food> foodlist){
        foodService.saveFood(foodlist);
    }

    @GetMapping("/getfood/{petType}")
    public List<Food> getFoodForPet(@PathVariable String petType){
        List<Food> food = foodService.getFoodByPet(petType);
        LOGGER.info("Zwrócona lista jedzenia: {}", food);
        return food;
    }

    @GetMapping("/getfood/id/{id}")
    public FoodDetails getFoodById(@PathVariable Integer id){
        return foodService.getFoodById(id);
    }


    @RequestMapping(value = "/getfile/{id}", method = RequestMethod.GET)
    public void getFile( HttpServletRequest request,
                         HttpServletResponse response,
            @PathVariable Integer id) {

        foodService.getDetailsById(id);

        String fileName = "iTextHelloWorld.pdf";
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

    @RequestMapping(value = PATH)
    public String error() {
        return "Error handling";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
