package model;

public class Request {

    private int floorNumber;
    private CorridorType corridorType;
    private int corridorNumber;
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
        return corridorNumber;
    }

    public void setCorridorNumber(int corridorNumber) {
        this.corridorNumber = corridorNumber;
    }

    public Request(boolean movement, int floorNumber, CorridorType corridorType, int corridorNumber) {
        this.floorNumber = floorNumber;
        this.corridorType = corridorType;
        this.corridorNumber = corridorNumber;
        this.movement = movement;
    }
}
