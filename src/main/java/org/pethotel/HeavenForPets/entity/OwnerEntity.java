package org.pethotel.HeavenForPets.entity;

import org.pethotel.HeavenForPets.enums.OwnerCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */
@Entity(name = "owner")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private OwnerCategory ownerCategory;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "pet")
    private List<PetEntity> petList = new ArrayList<>();
    private String city;
    private String street;
    private String numberofHouse;
    private String numberofFlat;
    private String zipCode;

    public OwnerEntity() {
    }

    @Override
    public String toString() {
        return "OwnerEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ownerCategory=" + ownerCategory +
                ", petList=" + petList +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", numberofHouse='" + numberofHouse + '\'' +
                ", numberofFlat='" + numberofFlat + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OwnerEntity that = (OwnerEntity) o;

        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (ownerCategory != that.ownerCategory) return false;
        if (petList != null ? !petList.equals(that.petList) : that.petList != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (numberofHouse != null ? !numberofHouse.equals(that.numberofHouse) : that.numberofHouse != null)
            return false;
        if (numberofFlat != null ? !numberofFlat.equals(that.numberofFlat) : that.numberofFlat != null) return false;
        return zipCode != null ? zipCode.equals(that.zipCode) : that.zipCode == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (ownerCategory != null ? ownerCategory.hashCode() : 0);
        result = 31 * result + (petList != null ? petList.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (numberofHouse != null ? numberofHouse.hashCode() : 0);
        result = 31 * result + (numberofFlat != null ? numberofFlat.hashCode() : 0);
        result = 31 * result + (zipCode != null ? zipCode.hashCode() : 0);
        return result;
    }

    public OwnerCategory getOwnerCategory() {
        return ownerCategory;
    }

    public void setOwnerCategory(OwnerCategory ownerCategory) {
        this.ownerCategory = ownerCategory;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<PetEntity> getPetList() {
        return petList;
    }

    public void setPetList(List<PetEntity> petList) {
        this.petList = petList;
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
}
