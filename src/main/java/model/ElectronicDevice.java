package model;

public class ElectronicDevice {
    private boolean electronicSwitch;
    private ElectronicDeviceType electronicDeviceType;

    public void on() {
        this.electronicSwitch = true;
    }

    public ElectronicDevice(int powerRating, ElectronicDeviceType electronicDeviceType) {
        this.electronicDeviceType = electronicDeviceType;
    }

    public void off() {
        this.electronicSwitch = false;
    }

    public boolean isSwitchOn() {
        return electronicSwitch;
    }

    public void setElectronicSwitch(boolean electronicSwitch) {
        this.electronicSwitch = electronicSwitch;
    }

    public int getPowerRating() {
        return electronicDeviceType.getUnits();
    }

    public ElectronicDeviceType getelectronicDeviceType() {
        return electronicDeviceType;
    }

    public void setelectronicDeviceType(ElectronicDeviceType electronicDeviceType) {
        this.electronicDeviceType = electronicDeviceType;
    }

    @Override
    public String toString() {
        return electronicDeviceType + " : " + (isSwitchOn() ? "ON" : "OFF");
    }
}

