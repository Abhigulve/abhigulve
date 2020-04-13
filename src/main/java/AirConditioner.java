import model.ElectronicDevice;
import model.ElectronicDeviceType;

public class AirConditioner extends ElectronicDevice {

    public AirConditioner(boolean electronicSwitch, int powerRating, ElectronicDeviceType deviceName) {
        super(powerRating,deviceName);
    }
}