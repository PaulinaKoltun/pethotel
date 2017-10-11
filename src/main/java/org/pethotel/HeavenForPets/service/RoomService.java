package org.pethotel.HeavenForPets.service;

import org.pethotel.HeavenForPets.domein.Room;

import java.util.List;

/**
 * Created by Paulina on 2017-10-07.
 */
public interface RoomService {
    void saveRoom(Room room);
    List<Integer> getAllNumbers();
}
