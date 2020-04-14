package model;

public class ElectronicDevice {
    private boolean electronicSwitch;
    private int powerRating;
    private ElectronicDeviceType electronicDeviceType;

    public void on() {
        this.electronicSwitch = true;
    }

    public ElectronicDevice(int powerRating, ElectronicDeviceType electronicDeviceType) {
        this.powerRating = powerRating;
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
        return powerRating;
    }

    public void setPowerRating(int powerRating) {
        this.powerRating = powerRating;
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

