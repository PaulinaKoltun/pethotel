package org.pethotel.HeavenForPets.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.pethotel.HeavenForPets.domein.Address;
import org.pethotel.HeavenForPets.entity.AddressEntity;
import org.pethotel.HeavenForPets.mappers.AddressMap;
import org.pethotel.HeavenForPets.repository.AddressRepository;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressMap addressMap;

    @InjectMocks
    private AddressServiceImpl addressService;

    @Test
    public void saveAddress() throws Exception {
        Address address = new Address();
        AddressEntity addressEntity = new AddressEntity();

        when(addressMap.mapAddress(address)).thenReturn(addressEntity);
        when(addressRepository.save(addressEntity)).thenReturn(addressEntity);

        addressService.saveAddress(address);

        verify(addressRepository, times(1)).save(addressEntity);
    }

}