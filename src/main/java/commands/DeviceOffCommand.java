package commands;

import model.ElectronicDevice;

public class DeviceOffCommand implements Command {

    private ElectronicDevice Device;

    public DeviceOffCommand(ElectronicDevice Device) {
        this.Device = Device;
    }

    @Override
    public void execute() {
        Device.on();
    }

    @Override
    public void undo() {
        Device.off();
    }
}
