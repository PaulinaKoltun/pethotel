package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.pethotel.HeavenForPets.enums.FoodType;
import org.pethotel.HeavenForPets.enums.PetType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FoodRepository extends CrudRepository<FoodEntity, Long> {
    @Query("SELECT e FROM FoodEntity e WHERE e.petType = :type")
    List<FoodEntity> getFoodByPetType(@Param("type") PetType petType);

    @Query("SELECT e FROM FoodEntity e " +
            "WHERE e.name = :name " +
            "AND e.foodType = :foodType " +
            "AND e.petType = :petType " +
            "AND e.taste = :taste")
    FoodEntity getFoodWeAlreadyHave(@Param("name") String name,
                                    @Param("foodType") FoodType foodType,
                                    @Param("petType") PetType petType,
                                    @Param("taste") String taste);

}
