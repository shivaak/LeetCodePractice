import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Command interface
interface Command {
    void execute(String[] args);
}

// Concrete command for Add Building
class AddBuildingCommand implements Command {
    @Override
    public void execute(String[] args) {

        // Perform action for Add Building command
    }
}

// Concrete command for Add Floor
class AddFloorCommand implements Command {
    @Override
    public void execute(String[] args) {
        String buildingName = args[0];
        String floorName = args[1];
        System.out.println("Command: Add Floor");
        System.out.println("Building Name: " + buildingName);
        System.out.println("Floor Name: " + floorName);
        // Perform action for Add Floor command
    }
}

// Concrete command for Add ConferenceRoom
class AddConfRoomCommand implements Command {
    @Override
    public void execute(String[] args) {
        String buildingName = args[0];
        String floorName = args[1];
        String confroomId = args[2];
        System.out.println("Command: Add ConferenceRoom");
        System.out.println("Building Name: " + buildingName);
        System.out.println("Floor Name: " + floorName);
        System.out.println("Confroom ID: " + confroomId);
        // Perform action for Add ConferenceRoom command
    }
}

// Concrete command for Book
class BookCommand implements Command {
    @Override
    public void execute(String[] args) {
        String slot = args[0];
        String buildingName = args[1];
        String floorName = args[2];
        String confroomId = args[3];
        System.out.println("Command: Book");
        System.out.println("Slot: " + slot);
        System.out.println("Building Name: " + buildingName);
        System.out.println("Floor Name: " + floorName);
        System.out.println("Confroom ID: " + confroomId);
        // Perform action for Book command
    }
}

// Invoker class
class CommandInvoker {
    private Map<String, Command> commandMap = new HashMap<>();

    public void registerCommand(String commandName, Command command) {
        commandMap.put(commandName, command);
    }

    public void executeCommand(String commandName, String[] args) {
        Command command = commandMap.get(commandName);
        if (command != null && args!=null) {
            command.execute(args);
        } else {
            System.out.println("Invalid command");
        }
    }
}

public class CommandParser {
    public static void main(String[] args) {
        // Create invoker and register commands
        CommandInvoker invoker = new CommandInvoker();
        invoker.registerCommand("Add Building", new AddBuildingCommand());
        invoker.registerCommand("Add Floor", new AddFloorCommand());
        invoker.registerCommand("Add ConferenceRoom", new AddConfRoomCommand());
        invoker.registerCommand("Book", new BookCommand());

        boolean shutdown=false;
        while(!shutdown){
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter command: ");
            String input = scanner.nextLine();

            String[] tokens = input.split(" ");
            StringBuilder command = new StringBuilder();
            String[] commandArgs = null;

            switch(tokens[0]){
                case "Add":
                    command.append(tokens[0]).append(" ").append(tokens[1]);
                    commandArgs = new String[tokens.length - 1];
                    System.arraycopy(tokens, 2, commandArgs, 0, tokens.length - 2);
                    break;
                case "Book":
                    command.append(tokens[0]);
                    commandArgs = new String[tokens.length - 1];
                    System.arraycopy(tokens, 1, commandArgs, 0, tokens.length - 1);
                    break;
                case "Exit":
                    shutdown=true;
                    break;
                default:
                    System.out.println("Invalid command");
            }

            // Execute the command
            invoker.executeCommand(command.toString(), commandArgs);
        }

    }
}