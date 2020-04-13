package model;

import exceptions.InvalidFloorNUmberException;

import java.util.ArrayList;
import java.util.List;

public class Hotel {
    private List<Floor> floorList;

    public Hotel() {
        this.floorList = new ArrayList<>();
    }

    public void updateHotelDeviceStatus(Request request) {
        try {
            Floor floor = getFloorFromFloorNumber(request.getFloorNumber());
            floor.updateDevicesOnFloor(request);
        } catch (InvalidFloorNUmberException e) {
            e.printStackTrace();
        }

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

