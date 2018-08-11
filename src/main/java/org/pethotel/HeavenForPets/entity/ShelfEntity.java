package org.pethotel.HeavenForPets.entity;

import org.pethotel.HeavenForPets.enums.PlantInsolation;

import javax.persistence.*;

@Entity
@Table(name = "SHELF")
public class ShelfEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "number")
    private int number;

    @Column(name = "free")
    private boolean free;

    @Column(name = "plant_insolation")
    private PlantInsolation plantInsolation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public PlantInsolation getPlantInsolation() {
        return plantInsolation;
    }

    public void setPlantInsolation(PlantInsolation plantInsolation) {
        this.plantInsolation = plantInsolation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShelfEntity that = (ShelfEntity) o;

        if (number != that.number) return false;
        if (free != that.free) return false;
        return plantInsolation == that.plantInsolation;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (free ? 1 : 0);
        result = 31 * result + (plantInsolation != null ? plantInsolation.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShelfEntity{" +
                "number=" + number +
                ", free=" + free +
                ", plantInsolation=" + plantInsolation +
                '}';
    }
}

