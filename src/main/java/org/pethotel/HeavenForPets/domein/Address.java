package org.pethotel.HeavenForPets.domein;

import java.io.Serializable;

/**
 * Created by Paulina on 2017-10-13.
 */
public class Address implements Serializable{
    private String city;
    private String street;
    private String numberOfHouse;
    private String numberOfFlat;
    private String zipCode;

    public Address() {
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumberOfHouse() {
        return numberOfHouse;
    }

    public void setNumberOfHouse(String numberOfHouse) {
        this.numberOfHouse = numberOfHouse;
    }

    public String getNumberOfFlat() {
        return numberOfFlat;
    }

    public void setNumberOfFlat(String numberOfFlat) {
        this.numberOfFlat = numberOfFlat;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (city != null ? !city.equals(address.city) : address.city != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (numberOfHouse != null ? !numberOfHouse.equals(address.numberOfHouse) : address.numberOfHouse != null)
            return false;
        if (numberOfFlat != null ? !numberOfFlat.equals(address.numberOfFlat) : address.numberOfFlat != null)
            return false;
        return zipCode != null ? zipCode.equals(address.zipCode) : address.zipCode == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (numberOfHouse != null ? numberOfHouse.hashCode() : 0);
        result = 31 * result + (numberOfFlat != null ? numberOfFlat.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberOfHouse='" + numberOfHouse + '\'' +
                ", numberOfFlat='" + numberOfFlat + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
