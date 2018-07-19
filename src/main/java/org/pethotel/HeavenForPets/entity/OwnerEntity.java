package org.pethotel.HeavenForPets.entity;

import org.pethotel.HeavenForPets.enums.OwnerCategory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paulina on 2017-09-30.
 */
@Entity
@Table(name = "OWNER")
public class OwnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(name = "owner_category")
    private OwnerCategory ownerCategory;

    @Column(name = "discount")
    private int discount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="OWNER_PET_LIST",
            joinColumns = @JoinColumn(name="owner_entity", referencedColumnName="id",
                    foreignKey = @ForeignKey(name = "FK_OWNER_AND_PET")),
            inverseJoinColumns = @JoinColumn(name="pet_list", referencedColumnName="id",
                    foreignKey = @ForeignKey(name = "FK_OWNER_AND_PET_ID"))
    )
    private List<PetEntity> petList = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name="address_entity", referencedColumnName="id",
            foreignKey = @ForeignKey(name = "FK_OWNER_TO_ADDRESS"))
    private AddressEntity addressEntity;

    public OwnerEntity() {
    }

    public Long getId() {
        return id;
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

    public List<PetEntity> getPetList() {
        return petList;
    }

    public void setPetList(List<PetEntity> petList) {
        this.petList = petList;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
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

        OwnerEntity that = (OwnerEntity) o;

        if (discount != that.discount) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (ownerCategory != that.ownerCategory) return false;
        if (petList != null ? !petList.equals(that.petList) : that.petList != null) return false;
        return addressEntity != null ? addressEntity.equals(that.addressEntity) : that.addressEntity == null;
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (ownerCategory != null ? ownerCategory.hashCode() : 0);
        result = 31 * result + discount;
        result = 31 * result + (petList != null ? petList.hashCode() : 0);
        result = 31 * result + (addressEntity != null ? addressEntity.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OwnerEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", ownerCategory=" + ownerCategory +
                ", discount=" + discount +
                ", petList=" + petList +
                ", addressEntity=" + addressEntity +
                '}';
    }
}
