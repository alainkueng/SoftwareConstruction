package commands;

import devices.Device;
import devices.Oven_Device.Oven;

public class SetProgramCommand implements Command {
    Oven oven;
    String program;

    public SetProgramCommand(Oven oven){
        this.oven = oven;
    }
    @Override
    public Device execute() {
        oven.setProgram();
        return null;
    }

    @Override
    public Device undo() {
        oven.interrupt();
        return null;
    }

    @Override
    public String toString() {
        return "Set program";
    }

    @Override
    public void updateDevice(Device device) {
        this.oven = (Oven)device;
    }
}
