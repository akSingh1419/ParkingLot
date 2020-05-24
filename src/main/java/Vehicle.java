public abstract class Vehicle {
    public String regNumber;
    public String color;
    public Slot slotAssociated;

    Vehicle(String regNumber, String color) {
        this.regNumber = regNumber;
        this.color = color;
    }

    public abstract void setSlot(Slot slotAssociated);

    public abstract void freeSlot();

    public abstract String getColor();

    public abstract String getRegNumber();
}