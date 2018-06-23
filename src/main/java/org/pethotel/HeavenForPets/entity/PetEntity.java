package org.pethotel.HeavenForPets.entity;

import org.pethotel.HeavenForPets.enums.PetType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Paulina on 2017-09-30.
 */
@Entity
@Table(name = "PET")
public class PetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "comment")
    private String comment;
    @Enumerated(EnumType.STRING)
    @Column(name = "pet_type")
    private PetType petType;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "room_entity", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_ROOM_TO_PET"))
    private RoomEntity roomEntity;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "shelf_entity", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_SHELF_TO_PET"))
    private ShelfEntity shelfEntity;
    @Column(name = "toWater")
    private int toWater;
    @Column(name = "date_in")
    private Date dateIn;
    @Column(name = "date_out")
    private Date dateOut;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "breakfast", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_BREAKFAST_TO_PET"))
    private FoodEntity breakfast;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "dinner", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_DINNER_TO_PET"))
    private FoodEntity dinner;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "supper", referencedColumnName = "id", foreignKey = @ForeignKey(name = "FK_SUPPER_TO_PET"))
    private FoodEntity supper;
    @Column(name = "presence")
    private Integer present = 1;

    public PetEntity() {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPresent() {
        return present;
    }

    public void setPresent(Integer present) {
        this.present = present;
    }

    public int getToWater() {
        return toWater;
    }

    public void setToWater(int toWater) {
        this.toWater = toWater;
    }

    public ShelfEntity getShelfEntity() {
        return shelfEntity;
    }

    public void setShelfEntity(ShelfEntity shelfEntity) {
        this.shelfEntity = shelfEntity;
    }

    @Override
    public String toString() {
        return "PetEntity{" +
                "name='" + name + '\'' +
                ", comment='" + comment + '\'' +
                ", petType=" + petType +
                ", roomEntity=" + roomEntity +
                ", shelfEntity=" + shelfEntity +
                ", toWater=" + toWater +
                ", dateIn=" + dateIn +
                ", dateOut=" + dateOut +
                ", breakfast=" + breakfast +
                ", dinner=" + dinner +
                ", supper=" + supper +
                ", present=" + present +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PetEntity petEntity = (PetEntity) o;

        if (toWater != petEntity.toWater) return false;
        if (name != null ? !name.equals(petEntity.name) : petEntity.name != null) return false;
        if (comment != null ? !comment.equals(petEntity.comment) : petEntity.comment != null) return false;
        if (petType != petEntity.petType) return false;
        if (roomEntity != null ? !roomEntity.equals(petEntity.roomEntity) : petEntity.roomEntity != null) return false;
        if (shelfEntity != null ? !shelfEntity.equals(petEntity.shelfEntity) : petEntity.shelfEntity != null)
            return false;
        if (dateIn != null ? !dateIn.equals(petEntity.dateIn) : petEntity.dateIn != null) return false;
        if (dateOut != null ? !dateOut.equals(petEntity.dateOut) : petEntity.dateOut != null) return false;
        if (breakfast != null ? !breakfast.equals(petEntity.breakfast) : petEntity.breakfast != null) return false;
        if (dinner != null ? !dinner.equals(petEntity.dinner) : petEntity.dinner != null) return false;
        if (supper != null ? !supper.equals(petEntity.supper) : petEntity.supper != null) return false;
        return present != null ? present.equals(petEntity.present) : petEntity.present == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (petType != null ? petType.hashCode() : 0);
        result = 31 * result + (roomEntity != null ? roomEntity.hashCode() : 0);
        result = 31 * result + (shelfEntity != null ? shelfEntity.hashCode() : 0);
        result = 31 * result + toWater;
        result = 31 * result + (dateIn != null ? dateIn.hashCode() : 0);
        result = 31 * result + (dateOut != null ? dateOut.hashCode() : 0);
        result = 31 * result + (breakfast != null ? breakfast.hashCode() : 0);
        result = 31 * result + (dinner != null ? dinner.hashCode() : 0);
        result = 31 * result + (supper != null ? supper.hashCode() : 0);
        result = 31 * result + (present != null ? present.hashCode() : 0);
        return result;
    }
}
