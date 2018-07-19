package org.pethotel.HeavenForPets.domein;

import org.pethotel.HeavenForPets.enums.FoodType;
import org.pethotel.HeavenForPets.enums.PetType;

import java.math.BigDecimal;

public class Food {
    private String name;
    private FoodType foodType;
    private int amount;
    private PetType petType;
    private String taste;
    private BigDecimal price;
    private long id;

    public Food() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public void setFoodType(FoodType foodType) {
        this.foodType = foodType;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public String getTaste() {
        return taste;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (amount != food.amount) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (foodType != food.foodType) return false;
        if (petType != food.petType) return false;
        if (taste != null ? !taste.equals(food.taste) : food.taste != null) return false;
        return price != null ? price.equals(food.price) : food.price == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (foodType != null ? foodType.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + (taste != null ? taste.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", foodType=" + foodType +
                ", amount=" + amount +
                ", petType=" + petType +
                ", taste='" + taste + '\'' +
                ", price=" + price +
                ", id=" + id +
                '}';
    }
}
