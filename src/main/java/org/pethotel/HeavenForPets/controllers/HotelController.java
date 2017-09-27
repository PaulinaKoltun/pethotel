package org.pethotel.HeavenForPets.controllers;

import org.pethotel.HeavenForPets.domein.Pet;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Paulina on 2017-09-27.
 */
@RestController
public class HotelController implements ErrorController{
    private static final String PATH = "/error";

    @PostMapping(value = "/{name}/{firstName}/{lastName}")
    public void addPet(@PathVariable String name,@PathVariable String firstName,@PathVariable String lastName){
        Pet pet = new Pet(name, firstName, lastName);
        System.out.println(pet);
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

