package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Paulina on 2017-10-07.
 */
public interface RoomRepository extends CrudRepository<RoomEntity, Long>{
    @Query("SELECT e FROM RoomEntity e WHERE e.roomNumber = :number")
    RoomEntity getRoomByNumber(@Param("number") int roomNumber);
}
