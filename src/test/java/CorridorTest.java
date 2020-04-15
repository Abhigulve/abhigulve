import model.Corridor;
import model.CorridorType;
import model.ElectronicDevice;
import model.ElectronicDeviceType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import strategy.DefaultSubCorridorDeviceStrategy;

import java.util.ArrayList;
import java.util.List;

public class CorridorTest {
    private Corridor corridor;

    @Before
    public void setup() {
        List<ElectronicDevice> electronicDevices = getElectronicDevices();
        corridor = new Corridor(CorridorType.SUBCORRIDOR, electronicDevices, new DefaultSubCorridorDeviceStrategy(), 1);
    }

    private List<ElectronicDevice> getElectronicDevices() {
        List<ElectronicDevice> electronicDevices = new ArrayList<>();
        electronicDevices.add(new ElectronicDevice(5, ElectronicDeviceType.LIGHT));
        electronicDevices.add(new ElectronicDevice(10, ElectronicDeviceType.AC));
        return electronicDevices;
    }

    @Test
    public void switchOffAcsTest() {
        corridor.switchOffACsOfCorridor(10);
        Assert.assertFalse(corridor.listOfAcs().get(0).isSwitchOn());
    }

    @Test
    public void switchONAcsTest() {
        corridor.switchOnACsOfCorridor(10);
        Assert.assertTrue(corridor.listOfAcs().get(0).isSwitchOn());
    }


}
