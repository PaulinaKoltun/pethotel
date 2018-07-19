package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.entity.PetEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */
public interface PetRepository extends CrudRepository<PetEntity, Long> {
    @Query(value = "SELECT DISTINCT owner_entity from owner_pet_list where pet_list in :idList", nativeQuery = true)
    List<Integer> ownerEntityList(@Param("idList") List<Integer> idList);

    @Query("SELECT e FROM PetEntity e WHERE e.petType is null")
    List<PetEntity> findAllPlants();

    @Query("SELECT e FROM PetEntity e WHERE e.petType is not null")
    List<PetEntity> findAllAnimals();
}
