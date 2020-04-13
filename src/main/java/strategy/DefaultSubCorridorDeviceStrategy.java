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

    @Override
    public int startDeviceInCorridor(List<ElectronicDevice> electronicDeviceList) {
        startBulbInCorridor(electronicDeviceList);
        return getTotalPowerConsumed(electronicDeviceList);
    }

    private int getTotalPowerConsumed(List<ElectronicDevice> electronicDeviceList) {
        return electronicDeviceList.stream().filter(electronicDevice -> electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.BULB))
                .mapToInt(ElectronicDevice::getPowerRating).sum();
    }

    private int getExtraUnitsNeededToStarBulb(List<ElectronicDevice> electronicDeviceList) {
        int getUnitOfExtraUnitsNeededToStarBulb = 0;
        List<ElectronicDevice> lightBulb = getLightBulb(electronicDeviceList);
        for (ElectronicDevice electronicDevice : lightBulb) {
            if (electronicDevice.isSwitchOn())
                getUnitOfExtraUnitsNeededToStarBulb += electronicDevice.getPowerRating();
        }
        return getUnitOfExtraUnitsNeededToStarBulb;
    }

    private List<ElectronicDevice> getLightBulb(List<ElectronicDevice> electronicDeviceList) {
        return electronicDeviceList.stream().filter(electronicDevice -> electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.BULB)).collect(Collectors.toList());
    }

    private List<ElectronicDevice> getACs(List<ElectronicDevice> electronicDeviceList) {
        return electronicDeviceList.stream().filter(electronicDevice -> electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.AC)).collect(Collectors.toList());
    }

    private List<ElectronicDevice> getOffACs(List<ElectronicDevice> electronicDeviceList) {
        return electronicDeviceList.stream().filter(electronicDevice -> electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.AC) && !electronicDevice.isSwitchOn()).collect(Collectors.toList());
    }

    @Override
    public int updateDeviceStatusNoWhenMovement(List<ElectronicDevice> electronicDeviceList, int currentPowerConsumption, int maxPowerConsumptionPerFloor) {
        StopAllBulbs(electronicDeviceList);
        int totalPower = getTotalPowerConsumed(electronicDeviceList);
        if (totalPower < maxPowerConsumptionPerFloor)
            startAcs(getOffACs(electronicDeviceList), maxPowerConsumptionPerFloor - totalPower);

        return getTotalPowerConsumed(electronicDeviceList);
    }

    private void startAcs(List<ElectronicDevice> aCs, int extraUnitsSpared) {
        for (ElectronicDevice ac : aCs) {
            if ((extraUnitsSpared - ac.getPowerRating()) > 0) {
                extraUnitsSpared -= ac.getPowerRating();
                ac.on();
            }
        }
    }

    private void StopAllBulbs(List<ElectronicDevice> electronicDeviceList) {
        for (ElectronicDevice electronicDevice : electronicDeviceList) {
            if (electronicDevice.getelectronicDeviceType().equals(ElectronicDeviceType.BULB)) electronicDevice.off();
        }
    }


    private void startBulbInCorridor(List<ElectronicDevice> electronicDeviceList) {
        electronicDeviceList.forEach(device -> {
            if (device.getelectronicDeviceType().equals(ElectronicDeviceType.BULB))
                device.on();
        });
    }

    private void needToStopACs(List<ElectronicDevice> electronicDeviceList, int extraUnits) {
        int i = 0;
        while (extraUnits > 0) {
            extraUnits -= electronicDeviceList.get(i).getPowerRating();
            electronicDeviceList.get(i).off();
            i++;
        }
    }
}
