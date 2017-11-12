package org.pethotel.HeavenForPets.entity;

import javax.persistence.*;

/**
 * Created by Paulina on 2017-10-13.
 */
@Entity
@Table(name = "address")
public class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    private String city;
    private String street;
    private String numberofHouse;
    private String numberofFlat;
    private String zipCode;

    public AddressEntity(){

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

        AddressEntity that = (AddressEntity) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (numberofHouse != null ? !numberofHouse.equals(that.numberofHouse) : that.numberofHouse != null)
            return false;
        if (numberofFlat != null ? !numberofFlat.equals(that.numberofFlat) : that.numberofFlat != null) return false;
        return zipCode != null ? zipCode.equals(that.zipCode) : that.zipCode == null;
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
        return "AddressEntity{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberofHouse='" + numberofHouse + '\'' +
                ", numberofFlat='" + numberofFlat + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
