package org.pethotel.HeavenForPets.utils;

import org.pethotel.HeavenForPets.entity.RoomEntity;
import org.pethotel.HeavenForPets.entity.ShelfEntity;
import org.pethotel.HeavenForPets.enums.PlantInsolation;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RoomEntityCreator {

    public static final int TEMPERATURE_10 = 10;
    public static final int TEMPERATURE_15 = 15;
    public static final int TEMPERATURE_20 = 20;
    public static final int TEMPERATURE_25 = 25;

    private RoomEntityCreator() {}

    public static List<RoomEntity> prepareRoomEntityListWithTemperature15() {
        RoomEntity roomEntity = new RoomEntity();
        roomEntity.setTemperature(TEMPERATURE_15);
        roomEntity.setShelfEntities(Collections.singletonList(new ShelfEntity()));

        List<RoomEntity> roomEntityList = new ArrayList<>();
        roomEntityList.add(roomEntity);

        return roomEntityList;
    }
}
