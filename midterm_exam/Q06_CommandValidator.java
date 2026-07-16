public class Q06_CommandValidator {
    public static void main(String[] args) {
        String[] commands = {
            "START",
            new String("stop"),
            "  Pause  ",
            "RUN",
            "   ",
            null
        };

        for (String command : commands) {
            System.out.println(command + " -> " + isValidCommand(command));
        }
    }

    public static boolean isValidCommand(String command) {
        // 1. Correctly handle null to avoid NullPointerException
        if (command == null) {
            return false;
        }

        // 2. Remove leading/trailing whitespace
        String cleanCommand = command.trim();

        // 3. Ignore case and compare content using equalsIgnoreCase
        return cleanCommand.equalsIgnoreCase("START") 
            || cleanCommand.equalsIgnoreCase("STOP") 
            || cleanCommand.equalsIgnoreCase("PAUSE");
    }
}