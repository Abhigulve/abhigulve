package test;

import builder.HotelBuilder;
import controller.Controller;
import model.*;
import org.jetbrains.annotations.NotNull;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sensor.Sensor;
import strategy.DefaultMainCorridorStrategy;
import strategy.DefaultSubCorridorDeviceStrategy;

import java.util.ArrayList;
import java.util.List;


public class ApplicationTest {
    private Sensor sensor;
    private Controller observer;
    private Hotel hotel;

    @Before
    public void test() {
        List<Corridor> corridors = getSubCorridors();
        List<Corridor> mainCorridor = getMainCorridor();
        corridors.addAll(mainCorridor);
        hotel = new HotelBuilder().addFloor(0, corridors).build();
        observer = new Controller(hotel);
        sensor = new Sensor(observer);
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
        electronicDevices.add(new ElectronicDevice(5, ElectronicDeviceType.LIGHT));
        electronicDevices.add(new ElectronicDevice(10, ElectronicDeviceType.AC));
        return electronicDevices;
    }

    @Test
    public void endToEndTest() {
        System.out.println(hotel);
        sensor.setState(new Request(true, 0, CorridorType.SUBCORRIDOR, 2));
        Assert.assertFalse(getElectronicDevice(0, 1, CorridorType.SUBCORRIDOR, ElectronicDeviceType.AC).isSwitchOn());
        sensor.setState(new Request(false, 0, CorridorType.SUBCORRIDOR, 2));
        Assert.assertTrue(getElectronicDevice(0, 1, CorridorType.SUBCORRIDOR, ElectronicDeviceType.AC).isSwitchOn());
        Assert.assertFalse(getElectronicDevice(0, 2, CorridorType.SUBCORRIDOR, ElectronicDeviceType.LIGHT).isSwitchOn());
    }

    @NotNull
    private ElectronicDevice getElectronicDevice(int floorNumber, int corridorNumber, CorridorType corridorType, ElectronicDeviceType electronicDeviceType) {
        return hotel.getFloorByFloorNumber(floorNumber).getCorridorByCorriDorNumber(corridorNumber, corridorType)
                .getElectronicDevices().stream()
                .filter(electronicDevice -> electronicDevice.getelectronicDeviceType()
                        .equals(electronicDeviceType)).findFirst().get();
    }
}
