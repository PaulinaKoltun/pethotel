package org.pethotel.HeavenForPets.entity;

import org.pethotel.HeavenForPets.enums.PetType;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Paulina on 2017-09-30.
 */
@Entity
@Table(name = "PET")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "comment")
    private String comment;
    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type")
    private PetType petType;
    @ManyToOne(cascade = CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_ROOM_TO_PET"))
    private RoomEntity roomEntity;
    @Column(name = "date_in")
    private Date dateIn;
    @Column(name = "date_out")
    private Date dateOut;
    @Transient
    @Column(name = "breakfast")
    private FoodEntity breakfast;
    @Transient
    @Column(name = "dinner")
    private FoodEntity dinner;
    @Transient
    @Column(name = "supper")
    private FoodEntity supper;

    public PetEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetEntity petEntity = (PetEntity) o;

        if (name != null ? !name.equals(petEntity.name) : petEntity.name != null) return false;
        if (comment != null ? !comment.equals(petEntity.comment) : petEntity.comment != null) return false;
        if (petType != petEntity.petType) return false;
        if (roomEntity != null ? !roomEntity.equals(petEntity.roomEntity) : petEntity.roomEntity != null) return false;
        if (dateIn != null ? !dateIn.equals(petEntity.dateIn) : petEntity.dateIn != null) return false;
        if (dateOut != null ? !dateOut.equals(petEntity.dateOut) : petEntity.dateOut != null) return false;
        if (breakfast != null ? !breakfast.equals(petEntity.breakfast) : petEntity.breakfast != null) return false;
        if (dinner != null ? !dinner.equals(petEntity.dinner) : petEntity.dinner != null) return false;
        return supper != null ? supper.equals(petEntity.supper) : petEntity.supper == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + (roomEntity != null ? roomEntity.hashCode() : 0);
        result = 31 * result + (dateIn != null ? dateIn.hashCode() : 0);
        result = 31 * result + (dateOut != null ? dateOut.hashCode() : 0);
        result = 31 * result + (breakfast != null ? breakfast.hashCode() : 0);
        result = 31 * result + (dinner != null ? dinner.hashCode() : 0);
        result = 31 * result + (supper != null ? supper.hashCode() : 0);
        return result;
    }

    public String getComment() {

        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Date getDateIn() {
        return dateIn;
    }

    public void setDateIn(Date dateIn) {
        this.dateIn = dateIn;
    }

    public Date getDateOut() {
        return dateOut;
    }

    public void setDateOut(Date dateOut) {
        this.dateOut = dateOut;
    }

    public FoodEntity getBreakfast() {
        return breakfast;
    }

    public void setBreakfast(FoodEntity breakfast) {
        this.breakfast = breakfast;
    }

    public FoodEntity getDinner() {
        return dinner;
    }

    public void setDinner(FoodEntity dinner) {
        this.dinner = dinner;
    }

    public FoodEntity getSupper() {
        return supper;
    }

    public void setSupper(FoodEntity supper) {
        this.supper = supper;
    }

    @Override
    public String toString() {
        return "PetEntity{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", petType=" + petType +
                ", roomEntity=" + roomEntity +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", breakfast=" + breakfast +
                ", dinner=" + dinner +
                ", supper=" + supper +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
