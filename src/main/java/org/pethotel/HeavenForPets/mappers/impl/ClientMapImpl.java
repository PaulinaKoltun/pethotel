package org.pethotel.HeavenForPets.mappers.impl;

import org.pethotel.HeavenForPets.domein.Client;
import org.pethotel.HeavenForPets.entity.OwnerEntity;
import org.pethotel.HeavenForPets.entity.PetEntity;
import org.pethotel.HeavenForPets.mappers.ClientMap;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

import static org.pethotel.HeavenForPets.utils.Calculator.getDaysOfVisit;

@Component
public class ClientMapImpl implements ClientMap {

    @Override
    public Client map(OwnerEntity ownerEntity) {
        Client client = new Client();
        client.setId(ownerEntity.getId());
        client.setFirstName(ownerEntity.getFirstName());
        client.setLastName(ownerEntity.getLastName());
        client.setPetNumbers(ownerEntity.getPetList().size());
        client.setWholePrice(getWholePriceAfterDiscount(ownerEntity));
        return client;
    }


    private BigDecimal getWholePriceAfterDiscount(OwnerEntity ownerEntity) {
        BigDecimal generalPrice = countWholePrice(ownerEntity.getPetList());
        BigDecimal priceOfDiscount = generalPrice.multiply(new BigDecimal(ownerEntity.getDiscount()));
        BigDecimal finalPrice = generalPrice.subtract(priceOfDiscount);
        return finalPrice;
    }

    private BigDecimal countWholePrice(List<PetEntity> petList) {
        BigDecimal wholePrice = BigDecimal.ZERO;
        for (PetEntity petEntity : petList) {
            if (null!=petEntity.getRoomEntity()) {
                BigDecimal daysOfVisit = new BigDecimal(getDaysOfVisit(petEntity));
                wholePrice = (wholePrice.add(petEntity.getRoomEntity().getPrice().multiply(daysOfVisit)));
            }
        }
        return wholePrice;
    }
}
