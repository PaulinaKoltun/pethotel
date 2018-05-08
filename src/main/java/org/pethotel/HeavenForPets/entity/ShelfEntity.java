package org.pethotel.HeavenForPets.entity;

import javax.persistence.*;

@Entity
@Table(name = "SHELF")
public class ShelfEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "number")
    private int number;
    @Column(name = "free")
    private boolean free;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ShelfEntity that = (ShelfEntity) o;

        if (number != that.number) return false;
        return free == that.free;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (free ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ShelfEntity{" +
                "number=" + number +
                ", free=" + free +
                '}';
    }
}

