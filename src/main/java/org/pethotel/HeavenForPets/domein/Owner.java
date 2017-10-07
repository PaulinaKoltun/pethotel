package org.pethotel.HeavenForPets.domein;


import org.pethotel.HeavenForPets.enums.OwnerCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */
public class Owner implements Serializable {
    private String firstName;
    private String lastName;
    private OwnerCategory ownerCategory;
    private List<Pet> petList = new ArrayList<>();
    private String city;
    private String street;
    private String numberofHouse;
    private String numberofFlat;
    private String zipCode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;

        if (firstName != null ? !firstName.equals(owner.firstName) : owner.firstName != null) return false;
        if (lastName != null ? !lastName.equals(owner.lastName) : owner.lastName != null) return false;
        if (ownerCategory != owner.ownerCategory) return false;
        if (petList != null ? !petList.equals(owner.petList) : owner.petList != null) return false;
        if (city != null ? !city.equals(owner.city) : owner.city != null) return false;
        if (street != null ? !street.equals(owner.street) : owner.street != null) return false;
        if (numberofHouse != null ? !numberofHouse.equals(owner.numberofHouse) : owner.numberofHouse != null)
            return false;
        if (numberofFlat != null ? !numberofFlat.equals(owner.numberofFlat) : owner.numberofFlat != null) return false;
        return zipCode != null ? zipCode.equals(owner.zipCode) : owner.zipCode == null;
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

    public Owner(){

    }

    public Owner(String firstName, String lastName, String city, String street, String numberofHouse, String numberofFlat, String zipCode, OwnerCategory ownerCategory) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.street = street;
        this.numberofHouse = numberofHouse;
        this.numberofFlat = numberofFlat;
        this.zipCode = zipCode;
        this.ownerCategory = ownerCategory;
    }

    @Override
    public String toString() {
        return "Owner{" +
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

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public OwnerCategory getOwnerCategory() {
        return ownerCategory;
    }

    public void setOwnerCategory(OwnerCategory ownerCategory) {
        this.ownerCategory = ownerCategory;
    }

    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
