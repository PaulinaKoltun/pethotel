package org.pethotel.HeavenForPets.domein;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Paulina on 2017-10-21.
 */
public @Data class Client {
    private long id;
    private String firstName;
    private String lastName;
    private int petNumbers;
    private BigDecimal wholePrice;
}
