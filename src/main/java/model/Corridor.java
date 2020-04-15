package model;

import strategy.DeviceOnStrategy;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Corridor {
    protected CorridorType corridorType;
    private List<ElectronicDevice> electronicDevices;
    private DeviceOnStrategy deviceOnStrategy;
    private int corridorNumber;
    private int currentPowerConsumedByCorridor;

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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (ElectronicDevice electronicDevice : electronicDevices) {
            s.append(electronicDevice).append(" ");
        }
        return
                corridorType + " " + corridorNumber + " : " + s;
    }

    public Corridor(CorridorType corridorType, List<ElectronicDevice> electronicDevices, DeviceOnStrategy deviceOnStrategy, int corridorNumber) {
        this.corridorType = corridorType;
        this.corridorNumber = corridorNumber;
        this.electronicDevices = electronicDevices;
        this.deviceOnStrategy = deviceOnStrategy;
        this.currentPowerConsumedByCorridor = this.deviceOnStrategy.defaultDeviceOnOffRule(electronicDevices);
    }

    public int getCurrentPowerConsumedByCorridor() {
        return currentPowerConsumedByCorridor;
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

    public void switchOnLightInCorridor() {
        electronicDevices.forEach(device -> {
            if (device.getelectronicDeviceType().equals(ElectronicDeviceType.LIGHT)) {
                device.on();
                currentPowerConsumedByCorridor += device.getPowerRating();
            }
        });
    }

    public List<ElectronicDevice> listOfAcs() {
        return electronicDevices.stream().filter(e -> e.getelectronicDeviceType().equals(ElectronicDeviceType.AC)).collect(Collectors.toList());
    }

    public int switchOffACsOfCorridor(int power) {
        int powerSaveAfterAcOff = 0;

        for (ElectronicDevice electronicDevice : listOfAcs()) {
            if (electronicDevice.isSwitchOn() && powerSaveAfterAcOff <= power) {
                electronicDevice.off();
                powerSaveAfterAcOff += electronicDevice.getPowerRating();
                currentPowerConsumedByCorridor -= electronicDevice.getPowerRating();
            }
        }
        return powerSaveAfterAcOff;
    }

    public void switchOffLightOfCorridor() {
        electronicDevices.forEach(device -> {
            if (device.getelectronicDeviceType().equals(ElectronicDeviceType.LIGHT)) {
                device.off();
                currentPowerConsumedByCorridor -= device.getPowerRating();
            }
        });
    }

    public int switchOnACsOfCorridor(int extraPower) {
        int powerAfterACsOn = 0;
        for (ElectronicDevice electronicDevice : listOfAcs()) {
            if (!electronicDevice.isSwitchOn() && (powerAfterACsOn + electronicDevice.getPowerRating()) <= extraPower) {
                electronicDevice.on();
                powerAfterACsOn += electronicDevice.getPowerRating();
                currentPowerConsumedByCorridor += electronicDevice.getPowerRating();
            }
        }
        return powerAfterACsOn;
    }
}
