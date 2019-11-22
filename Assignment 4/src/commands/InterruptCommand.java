package commands;

import devices.Device;

public class InterruptCommand implements Command {
    Device device;
    public InterruptCommand(Device device){
        this.device = device;

    }
    @Override
    public Device execute() {
        device.interrupt();
        return null;
    }

    @Override
    public Device undo() {
        return null;
    }

    @Override
    public String toString() {
        return "Interrupt";
    }

    @Override
    public void updateDevice(Device device) {
        this.device = device;
    }
}
