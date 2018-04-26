package org.pethotel.HeavenForPets.mappers;

import org.pethotel.HeavenForPets.domein.Rooms.Room;
import org.pethotel.HeavenForPets.entity.RoomEntity;

/**
 * Created by Paulina on 2017-11-12.
 */
public interface RoomMap {
    Room map(RoomEntity roomEntity);
}
