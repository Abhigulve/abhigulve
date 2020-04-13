import model.ElectronicDevice;
import model.ElectronicDeviceType;

public class LightBulb extends ElectronicDevice {

    public LightBulb(boolean electronicSwitch, int powerRating, ElectronicDeviceType deviceName) {
        super(powerRating, deviceName);
    }
}
