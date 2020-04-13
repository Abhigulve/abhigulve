package model;

import model.CorridorType;

public class Request {

    private int floorNumber;
    private CorridorType corridorType;
    private int corridorNUmber;
    private boolean movement;

    public boolean isMovement() {
        return movement;
    }

    public void setMovement(boolean movement) {
        this.movement = movement;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public CorridorType getCorridorType() {
        return corridorType;
    }

    public void setCorridorType(CorridorType corridorType) {
        this.corridorType = corridorType;
    }

    public int getCorridorNumber() {
        return corridorNUmber;
    }

    public void setCorridorNumber(int corridorNUmber) {
        this.corridorNUmber = corridorNUmber;
    }

    public Request(boolean movement, int floorNumber, CorridorType corridorType, int corridorNumber) {
        this.floorNumber = floorNumber;
        this.corridorType = corridorType;
        this.corridorNUmber = corridorNumber;
        this.movement = movement;
    }
}
