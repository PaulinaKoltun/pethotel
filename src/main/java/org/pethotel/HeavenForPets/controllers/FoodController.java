package org.pethotel.HeavenForPets.controllers;


import org.pethotel.HeavenForPets.domein.Food;
import org.pethotel.HeavenForPets.service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/food")
public class FoodController implements ErrorController{
    private static final String PATH = "/error";

    @Autowired
    private FoodService foodService;

    @PostMapping("/addfood")
    public void addNewFood(@RequestBody Food food){
        foodService.saveFood(food);
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
