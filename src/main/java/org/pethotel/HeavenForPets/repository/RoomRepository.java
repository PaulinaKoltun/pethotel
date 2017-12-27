package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Paulina on 2017-10-07.
 */
public interface RoomRepository extends PagingAndSortingRepository<RoomEntity, Long> {
    @Query("SELECT e FROM RoomEntity e WHERE e.roomNumber = :number")
    RoomEntity getRoomByNumber(@Param("number") int roomNumber);

    @Query("SELECT r FROM RoomEntity r ORDER BY r.roomNumber ASC")
    List<RoomEntity> sortedRoomEntitiesASC();

    @Query("SELECT r FROM RoomEntity r ORDER BY r.roomNumber DESC")
    List<RoomEntity> sortedRoomEntitiesDESC();
}
