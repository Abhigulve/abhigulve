package model;

import exceptions.InvalidFloorNUmberException;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Floor> floorList;

    public List<Floor> getFloorList() {
        return floorList;
    }

    public Hotel() {
        this.floorList = new ArrayList<>();
    }

    public Floor getFloorByFloorNumber(int floorNumber) {
        return floorList.stream().filter(floor -> floor.getFloorNumber() == floorNumber).findFirst().get();
    }

    public void updateHotelDeviceStatus(Request request) {
        try {
            Floor floor = getFloorFromFloorNumber(request.getFloorNumber());
            floor.updateDevicesOnFloor(request);
            System.out.println(floor);
        } catch (InvalidFloorNUmberException e) {
            e.printStackTrace();
        }

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Floor floor : floorList) {
            stringBuilder.append(floor).append("\n");
        }
        return stringBuilder.toString();
    }

    private Floor getFloorFromFloorNumber(int floorNumber) throws InvalidFloorNUmberException {
        for (Floor floor : floorList) {
            if (floor.getFloorNumber() == floorNumber) return floor;
        }
        throw new InvalidFloorNUmberException("Please provide valid floor number");
    }

    public void addFloor(Floor floor) {
        floorList.add(floor);
    }
}

