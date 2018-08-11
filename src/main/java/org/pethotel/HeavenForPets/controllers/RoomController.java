package org.pethotel.HeavenForPets.controllers;

import org.pethotel.HeavenForPets.domein.Rooms.PetRoom;
import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.exceptions.TemperatureWrongRangeException;
import org.pethotel.HeavenForPets.repository.RoomRepository;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/getNumberOfRooms")
    public int getNumberOfRooms(){
        return roomService.getNumberOfRooms();
    }

    @GetMapping("/getNumberOfRoomsFromQuery")
    public int getNumberOfRoomsFromQuery(){
        return roomService.getNumberOfRoomsFromQuery();
    }

    @GetMapping("/getAllNumbers")
    public List<Long> getAllNumbers(){
        return roomService.getAllNumbers();
    }

    @GetMapping("/getAllRooms/{type}")
    public List<Room> getAllRooms(@PathVariable String type, Pageable pageable){
        return roomService.getAllRooms(type, pageable);
    }

    @GetMapping("/findByRoomNumber/{roomNumber}")
    public RoomEntity findByRoomNumber(@PathVariable int roomNumber) {
        return roomService.getRoomByNumber(roomNumber);
    }

    @GetMapping("/freePlacesForPetType")
    public Map<PetType,Integer> freePlacesForPetType(){
        return roomService.freePlacesForPetType();
    }

    @PutMapping("/updateRoom")
    public void updateRoom(@RequestBody PetRoom room) {
        roomService.updateRoom(room);
    }

    @DeleteMapping("/{roomNumber}")
    public void deleteRoom(@PathVariable int roomNumber){
        roomService.deleteRoom(roomNumber);
    }

    @GetMapping("/getAllRoomsInTheTemperatureRange/{id}")
    public List<Room> getAllRoomsInTheRangeForPlant(@PathVariable int id) throws TemperatureWrongRangeException {
        return roomService.getAllRoomsInTheRangeForPlant(id);
    }
}
