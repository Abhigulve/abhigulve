package strategy;

import model.Corridor;

import java.util.List;

public interface FloorDeviceStrategy {
    void updateDevicesDefault(List<Corridor> corridors);
    int defaultMaximumPowerPerFloor(List<Corridor> corridors);
}
