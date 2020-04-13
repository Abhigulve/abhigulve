package commands;

import model.ElectronicDevice;

public class DeviceOnCommand implements Command {

    private ElectronicDevice device;

    public DeviceOnCommand(ElectronicDevice device) {
        this.device = device;
    }

    @Override
    public void execute() {

        device.on();
    }

    @Override
    public void undo() {
        device.off();
    }
}