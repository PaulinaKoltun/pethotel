package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.entity.PetEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Paulina on 2017-09-30.
 */
public interface PetRepository extends CrudRepository<PetEntity, Long> {

}
