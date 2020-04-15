package model;

public enum ElectronicDeviceType {
    LIGHT(5), AC(10);

    private int units = 0;

    ElectronicDeviceType(int units) {
        this.units = units;
    }

    public int getUnits() {
        return units;
    }
}
