package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.entity.FoodEntity;
import org.springframework.data.repository.CrudRepository;

public interface FoodRepository extends CrudRepository<FoodEntity, Long> {
}
