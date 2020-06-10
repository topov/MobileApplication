package com.example.carsforyou;

import androidx.annotation.NonNull;

public class Advertisement {
    private int id;
    private String ownerName;
    private String ownerNumber;
    private float price;
    private String carMake;
    private String carModel;
    private String carDescription;

    public Advertisement() {
    }

    public Advertisement(
            String ownerName,
            String ownerNumber,
            float price,
            String carMake,
            String carModel,
            String carDescription) {

        this.ownerName = ownerName;
        this.ownerNumber = ownerNumber;
        this.price = price;
        this.carMake = carMake;
        this.carModel = carModel;
        this.carDescription = carDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerNumber() {
        return ownerNumber;
    }

    public void setOwnerNumber(String ownerNumber) {
        this.ownerNumber = ownerNumber;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarDescription() {
        return carDescription;
    }

    public void setCarDescription(String carDescription) {
        this.carDescription = carDescription;
    }

    @NonNull
    @Override
    public String toString() {
        return "#" + this.getId() + " " + this.getCarMake() + " " + this.getCarModel() +" - " + this.getPrice() + "USD";
    }
}
