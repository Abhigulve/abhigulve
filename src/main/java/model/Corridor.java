package model;

import strategy.DeviceOnStrategy;

import java.util.List;
import java.util.Objects;

public class Corridor {
    protected CorridorType corridorType;
    private List<ElectronicDevice> electronicDevices;
    private DeviceOnStrategy deviceOnStrategy;
    private int corridorNumber;
    private int currentPowerCunsumedByCoridor;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Corridor)) return false;
        Corridor corridor = (Corridor) o;
        return corridorNumber == corridor.corridorNumber &&
                corridorType == corridor.corridorType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(corridorType, corridorNumber);
    }

    public DeviceOnStrategy getDeviceOnStrategy() {
        return deviceOnStrategy;
    }

    public void setDeviceOnStrategy(DeviceOnStrategy deviceOnStrategy) {
        this.deviceOnStrategy = deviceOnStrategy;
    }

    public int getCorridorNumber() {
        return corridorNumber;
    }

    public void setCorridorNumber(int corridorNumber) {
        this.corridorNumber = corridorNumber;
    }

    public Corridor(CorridorType corridorType, List<ElectronicDevice> electronicDevices, DeviceOnStrategy deviceOnStrategy, int corridorNumber) {
        this.corridorType = corridorType;
        this.corridorNumber = corridorNumber;
        this.electronicDevices = electronicDevices;
        this.deviceOnStrategy = deviceOnStrategy;
        this.currentPowerCunsumedByCoridor = this.deviceOnStrategy.defaultDeviceOnOffRule(electronicDevices);
    }

    public int getCurrentPowerCunsumedByCoridor() {
        return currentPowerCunsumedByCoridor;
    }

    public CorridorType getCorridorType() {
        return corridorType;
    }

    public void setCorridorType(CorridorType corridorType) {
        this.corridorType = corridorType;
    }

    public List<ElectronicDevice> getElectronicDevices() {
        return electronicDevices;
    }

    public void setElectronicDevices(List<ElectronicDevice> electronicDevices) {
        this.electronicDevices = electronicDevices;
    }

    private int powerConsumptionOfCorridor() {
        return electronicDevices.stream().filter(ElectronicDevice::isSwitchOn)
                .mapToInt(ElectronicDevice::getPowerRating).sum();
    }

    public void updateDeviceStateInCorridor(Request request) {
        if (request.isMovement()) {
            deviceOnStrategy.startDeviceInCorridor(electronicDevices);
            currentPowerCunsumedByCoridor = powerConsumptionOfCorridor();
        } else {
        }
    }
}
