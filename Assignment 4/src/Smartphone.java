import commands.Command;
import devices.Device;

import java.util.ArrayList;
import java.util.Scanner;

public class Smartphone{
    //every device stores an ArrayList with possible commands (implements Command)
    //there exists a getCommandList-function for every device which returns the list
    //then we process the user input with a HashMap "start" gets mapped to startCommand-object
    //returnValue = hashMap.get(input).execute(), execute returns an object if the state gets changed or returns null
    //if nothing is changed (in order to update the devices list accordingly
    //if returnValue not null then
    //  for device : devices
    //      if device.getClass().getInterfaces()[0] == returnValue.getClass().getInterfaces()[0] then
    //          device = returnValue, not sure if this works but it's just exchanging the device at this index basically
    private ArrayList<Device> devices;
    private ArrayList<Command> commands;
    private boolean appOpen;


    public Smartphone(){
        this.appOpen = true;
        this.devices = new ArrayList<>();
        this.commands = new ArrayList<>();
    }

    private void mainMenu(){
        while(this.appOpen){
            String input;

            System.out.println("Which one of the following devices you'd like to modify?\n");
            for(Device device:devices){
                System.out.format("- %s\n", device.printState());
            }
            System.out.println("\nClose app\n");
            input = inputCheck().toLowerCase();
            if(input.equals("close app") || input.equals("close")){
                this.appOpen = false;
                break;
            }
            for(int i = 0; i < devices.size(); i++){
                Device device = devices.get(i);
                if(device.printState().toLowerCase().equals(input)){
                    this.commands = devices.get(i).getCommandList();
                    subMenu(device);
                    break;
                }
                if(i + 1 == devices.size()){
                    System.out.println("\nThis doesn't exist in the menu, please choose something from the menu.\n");
                    input = inputCheck().toLowerCase();
                    i = 0;
                }
            }
        }
    }

    private void subMenu(Device device){
        while(true){
            String input;
            Device statusChanged;
            System.out.format("What do you want to do with the %s?\n\n", device.printState());
            for (String command : device.getAvailableCommands()){
                System.out.format("- %s\n", command);
            }
            System.out.println("\nBack to devices menu\n");
            input = inputCheck().toLowerCase();
            if(input.equals("back to devices menu")){
                break;
            }
            for(int i = 0; i < commands.size(); i++){
                Command command = commands.get(i);
                if(command.toString().toLowerCase().equals(input)){
                    statusChanged = command.execute();
                    if(statusChanged != null){
                        for(int j = 0; j < devices.size(); j++){
                            if(devices.get(j).printState().equals(statusChanged.printState())){
                                devices.set(j,statusChanged);
                                device = statusChanged;
                                break;
                            }
                        }
                    }
                    break;
                }
                if(i + 1 == commands.size()){
                    System.out.println("This doesn't exist in the menu, please choose something from the menu.\n");
                    input = inputCheck().toLowerCase();
                    i = 0;
                }
            }
        }
    }

    public void addDevice(Device device){
        this.devices.add(device);
    }

    private String inputCheck(){
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        while(input.equals("") || !input.matches("^[a-z A-Z]*$")){
            System.out.println("This doesn't exist in the menu, please choose something from the menu.\n");
            input = scanner.nextLine();
        }
        return input;
    }

    public void start(){mainMenu();}
}
