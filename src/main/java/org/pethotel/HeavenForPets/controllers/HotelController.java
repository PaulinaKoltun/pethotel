package org.pethotel.HeavenForPets.controllers;

import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.pethotel.HeavenForPets.service.PetService;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Paulina on 2017-09-27.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/hotel")
public class HotelController implements ErrorController{
    private static final String PATH = "/error";

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PetService petService;

    @PostMapping("/add")
    public void addPets(@RequestBody Owner owner){
        ownerService.saveOwner(owner);
        System.out.println(owner);
        System.out.println(owner.getAddress());
    }

    @GetMapping("/get")
    public List<Pet> getPets(){
        return petService.getPets();
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

