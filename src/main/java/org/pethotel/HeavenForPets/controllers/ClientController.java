package org.pethotel.HeavenForPets.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pethotel.HeavenForPets.domein.Client;
import org.pethotel.HeavenForPets.domein.Pet;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.pethotel.HeavenForPets.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * Created by Paulina on 2017-10-19.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/clients")
public class ClientController implements ErrorController {
    private static final String PATH = "/error";

    private static final Logger LOGGER = LogManager.getLogger(ClientController.class);

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PetService petService;

    @GetMapping("/getAllClients")
    public List<Client> getAllClients(){
        return ownerService.getAllClients();
    }

    @PostMapping("addPetToOwner/{id}")
    public void addPetToOwner(@PathVariable Long id, @RequestBody Pet pet){
        petService.addPetToOwner(id, pet);
    }

    @PostMapping("/bringPetAgain")
    public void bringPetAgain(@RequestBody Pet pet ){
        petService.bringPetAgain(pet);
    }


    @GetMapping("/showAllPets/{id}")
    public List<Pet> showAllPets(@PathVariable int id){
        List<Pet> pets = ownerService.showAllPets(id);
        return pets;
    }

    @PostMapping("/pickupAllPets/{id}")
    public void pickupAllPets(@PathVariable int id){
        ownerService.pickupAllPets(id);
    }

    @PutMapping("/pickupPets")
    public BigDecimal pickupPets(@RequestBody List<Integer> idList) {
        return petService.pickupPets(idList);
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
