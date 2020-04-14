package strategy;

import model.ElectronicDevice;

import java.util.List;

public interface DeviceOnStrategy {
    int defaultDeviceOnOffRule(List<ElectronicDevice> electronicDeviceList);
}
