package strategy;

import model.ElectronicDevice;
import model.ElectronicDeviceType;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultSubCorridorDeviceStrategy implements DeviceOnStrategy {

    @Override
    public int defaultDeviceOnOffRule(List<ElectronicDevice> electronicDeviceList) {
        int powerConsumption = 0;
        for (ElectronicDevice electronicDevice : electronicDeviceList) {
            if (electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.AC)) {
                electronicDevice.on();
                powerConsumption += electronicDevice.getPowerRating();
            }
        }
        return powerConsumption;
    }
}
