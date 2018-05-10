package org.pethotel.HeavenForPets.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pethotel.HeavenForPets.domein.Shelf;
import org.pethotel.HeavenForPets.service.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/shelf")
public class ShelfController {

    private static final Logger LOGGER = LogManager.getLogger(HotelController.class);

    private static final String PATH = "/error";

    @Autowired
    private ShelfService shelfService;

    @PostMapping("/add/{id}")
    public void addShelves(@RequestBody List<Shelf> shelves, @PathVariable int id){
        shelfService.saveShelves(shelves, id);
    }

    @GetMapping("/getShelvesFromRoom/{id}")
    public List<Shelf> getShelvesFromRoom(@PathVariable int id){
        return shelfService.getShelvesFromRoom(id);
    }

    @GetMapping("/getFreeShelvesInRoom/{id}")
    public List<Shelf> getFreeShelves(@PathVariable int id){
        return shelfService.getFreeShelves(id);
    }
}
