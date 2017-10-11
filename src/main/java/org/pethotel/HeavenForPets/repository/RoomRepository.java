package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Paulina on 2017-10-07.
 */
public interface RoomRepository extends CrudRepository<RoomEntity, Long>{
    @Query(value = "SELECT * FROM Room e WHERE e.room_number = :number", nativeQuery = true)
    RoomEntity getRoomByNumber(@Param("number") String roomNumber);
}
