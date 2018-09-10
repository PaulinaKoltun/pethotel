package org.pethotel.HeavenForPets.domein.Pet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Paulina on 2017-09-27.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Animal.class, name = "animal"),

        @JsonSubTypes.Type(value = Plant.class, name = "plant") }
)
public abstract @Data class Pet implements Serializable {
    private long id;
    private String name;
    private String comment;
    private int roomNumber;
    private Date dateIn;
    private Date dateOut;

}
