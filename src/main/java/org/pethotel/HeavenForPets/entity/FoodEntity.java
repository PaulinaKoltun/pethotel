package org.pethotel.HeavenForPets.entity;

import org.pethotel.HeavenForPets.enums.FoodType;
import org.pethotel.HeavenForPets.enums.PetType;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "FOOD")
public class FoodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column(name = "name")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "food_type")
    private FoodType foodType;
    @Column(name = "amount")
    private int amount;
    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type")
    private PetType petType;
    @Column(name = "taste")
    private String taste;
    @Column(name = "price")
    private BigDecimal price;

    public FoodEntity() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FoodEntity that = (FoodEntity) o;

        if (amount != that.amount) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (foodType != that.foodType) return false;
        if (petType != that.petType) return false;
        if (taste != null ? !taste.equals(that.taste) : that.taste != null) return false;
        return price != null ? price.equals(that.price) : that.price == null;
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
        return "FoodEntity{" +
                "name='" + name + '\'' +
                ", foodType=" + foodType +
                ", amount=" + amount +
                ", petType=" + petType +
                ", taste='" + taste + '\'' +
                ", price=" + price +
                '}';
    }
}
