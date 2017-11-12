package org.pethotel.HeavenForPets.domein;

import java.io.Serializable;

/**
 * Created by Paulina on 2017-10-13.
 */
public class Address implements Serializable{
    private String city;
    private String street;
    private String numberofHouse;
    private String numberofFlat;
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

    public String getNumberofHouse() {
        return numberofHouse;
    }

    public void setNumberofHouse(String numberofHouse) {
        this.numberofHouse = numberofHouse;
    }

    public String getNumberofFlat() {
        return numberofFlat;
    }

    public void setNumberofFlat(String numberofFlat) {
        this.numberofFlat = numberofFlat;
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
        if (numberofHouse != null ? !numberofHouse.equals(address.numberofHouse) : address.numberofHouse != null)
            return false;
        if (numberofFlat != null ? !numberofFlat.equals(address.numberofFlat) : address.numberofFlat != null)
            return false;
        return zipCode != null ? zipCode.equals(address.zipCode) : address.zipCode == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (numberofHouse != null ? numberofHouse.hashCode() : 0);
        result = 31 * result + (numberofFlat != null ? numberofFlat.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberofHouse='" + numberofHouse + '\'' +
                ", numberofFlat='" + numberofFlat + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
