package org.pethotel.HeavenForPets.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * Created by Paulina on 2017-10-13.
 */
@Entity
@Table(name = "ADDRESS")
public @Data class AddressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "number_of_house")
    private String numberOfHouse;

    @Column(name = "number_of_flat")
    private String numberOfFlat;

    @Column(name = "zip_code")
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

        AddressEntity that = (AddressEntity) o;

        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (numberOfHouse != null ? !numberOfHouse.equals(that.numberOfHouse) : that.numberOfHouse != null)
            return false;
        if (numberOfFlat != null ? !numberOfFlat.equals(that.numberOfFlat) : that.numberOfFlat != null) return false;
        return zipCode != null ? zipCode.equals(that.zipCode) : that.zipCode == null;
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
        return "AddressEntity{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberOfHouse='" + numberOfHouse + '\'' +
                ", numberOfFlat='" + numberOfFlat + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
