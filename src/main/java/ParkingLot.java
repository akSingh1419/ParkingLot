import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Ordering;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

interface RunCommand {
    void execute();
}

class ParkingLot {
    private final Integer numOfSlots;
    private final Slot[] slotList;
    private final TreeSet<Integer> emptySlots;
    private final BiMap<String, Integer> mapRegNumToSlots;

    ParkingLot(Integer numOfSlots) {
        emptySlots = new TreeSet<Integer>();
        mapRegNumToSlots = HashBiMap.create();
        this.numOfSlots = numOfSlots;
        slotList = new Slot[numOfSlots];
        for (int i = 0; i < numOfSlots; i++) {
            slotList[i] = new Slot();
            emptySlots.add(i);
        }
        System.out.println("Created a parking lot with " + numOfSlots + " slots");
    }

    private Integer getNearestEmptySlot() {
        if (emptySlots.isEmpty()) {
            return -1;
        } else {
            return emptySlots.first();
        }
    }

    public Integer bookSlot(Car car) {
        if (mapRegNumToSlots.containsKey(car.getRegNumber())) {
            Integer slotNum = mapRegNumToSlots.get(car.getRegNumber());
            System.out.println("Car is already parked at " + (slotNum + 1));
            return slotNum + 1;
        }
        Integer freeSlot = getNearestEmptySlot();
        if (freeSlot.equals(-1)) {
            System.out.println("Sorry, parking lot is full");
            return -1;
        }
        Ticket associatedTicket = new Ticket(car);
        slotList[freeSlot] = new Slot(freeSlot);
        slotList[freeSlot].fillSlot(car, associatedTicket);
        car.setSlot(slotList[freeSlot]);
        mapRegNumToSlots.put(car.getRegNumber(), freeSlot);
        emptySlots.remove(freeSlot);
        System.out.println("Allocated slot number: " + (freeSlot + 1));
        return freeSlot + 1;
    }

    public Boolean clearSlot(Integer slotNum) {
        slotNum--;
        Preconditions.checkElementIndex(slotNum, numOfSlots, "Invalid slot");
        Preconditions.checkNotNull(slotList[slotNum], "Slot is already empty.");
        Car carAssociated = slotList[slotNum].getCarAssociated();
        carAssociated.freeSlot();
        slotList[slotNum].emptySlot();
        slotList[slotNum] = null;
        mapRegNumToSlots.remove(carAssociated.getRegNumber());
        emptySlots.add(slotNum);
        System.out.println("Slot number " + (slotNum + 1) + " is free");
        return true;
    }

    public Boolean getRegNumByColor(String color) {
        ArrayList<String> reqCarSet = new ArrayList<String>();
        for (int i = 0; i < numOfSlots; i++) {
            if ((emptySlots.contains(i) == false) && (slotList[i].getCarColor().equals(color))) {
                reqCarSet.add(slotList[i].getCarRegNumber());
            }
        }
        Integer size = reqCarSet.size();
        if (size != 0) {
            for (int i = 0; i < reqCarSet.size(); i++) {
                String regNum = reqCarSet.get(i);
                if (i != size - 1) {
                    System.out.print(regNum + ", ");
                } else {
                    System.out.print(regNum + "\n");
                }
            }
            return true;
        } else {
            System.out.println("Not found");
            return false;
        }
    }

    public Boolean getSlotNumByRegNumber(String regNumber) {
        if (mapRegNumToSlots.containsKey(regNumber)) {
            Integer slotNumber = mapRegNumToSlots.get(regNumber);
            System.out.println((slotNumber + 1));
            return true;
        } else {
            System.out.println("Not found");
            return false;
        }
    }

    public Boolean getSlotNumByColor(String color) {
        ArrayList<Integer> reqSlotSet = new ArrayList<Integer>();
        for (String regNum : mapRegNumToSlots.keySet()) {
            Integer slotNum = mapRegNumToSlots.get(regNum);
            Slot currSlot = slotList[slotNum];
            if (currSlot.getCarColor().equals(color)) {
                reqSlotSet.add(currSlot.getSlotNumber() + 1);
            }
        }
        Integer size = reqSlotSet.size();
        if (size != 0) {
            for (int i = 0; i < size; i++) {
                Integer slotNum = reqSlotSet.get(i);
                if (i != size - 1) {
                    System.out.print(slotNum + ", ");
                } else {
                    System.out.print(slotNum + "\n");
                }
            }
            return true;
        } else {
            System.out.println("Not found");
            return false;
        }
    }

    public void getStatus() {
        ArrayList<Slot> slotDetails = new ArrayList<Slot>();
        for (String regNum : mapRegNumToSlots.keySet()) {
            Integer slotNum = mapRegNumToSlots.get(regNum);
            slotDetails.add(slotList[slotNum]);
        }
        Ordering<Slot> ordering = new Ordering<Slot>() {
            @Override
            public int compare(Slot slot, Slot t1) {
                return slot.getSlotNumber() - t1.getSlotNumber();
            }
        };
        Collections.sort(slotDetails, ordering);
        System.out.println("\nSlot No. Registration No Colour");
        for (Slot row : slotDetails) {
            System.out.println(row);
        }
        System.out.println();
    }
}

class BookSlot implements RunCommand {
    ParkingLot parkingLot;
    Car car;

    public BookSlot(ParkingLot parkingLot, Car car) {
        this.parkingLot = parkingLot;
        this.car = car;
    }

    @Override
    public void execute() {
        parkingLot.bookSlot(car);
    }
}

class ClearSlot implements RunCommand {
    ParkingLot parkingLot;
    Integer number;

    public ClearSlot(ParkingLot parkingLot, Integer number) {
        this.parkingLot = parkingLot;
        this.number = number;
    }

    @Override
    public void execute() {
        parkingLot.clearSlot(number);
    }
}

class Status implements RunCommand {
    ParkingLot parkingLot;

    public Status(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    @Override
    public void execute() {
        parkingLot.getStatus();
    }
}

class RegNumFromColor implements RunCommand {
    ParkingLot parkingLot;
    String color;

    public RegNumFromColor(ParkingLot parkingLot, String color) {
        this.parkingLot = parkingLot;
        this.color = color;
    }

    @Override
    public void execute() {
        parkingLot.getRegNumByColor(color);
    }
}

class SlotNumFromColor implements RunCommand {
    ParkingLot parkingLot;
    String color;

    public SlotNumFromColor(ParkingLot parkingLot, String color) {
        this.parkingLot = parkingLot;
        this.color = color;
    }

    @Override
    public void execute() {
        parkingLot.getSlotNumByColor(color);
    }
}

class SlotNumFromRegNum implements RunCommand {
    ParkingLot parkingLot;
    String regNum;

    public SlotNumFromRegNum(ParkingLot parkingLot, String regNum) {
        this.parkingLot = parkingLot;
        this.regNum = regNum;
    }

    @Override
    public void execute() {
        parkingLot.getSlotNumByRegNumber(regNum);
    }
}

class ManageParkingLot {
    RunCommand cmd;

    public ManageParkingLot() {
    }

    void setCommand(RunCommand cmd) {
        this.cmd = cmd;
    }

    void executeCommand() {
        cmd.execute();
    }
}