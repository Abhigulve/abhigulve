package builder;

import model.Corridor;
import model.Floor;
import model.Hotel;
import strategy.DefaultFloorDeviceStrategy;

import java.util.List;

public class HotelBuilder {
    private Hotel hotel;

    public HotelBuilder() {
        this.hotel = new Hotel();
    }

    public HotelBuilder addFloor(int floorNumber,List<Corridor> corridorList) {
        hotel.addFloor(new Floor(floorNumber,corridorList, new DefaultFloorDeviceStrategy()));
        return this;
    }

    public Hotel build() {
        return this.hotel;
    }
}
