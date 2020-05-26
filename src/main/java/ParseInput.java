import com.google.common.base.Preconditions;

import java.util.Scanner;

public class ParseInput {

    private ParkingLot parkingLot;
    private Scanner sc;

    public ParseInput() {
    }

    public ParseInput(Scanner sc) {
        this.sc = sc;
    }

    public void createParkingLot(Integer numberOfSlots) {
        Preconditions.checkArgument(numberOfSlots > 0, "Negative number of slots is not possible.");
        if (parkingLot != null) {
            System.out.println("Parking lot already exists.");
            return;
        }
        parkingLot = new ParkingLot(numberOfSlots);
    }

    public void manageParkingLot() {
        String input;
        ManageParkingLot manage = new ManageParkingLot();
        while (sc.hasNextLine()) {
            try {
                input = sc.nextLine();
                String[] inputText = input.split("\\s+");
                Command command = Command.getCommand(inputText[0]);
                if (command == Command.EXIT) {
                    System.out.println("Exitting program.");
                    break;
                }
                switch (command) {
                    case CREATE:
                        Preconditions.checkElementIndex(1, 2, "Argument list not complete.");
                        int capacity = Integer.parseInt(inputText[1]);
                        createParkingLot(capacity);
                        break;
                    case PARK:
                        Preconditions.checkElementIndex(2, 3, "Argument list not complete.");
                        String regNum = inputText[1];
                        String color = inputText[2];
                        manage.setCommand(new BookSlot(parkingLot, new Car(regNum, color)));
                        manage.executeCommand();
                        break;
                    case LEAVE:
                        Integer slotNum = Integer.valueOf(inputText[1]);
                        Preconditions.checkElementIndex(1, 2, "Argument list not complete.");
                        manage.setCommand(new ClearSlot(parkingLot, slotNum));
                        manage.executeCommand();
                        break;
                    case STATUS:
                        manage.setCommand(new Status(parkingLot));
                        manage.executeCommand();
                        break;
                    case REG_NUM_FROM_COLOR:
                        Preconditions.checkElementIndex(1, 2, "Argument list not complete.");
                        manage.setCommand(new RegNumFromColor(parkingLot, inputText[1]));
                        manage.executeCommand();
                        break;
                    case SLOT_NUMS_FROM_COLOR:
                        Preconditions.checkElementIndex(1, 2, "Argument list not complete.");
                        manage.setCommand(new SlotNumFromColor(parkingLot, inputText[1]));
                        manage.executeCommand();
                        break;
                    case SLOT_NUM_FROM_REG_NUM:
                        Preconditions.checkElementIndex(1, 2, "Argument list not complete.");
                        manage.setCommand(new SlotNumFromRegNum(parkingLot, inputText[1]));
                        manage.executeCommand();
                        break;
                    default:
                        System.out.println("Invalid command.Try again.");
                        break;
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Incomplete arguments or trying to access invalid slot.");
            } catch (NullPointerException e) {
                System.out.println("Slot is not filled.");
            } catch (IllegalArgumentException e) {
                System.out.println("Number of slots is negative!");
            } catch (Exception e) {
                System.out.println("Exception," + e + ", Please try again");
            }
        }
    }
}
