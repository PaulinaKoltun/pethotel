package org.pethotel.HeavenForPets.entity;

import org.pethotel.HeavenForPets.enums.PetType;

import javax.persistence.*;

/**
 * Created by Paulina on 2017-09-30.
 */
@Entity(name = "pet")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String comment;
    @Enumerated(EnumType.STRING)
    private PetType petType;

    public PetEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetEntity petEntity = (PetEntity) o;

        if (name != null ? !name.equals(petEntity.name) : petEntity.name != null) return false;
        if (comment != null ? !comment.equals(petEntity.comment) : petEntity.comment != null) return false;
        return petType == petEntity.petType;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        return result;
    }

    public String getComment() {

        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    @Override
    public String toString() {
        return "PetEntity{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", petType=" + petType +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
