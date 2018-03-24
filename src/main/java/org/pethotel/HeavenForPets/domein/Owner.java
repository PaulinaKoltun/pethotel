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
    private int discount;
    private List<Pet> petList = new ArrayList<>();
    private Address address;
        //szukac ownera po czyms unikalnym i dodac zwierze do hotelu jedno lub kilka sposrod jego zwierzat 
    public Owner(){

    }

    public Owner(String firstName, String lastName, OwnerCategory ownerCategory, List<Pet> petList, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ownerCategory = ownerCategory;
        this.petList = petList;
        this.discount =ownerCategory.getDiscount();
        this.address = address;
    }

    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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

    public OwnerCategory getOwnerCategory() {
        return ownerCategory;
    }

    public void setOwnerCategory(OwnerCategory ownerCategory) {
        this.ownerCategory = ownerCategory;
    }

    public List<Pet> getPetList() {
        return petList;
    }

    public void setPetList(List<Pet> petList) {
        this.petList = petList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Owner owner = (Owner) o;

        if (discount != owner.discount) return false;
        if (firstName != null ? !firstName.equals(owner.firstName) : owner.firstName != null) return false;
        if (lastName != null ? !lastName.equals(owner.lastName) : owner.lastName != null) return false;
        if (ownerCategory != owner.ownerCategory) return false;
        if (petList != null ? !petList.equals(owner.petList) : owner.petList != null) return false;
        return address != null ? address.equals(owner.address) : owner.address == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (ownerCategory != null ? ownerCategory.hashCode() : 0);
        result = 31 * result + (petList != null ? petList.hashCode() : 0);
        result = 31 * result + discount;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ownerCategory=" + ownerCategory +
                ", petList=" + petList +
                ", discount=" + discount +
                ", address=" + address +
                '}';
    }
}
