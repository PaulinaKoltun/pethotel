package org.pethotel.HeavenForPets.domein.Rooms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Created by Paulina on 2017-10-07.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "orgin")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PlantRoom.class, name = "PlantRoom"),

        @JsonSubTypes.Type(value = PetRoom.class, name = "PetRoom") }
)
public @Data class Room implements Serializable {
    private int roomNumber;
    private int numberOfPlaces;
    private int freePlaces;
    private BigDecimal price;

}
