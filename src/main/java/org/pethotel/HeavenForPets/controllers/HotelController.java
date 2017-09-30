package org.pethotel.HeavenForPets.controllers;

import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Paulina on 2017-09-27.
 */
@RestController
public class HotelController implements ErrorController{
    private static final String PATH = "/error";

    @Autowired
    private OwnerService ownerService;

    @PostMapping(value = "/{name}/{firstName}/{lastName}/{city}/{street}/{numberofHouse}/{numberofFlat}/{zipCode}")
    public void addPet(@PathVariable String name,@PathVariable String firstName,@PathVariable String lastName, @PathVariable String city, @PathVariable String street, @PathVariable String numberofHouse, @PathVariable String numberofFlat, @PathVariable String zipCode){
        Pet pet = new Pet(name);
        Owner owner = new Owner(firstName, lastName, city, street, numberofHouse, numberofFlat, zipCode);
        owner.setPetList(pet);
        System.out.println(pet);
        System.out.println(owner);

        ownerService.saveOwner(owner);
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

