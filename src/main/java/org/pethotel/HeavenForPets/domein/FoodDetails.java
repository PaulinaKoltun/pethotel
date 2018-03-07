package org.pethotel.HeavenForPets.domein;

import java.util.Date;

public class FoodDetails {
    private Food food;
    private int deliveryAmount;
    private Date deliveryDate;

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getDeliveryAmount() {
        return deliveryAmount;
    }

    public void setDeliveryAmount(int deliveryAmount) {
        this.deliveryAmount = deliveryAmount;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodDetails that = (FoodDetails) o;

        if (deliveryAmount != that.deliveryAmount) return false;
        if (food != null ? !food.equals(that.food) : that.food != null) return false;
        return deliveryDate != null ? deliveryDate.equals(that.deliveryDate) : that.deliveryDate == null;
    }

    @Override
    public int hashCode() {
        int result = food != null ? food.hashCode() : 0;
        result = 31 * result + deliveryAmount;
        result = 31 * result + (deliveryDate != null ? deliveryDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FoodDetails{" +
                "food=" + food +
                ", deliveryAmount=" + deliveryAmount +
                ", deliveryDate=" + deliveryDate +
                '}';
    }
}
