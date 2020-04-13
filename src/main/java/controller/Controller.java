package controller;

import model.Hotel;
import model.Request;

public class Controller {
    private Hotel hotel;

    public Controller(Hotel hotel) {
        this.hotel = hotel;
    }

    public void update(Request o) {
        //here start the real game
        hotel.updateHotelDeviceStatus(o);
    }
}