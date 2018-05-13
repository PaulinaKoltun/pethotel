package org.pethotel.HeavenForPets.service.impl;

import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.mappers.PetMap;
import org.pethotel.HeavenForPets.repository.PetRepository;
import org.pethotel.HeavenForPets.service.OwnerService;
import org.pethotel.HeavenForPets.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class PetServiceImplTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private RoomService roomService;

    @Mock
    private OwnerService ownerService;

    @Mock
    private PetMap petMap;

    @InjectMocks
    private PetServiceImpl petService;




}