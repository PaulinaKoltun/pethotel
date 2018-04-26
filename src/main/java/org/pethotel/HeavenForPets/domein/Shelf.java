package org.pethotel.HeavenForPets.domein;

public class Shelf {
    private int number;
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

        Shelf shelf = (Shelf) o;

        if (number != shelf.number) return false;
        return free == shelf.free;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + (free ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Shelf{" +
                "number=" + number +
                ", free=" + free +
                '}';
    }
}
