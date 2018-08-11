package org.pethotel.HeavenForPets.repository;

import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ShelfRepository extends CrudRepository<ShelfEntity, Long> {
    }
