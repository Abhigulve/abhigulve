package model;

import strategy.FloorDeviceStrategy;

import java.util.List;

public class Floor {

    private List<Corridor> corridors;
    private FloorDeviceStrategy floorDeviceStrategy;
    private int floorNumber;

    public Floor(int floorNumber, List<Corridor> corridors, FloorDeviceStrategy floorDeviceStrategy) {
        this.corridors = corridors;
        this.floorDeviceStrategy = floorDeviceStrategy;
        this.floorNumber = floorNumber;
    }


    public List<Corridor> getCorridors() {
        return corridors;
    }

    public void setCorridors(List<Corridor> corridors) {
        this.corridors = corridors;
    }

    public void setFloorDeviceStrategy(FloorDeviceStrategy floorDeviceStrategy) {
        this.floorDeviceStrategy = floorDeviceStrategy;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Corridor corridor : corridors) {
            stringBuilder.append(corridor).append("\n");
        }
        return "\t\t\t\tFloor " + floorNumber +"\n"+ stringBuilder;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public void updateDevicesOnFloor(Request request) {
        floorDeviceStrategy.updateDevicesState(corridors, request);
    }
}

