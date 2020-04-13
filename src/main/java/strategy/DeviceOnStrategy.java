package strategy;

import model.ElectronicDevice;

import java.util.List;

public interface DeviceOnStrategy {


    int defaultDeviceOnOffRule(List<ElectronicDevice> electronicDeviceList);

    int startDeviceInCorridor(List<ElectronicDevice> electronicDeviceList);

    int updateDeviceStatusNoWhenMovement(List<ElectronicDevice> electronicDeviceList, int currentPowerConsumption, int maxPowerConsumptionPerFloor);
}
