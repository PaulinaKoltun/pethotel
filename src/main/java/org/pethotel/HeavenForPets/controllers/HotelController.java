package org.pethotel.HeavenForPets.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.domein.Pet.Animal;
import org.pethotel.HeavenForPets.domein.Pet.Plant;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.pethotel.HeavenForPets.service.PetService;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Paulina on 2017-09-27.
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/hotel")
public class HotelController implements ErrorController{

    private static final Logger LOGGER = LogManager.getLogger(HotelController.class);

    private static final String PATH = "/error";

    @Autowired
    private OwnerService ownerService;

    @Autowired
    private PetService petService;

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public void addPets(@RequestBody Owner owner){
        LOGGER.info("Add Owner with data: {}", owner);
        ownerService.saveOwner(owner);
    }

    @PutMapping("/updateDiscountAtOwner/{lastName}/{newDiscount}")
    public void updateDiscountAtOwner(@PathVariable String lastName, @PathVariable int newDiscount) {
        ownerService.updateDiscountAtOwner(lastName, newDiscount);
    }

    @GetMapping("/getAllRoomsByType/{petType}")
    public List<Room> getAllRoomsByType(@PathVariable String petType){
        return roomService.getAllRoomsByType(petType);
    }

    @GetMapping("/getAnimals")
    public List<Animal> getPets(){
        return petService.getAnimals();
    }

    @GetMapping("/getPlants")
    public List<Plant> getPlants() {
        return petService.getPlants();
    }

    @GetMapping("/tempRange/{plantInsolation}/{minTemp}/{maxTemp}")
    public List<Room> getRoomsForTemperatureRange(@PathVariable String plantInsolation, @PathVariable int minTemp,
                                                  @PathVariable int maxTemp) {
        LOGGER.info("getRoomsForTemperatureRange " + plantInsolation + " " + minTemp + " " + maxTemp);
        return roomService.getRoomsForTemperatureRange(plantInsolation, minTemp, maxTemp);
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

