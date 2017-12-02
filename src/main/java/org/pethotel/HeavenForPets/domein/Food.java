package org.pethotel.HeavenForPets.domein;

import org.pethotel.HeavenForPets.enums.FoodType;
import org.pethotel.HeavenForPets.enums.PetType;

public class Food {
    private String name;
    private FoodType foodType;
    private int amount;
    private PetType petType;
    private String taste;

    public Food() {
    }

    public Food(String name, FoodType foodType, int amount, PetType petType, String taste) {
        this.name = name;
        this.foodType = foodType;
        this.amount = amount;
        this.petType = petType;
        this.taste = taste;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Food food = (Food) o;

        if (amount != food.amount) return false;
        if (name != null ? !name.equals(food.name) : food.name != null) return false;
        if (foodType != null ? !foodType.equals(food.foodType) : food.foodType != null) return false;
        if (petType != food.petType) return false;
        return taste != null ? taste.equals(food.taste) : food.taste == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (foodType != null ? foodType.hashCode() : 0);
        result = 31 * result + amount;
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + (taste != null ? taste.hashCode() : 0);
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
                '}';
    }
}
