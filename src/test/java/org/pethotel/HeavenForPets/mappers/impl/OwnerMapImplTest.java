package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Ignore;
import org.junit.Test;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.enums.OwnerCategory;
import org.pethotel.HeavenForPets.exceptions.InvalidPetTypeException;

import static org.junit.Assert.*;

/**
 * Created by Paulina on 2017-10-04.
 */
@Ignore
public class OwnerMapImplTest {
    @Test
    public void testMapper() throws InvalidPetTypeException {
        Owner owner = new Owner();
        OwnerMapImpl ownerMap = new OwnerMapImpl();
        OwnerEntity ownerEntity = ownerMap.map(owner);

        assertEquals(owner.getFirstName(),ownerEntity.getFirstName());
    }
}