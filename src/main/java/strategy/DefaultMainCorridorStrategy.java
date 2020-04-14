package strategy;

import model.ElectronicDevice;

import java.util.List;

public class DefaultMainCorridorStrategy implements DeviceOnStrategy {
    @Override
    public int defaultDeviceOnOffRule(List<ElectronicDevice> electronicDeviceList) {
        int powerConsumption = 0;
        for (ElectronicDevice electronicDevice : electronicDeviceList) {
            electronicDevice.on();
            powerConsumption += electronicDevice.getPowerRating();
        }
        return powerConsumption;
    }
}
