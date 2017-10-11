package org.pethotel.HeavenForPets.controllers;

import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Paulina on 2017-10-07.
 */
@RestController
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/add")
    public void addRoom(@RequestBody Room room){
        roomService.saveRoom(room);
    }

    @GetMapping("/getAllNumbers")
    public List<Integer> getAllNumbers(){
        return roomService.getAllNumbers();
    }
}
