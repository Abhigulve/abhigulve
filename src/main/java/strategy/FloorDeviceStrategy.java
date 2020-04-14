package strategy;

import model.Corridor;
import model.Request;

import java.util.List;

public interface FloorDeviceStrategy {
    void updateDevicesState(List<Corridor> corridors, Request request);
    int defaultMaximumPowerPerFloor(List<Corridor> corridors);
}
