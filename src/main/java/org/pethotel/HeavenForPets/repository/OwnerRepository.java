package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.entity.AddressEntity;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Created by Paulina on 2017-09-30.
 */
public interface OwnerRepository extends CrudRepository<OwnerEntity, Long> {
    @Query("SELECT e FROM OwnerEntity e WHERE e.lastName = :lastName")
    OwnerEntity getOwnerByLastName(@Param("lastName") String lastName);
}
