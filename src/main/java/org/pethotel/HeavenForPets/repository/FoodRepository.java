package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.enums.PetType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends CrudRepository<FoodEntity, Long> {
    @Query("SELECT e FROM FoodEntity e WHERE e.petType = :type")
    List<FoodEntity> getFoodByPetType(@Param("type") PetType petType);
}
