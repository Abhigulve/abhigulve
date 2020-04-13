package strategy;

import model.Corridor;
import model.CorridorType;

import java.util.List;

public class DefaultFloorDeviceStrategy implements FloorDeviceStrategy {
    @Override
    public void updateDevicesDefault(List<Corridor> corridors) {

    }

    @Override
    public int defaultMaximumPowerPerFloor(List<Corridor> corridors) {
        int mainCoridoor = (int) corridors.stream().filter(corridor -> corridor.getCorridorType().equals(CorridorType.MAINCORRIDOR)).count() * 15;
        int subCorridor = (int) corridors.stream().filter(corridor -> corridor.getCorridorType().equals(CorridorType.SUBCORRIDOR)).count() * 10;
        return mainCoridoor + subCorridor;
    }
}
