package org.pethotel.HeavenForPets.controllers;

import org.pethotel.HeavenForPets.domein.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by Paulina on 2017-10-07.
 */
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/getAllRooms")
    public List<Room> getAllRooms(){
        return roomService.getAllRooms();
    }

    @GetMapping("/findByRoomNumber/{roomNumber}")
    public RoomEntity findByRoomNumber(@PathVariable int roomNumber) {
        return roomService.findByRoomNumber(roomNumber);
    }

    @GetMapping("/freePlacesForPetType")
    public Map<PetType,Integer> freePlacesForPetType(){
        return roomService.freePlacesForPetType();
    }

    @PutMapping("/updateRoom")
    public void updateRoom(@RequestBody Room room) {
        roomService.updateRoom(room);
    }

    @DeleteMapping("/{roomNumber}")
    public void deleteRoom(@PathVariable int roomNumber){
        roomService.deleteRoom(roomNumber);
    }
}
