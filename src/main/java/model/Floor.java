package model;

import exceptions.InvalidCorridorException;
import strategy.FloorDeviceStrategy;

import java.util.List;
import java.util.stream.Collectors;

public class Floor {

    private List<Corridor> corridors;
    private int powerConsumptionPerFloor;
    private FloorDeviceStrategy floorDeviceStrategy;
    private int maximumPowerConsumptionPerFloor;
    private int floorNumber;

    public Floor(int floorNumber, List<Corridor> corridors, FloorDeviceStrategy floorDeviceStrategy) {
        this.corridors = corridors;
        this.floorDeviceStrategy = floorDeviceStrategy;
        this.floorNumber = floorNumber;
        this.maximumPowerConsumptionPerFloor = floorDeviceStrategy.defaultMaximumPowerPerFloor(corridors);
        this.powerConsumptionPerFloor = getCurrentPowerConsumption();
    }

    private int getCurrentPowerConsumption() {
        return corridors.stream().mapToInt(Corridor::getCurrentPowerCunsumedByCoridor).sum();
    }

    public void updateDefaultDeviceState() {
        floorDeviceStrategy.updateDevicesDefault(corridors);
    }

    public List<Corridor> getCorridors() {
        return corridors;
    }

    public void setCorridors(List<Corridor> corridors) {
        this.corridors = corridors;
    }

    public int getPowerConsumptionPerFloor() {
        return powerConsumptionPerFloor;
    }

    public void setPowerConsumptionPerFloor(int powerConsumptionPerFloor) {
        this.powerConsumptionPerFloor = powerConsumptionPerFloor;
    }

    public FloorDeviceStrategy getFloorDeviceStrategy() {
        return floorDeviceStrategy;
    }

    public void setFloorDeviceStrategy(FloorDeviceStrategy floorDeviceStrategy) {
        this.floorDeviceStrategy = floorDeviceStrategy;
    }

    public int getMaximumPowerConsumptionPerFloor() {
        return maximumPowerConsumptionPerFloor;
    }

    public void setMaximumPowerConsumptionPerFloor(int maximumPowerConsumptionPerFloor) {
        this.maximumPowerConsumptionPerFloor = maximumPowerConsumptionPerFloor;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }


    private Corridor getCorridor(int corridorNumber, CorridorType corridorType) throws InvalidCorridorException {
        for (Corridor corridor : corridors) {
            if (corridor.corridorType.equals(corridorType) && corridor.getCorridorNumber() == corridorNumber)
                return corridor;
        }
        throw new InvalidCorridorException("corridor you requested is not available");
    }

    public void updateDevicesOnFloor(Request request) {
        try {
            Corridor corridor = getCorridor(request.getCorridorNumber(), request.getCorridorType());
            corridor.updateDeviceStateInCorridor(request);
            powerConsumptionPerFloor = getCurrentPowerConsumption();
            if (powerConsumptionPerFloor > maximumPowerConsumptionPerFloor)
                switchOfAcsOfOtherCorridors(getCorridorsExcept(corridor),
                        (powerConsumptionPerFloor - maximumPowerConsumptionPerFloor));
        } catch (InvalidCorridorException e) {
            e.printStackTrace();
        }
    }

    private List<Corridor> getCorridorsExcept(Corridor corridor) {
        return corridors.stream()
                .filter(corridor1 -> !(corridor1.equals(corridor))
                        && corridor1.getCorridorType().equals(corridor.getCorridorType()))
                .collect(Collectors.toList());
    }

    private void switchOfAcsOfOtherCorridors(List<Corridor> corridorList, int extraPower) {
        for (Corridor corridor : corridorList) {
            int powerSaveAfterSwitchOff = StopACsOfCorridor(corridor.getElectronicDevices(), extraPower);
            if ((extraPower - powerSaveAfterSwitchOff) <= 0) {
                return;
            } else
                extraPower -= powerSaveAfterSwitchOff;
        }
    }

    private int StopACsOfCorridor(List<ElectronicDevice> electronicDeviceList, int power) {
        int powerSaveAfterAcOff = 0;
        for (ElectronicDevice electronicDevice : electronicDeviceList) {
            if (electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.AC)
                    && electronicDevice.isSwitchOn() && powerSaveAfterAcOff < power) {
                electronicDevice.off();
                powerSaveAfterAcOff += electronicDevice.getPowerRating();
            }
        }
        return powerSaveAfterAcOff;
    }


}

