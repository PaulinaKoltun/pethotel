package org.pethotel.HeavenForPets.domein;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Paulina on 2017-10-13.
 */
public @Data class Address implements Serializable{
    private String city;
    private String street;
    private String numberOfHouse;
    private String numberOfFlat;
    private String zipCode;
}
