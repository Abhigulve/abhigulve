package test;

import builder.HotelBuilder;
import controller.Controller;
import model.*;
import org.junit.Before;
import org.junit.Test;
import sensor.Sensor;
import strategy.DefaultMainCorridorStrategy;
import strategy.DefaultSubCorridorDeviceStrategy;

import java.util.ArrayList;
import java.util.List;


public class ApplicationTest {
    private Sensor sensor = new Sensor();
    private Controller observer;

    @Before
    public void test() {

        List<Corridor> corridors = getSubCorridors();
        List<Corridor> mainCorridor = getMainCorridor();
        corridors.addAll(mainCorridor);
        observer = new Controller(new HotelBuilder().addFloor(0, corridors).build());
        sensor.subscribe(observer);
    }

    private List<Corridor> getMainCorridor() {
        List<Corridor> corridors = new ArrayList<>();
        List<ElectronicDevice> electronicDevices = getElectronicDevices();
        corridors.add(new Corridor(CorridorType.MAINCORRIDOR, electronicDevices, new DefaultMainCorridorStrategy(), 1));
        return corridors;
    }

    private List<Corridor> getSubCorridors() {
        List<Corridor> corridors = new ArrayList<>();
        List<ElectronicDevice> electronicDevices = getElectronicDevices();
        corridors.add(new Corridor(CorridorType.SUBCORRIDOR, electronicDevices, new DefaultSubCorridorDeviceStrategy(), 1));
        corridors.add(new Corridor(CorridorType.SUBCORRIDOR, getElectronicDevices(), new DefaultSubCorridorDeviceStrategy(), 2));
        return corridors;
    }

    private List<ElectronicDevice> getElectronicDevices() {
        List<ElectronicDevice> electronicDevices = new ArrayList<>();
        electronicDevices.add(new ElectronicDevice(5, ElectronicDeviceType.BULB));
        electronicDevices.add(new ElectronicDevice(10, ElectronicDeviceType.AC));
        return electronicDevices;
    }

    @Test
    public void endToEndTest() {
        sensor.setState(new Request(true, 0, CorridorType.SUBCORRIDOR, 1));
    }

}
