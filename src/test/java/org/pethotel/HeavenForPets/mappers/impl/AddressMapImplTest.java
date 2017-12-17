package org.pethotel.HeavenForPets.mappers.impl;

import org.junit.Test;
import org.pethotel.HeavenForPets.domein.Address;
import org.pethotel.HeavenForPets.entity.AddressEntity;
import org.pethotel.HeavenForPets.mappers.AddressMap;

import static org.junit.Assert.*;

public class AddressMapImplTest {

    AddressMap addressMap = new AddressMapImpl();

    @Test
    public void shouldTestIfCityIsMappedCorrectlyToAddressEntity(){
        Address address = new Address();
        address.setCity("Wro");

        AddressEntity addressEntity = addressMap.mapAddress(address);
        assertEquals(address.getCity(), addressEntity.getCity());
    }

    @Test
    public void shouldTestIfStreetIsMappedCorrectlyToAddressEntity(){
        Address address = new Address();
        address.setStreet("Piękna");

        AddressEntity addressEntity = addressMap.mapAddress(address);
        assertEquals(address.getStreet(), addressEntity.getStreet());
    }

    @Test
    public void shouldTestIfZipCodeIsMappedCorrectlyToAddressEntity(){
        Address address = new Address();
        address.setZipCode("22");

        AddressEntity addressEntity = addressMap.mapAddress(address);
        assertEquals(address.getZipCode(), addressEntity.getZipCode());
    }

    @Test
    public void shouldTestIfNumberOfHouseIsMappedCorrectlyToAddressEntity(){
        Address address = new Address();
        address.setNumberOfHouse("3");

        AddressEntity addressEntity = addressMap.mapAddress(address);
        assertEquals(address.getNumberOfHouse(), addressEntity.getNumberOfHouse());
    }

    @Test
    public void shouldTestIfNumberOfFlatIsMappedCorrectlyToAddressEntity(){
        Address address = new Address();
        address.setNumberOfFlat("3");

        AddressEntity addressEntity = addressMap.mapAddress(address);
        assertEquals(address.getNumberOfFlat(), addressEntity.getNumberOfFlat());
    }

}