package org.pethotel.HeavenForPets.entity;

import org.pethotel.HeavenForPets.enums.PetType;
import org.pethotel.HeavenForPets.enums.PlantInsolation;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

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

    @Column(name = "to_water")
    private Integer toWater;

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

    @Column(name = "min_temperature")
    private Integer minTemperature;

    @Column(name = "max_temperature")
    private  Integer maxTemperature;

    @Enumerated(EnumType.STRING)
    @Column(name = "plant_insolation")
    private PlantInsolation plantInsolation;

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

    public Integer getToWater() {
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

    public Integer getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(int minTemperature) {
        this.minTemperature = minTemperature;
    }

    public Integer getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(int maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public PlantInsolation getPlantInsolation() {
        return plantInsolation;
    }

    public void setPlantInsolation(PlantInsolation plantInsolation) {
        this.plantInsolation = plantInsolation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetEntity petEntity = (PetEntity) o;
        return Objects.equals(name, petEntity.name) &&
                Objects.equals(comment, petEntity.comment) &&
                petType == petEntity.petType &&
                Objects.equals(roomEntity, petEntity.roomEntity) &&
                Objects.equals(shelfEntity, petEntity.shelfEntity) &&
                Objects.equals(toWater, petEntity.toWater) &&
                Objects.equals(dateIn, petEntity.dateIn) &&
                Objects.equals(dateOut, petEntity.dateOut) &&
                Objects.equals(breakfast, petEntity.breakfast) &&
                Objects.equals(dinner, petEntity.dinner) &&
                Objects.equals(supper, petEntity.supper) &&
                Objects.equals(present, petEntity.present) &&
                Objects.equals(minTemperature, petEntity.minTemperature) &&
                Objects.equals(maxTemperature, petEntity.maxTemperature) &&
                plantInsolation == petEntity.plantInsolation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, comment, petType, roomEntity, shelfEntity, toWater, dateIn, dateOut, breakfast, dinner, supper, present, minTemperature, maxTemperature, plantInsolation);
    }

    @Override
    public String toString() {
        return "PetEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", plantInsolation=" + plantInsolation +
                '}';
    }

}
