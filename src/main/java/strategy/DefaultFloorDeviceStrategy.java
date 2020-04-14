package strategy;

import exceptions.InvalidCorridorException;
import model.*;

import java.util.List;
import java.util.stream.Collectors;

public class DefaultFloorDeviceStrategy implements FloorDeviceStrategy {

    @Override
    public void updateDevicesState(List<Corridor> corridors, Request request) {
        if (request.isMovement())
            updateDeviceStateWhenMovement(corridors, request);
        else updateDeviceStateWhenNoMovement(corridors, request);
    }

    private void updateDeviceStateWhenNoMovement(List<Corridor> corridors, Request request) {
        try {
            Corridor corridor = getCorridor(request.getCorridorNumber(), request.getCorridorType(), corridors);
            corridor.switchOffLightOfCorridor();
            int powerConsumptionPerFloor = getCurrentPowerConsumption(corridors);
            int defaultMaximumPowerPerFloor = defaultMaximumPowerPerFloor(corridors);
            if (powerConsumptionPerFloor < defaultMaximumPowerPerFloor) {
                switchOnAcsOfOtherCorridors(getCorridorsExcept(corridor, corridors),
                        (defaultMaximumPowerPerFloor - powerConsumptionPerFloor));
            }
        } catch (InvalidCorridorException e) {
            e.printStackTrace();
        }
    }

    private void updateDeviceStateWhenMovement(List<Corridor> corridors, Request request) {
        try {
            Corridor corridor = getCorridor(request.getCorridorNumber(), request.getCorridorType(), corridors);
            corridor.switchOnLightInCorridor();
            int powerConsumptionPerFloor = getCurrentPowerConsumption(corridors);
            int defaultMaximumPowerPerFloor = defaultMaximumPowerPerFloor(corridors);
            if (powerConsumptionPerFloor > defaultMaximumPowerPerFloor)
                switchOffAcsOfOtherCorridors(getCorridorsExcept(corridor, corridors),
                        (powerConsumptionPerFloor - defaultMaximumPowerPerFloor));
        } catch (InvalidCorridorException e) {
            e.printStackTrace();
        }
    }

    private int getCurrentPowerConsumption(List<Corridor> corridors) {
        return corridors.stream().mapToInt(Corridor::getCurrentPowerConsumedByCorridor).sum();
    }

    private Corridor getCorridor(int corridorNumber, CorridorType corridorType, List<Corridor> corridors) throws InvalidCorridorException {
        for (Corridor corridor : corridors) {
            if (corridor.getCorridorType().equals(corridorType) && corridor.getCorridorNumber() == corridorNumber)
                return corridor;
        }
        throw new InvalidCorridorException("corridor you requested is not available");
    }


    private List<Corridor> getCorridorsExcept(Corridor corridor, List<Corridor> corridors) {
        return corridors.stream()
                .filter(corridor1 -> !(corridor1.equals(corridor))
                        && corridor1.getCorridorType().equals(corridor.getCorridorType()))
                .collect(Collectors.toList());
    }

    private void switchOffAcsOfOtherCorridors(List<Corridor> corridorList, int extraPower) {
        for (Corridor corridor : corridorList) {
            int powerSaveAfterSwitchOff = corridor.switchOffACsOfCorridor(extraPower);
            if ((extraPower - powerSaveAfterSwitchOff) <= 0) {
                return;
            } else
                extraPower -= powerSaveAfterSwitchOff;
        }
    }

    private void switchOnAcsOfOtherCorridors(List<Corridor> corridorList, int extraPower) {
        for (Corridor corridor : corridorList) {
            int powerSaveAfterSwitchOff = corridor.switchOnACsOfCorridor(extraPower);
            if ((extraPower - powerSaveAfterSwitchOff) <= 0) {
                return;
            } else
                extraPower -= powerSaveAfterSwitchOff;
        }
    }


    @Override
    public int defaultMaximumPowerPerFloor(List<Corridor> corridors) {
        int mainCorridor = (int) corridors.stream().filter(corridor -> corridor.getCorridorType().equals(CorridorType.MAINCORRIDOR)).count() * 15;
        int subCorridor = (int) corridors.stream().filter(corridor -> corridor.getCorridorType().equals(CorridorType.SUBCORRIDOR)).count() * 10;
        return mainCorridor + subCorridor;
    }
}
