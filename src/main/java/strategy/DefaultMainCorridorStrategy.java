package strategy;

import model.ElectronicDevice;
import model.ElectronicDeviceType;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultMainCorridorStrategy implements DeviceOnStrategy {
    @Override
    public int defaultDeviceOnOffRule(List<ElectronicDevice> electronicDeviceList) {
        int powerConsumption = 0;
        for (ElectronicDevice electronicDevice : electronicDeviceList) {
//            if (electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.AC) || electronicDevice.getelectronicDeviceType().equals()) {
            electronicDevice.on();
            powerConsumption += electronicDevice.getPowerRating();
//            }
        }
        return powerConsumption;
    }

    private List<ElectronicDevice> getLightBulb(List<ElectronicDevice> electronicDeviceList) {
        return electronicDeviceList.stream().filter(electronicDevice -> electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.BULB)).collect(Collectors.toList());
    }

    private List<ElectronicDevice> getACs(List<ElectronicDevice> electronicDeviceList) {
        return electronicDeviceList.stream().filter(electronicDevice -> electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.AC)).collect(Collectors.toList());
    }

    @Override
    public int startDeviceInCorridor(List<ElectronicDevice> electronicDeviceList) {
        int getUnitOfExtraUnitsNeededToStartBulb = getUnitOfExtraUnitsNeededToStarBulb(electronicDeviceList);
//        int totalPower = currentPowerConsumption + getUnitOfExtraUnitsNeededToStartBulb;
//        if (totalPower > maxPowerConsumptionPerFloor) {
//            needToStopSomeDevices(getACs(electronicDeviceList), totalPower - maxPowerConsumptionPerFloor);
//        }
//        startBulbOfCorridor(electronicDeviceList);
        return electronicDeviceList.stream().mapToInt(ElectronicDevice::getPowerRating).sum();
    }

    private void startBulbOfCorridor(List<ElectronicDevice> electronicDeviceList) {
        electronicDeviceList.forEach(device -> {
            if (device.getelectronicDeviceType().equals(ElectronicDeviceType.BULB))
                device.on();
        });
    }

    private void needToStopSomeDevices(List<ElectronicDevice> electronicDeviceList, int extraUnits) {
        int i = 0;
        while (extraUnits > 0) {
            extraUnits -= electronicDeviceList.get(i).getPowerRating();
            electronicDeviceList.get(i).off();
        }
    }

    private int getUnitOfExtraUnitsNeededToStarBulb(List<ElectronicDevice> electronicDeviceList) {
        int getUnitOfExtraUnitsNeededToStarBulb = 0;
        List<ElectronicDevice> lightBulb = getLightBulb(electronicDeviceList);
        for (ElectronicDevice electronicDevice : lightBulb) {
            getUnitOfExtraUnitsNeededToStarBulb += electronicDevice.getPowerRating();
        }
        return getUnitOfExtraUnitsNeededToStarBulb;
    }

    @Override
    public int updateDeviceStatusNoWhenMovement(List<ElectronicDevice> electronicDeviceList, int currentPowerConsumption, int maxPowerConsumptionPerFloor) {


        return 0;
    }

}
