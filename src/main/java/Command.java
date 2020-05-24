import java.util.HashMap;
import java.util.Map;

public enum Command {
    ERROR(""),
    EXIT("exit"),
    CREATE("create_parking_lot"),
    PARK("park"),
    LEAVE("leave"),
    STATUS("status"),
    REG_NUM_FROM_COLOR("registration_numbers_for_cars_with_colour"),
    SLOT_NUMS_FROM_COLOR("slot_numbers_for_cars_with_colour"),
    SLOT_NUM_FROM_REG_NUM("slot_number_for_registration_number");

    private static Map<String, Command> mapLineToCommand;

    static {
        mapLineToCommand = new HashMap<String, Command>();
        for (Command command : Command.values()) {
            mapLineToCommand.put(command.input, command);
        }
    }

    private String input;

    Command(String input) {
        this.input = input;
    }

    public static Command getCommand(String input) {
        return mapLineToCommand.getOrDefault(input, ERROR);
    }
}