package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Test;
import org.pethotel.HeavenForPets.domein.Owner;
import org.pethotel.HeavenForPets.entity.OwnerEntity;

import static org.junit.Assert.*;

/**
 * Created by Paulina on 2017-10-04.
 */
public class OwnerMapImplTest {

    @Test
    public void testMapper(){
        Owner owner = new Owner("p","k","w","g","6","3","2");
        OwnerMapImpl ownerMap = new OwnerMapImpl();
        OwnerEntity ownerEntity = ownerMap.map(owner);

        assertEquals(owner.getCity(),ownerEntity.getCity());
    }
}