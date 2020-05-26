import javax.inject.Inject;

class Car implements Vehicle {
    public String regNumber;
    public String color;
    public Slot slotAssociated;

    @Inject
    Car(@RegNumValue String regNumber, @ColorValue String color) {
        this.regNumber = regNumber;
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public Integer getSlotNumber() {
        if (slotAssociated == null) {
            return -1;
        }
        return slotAssociated.getSlotNumber();
    }

    @Override
    public void setSlot(Slot slotAssociated) {
        this.slotAssociated = slotAssociated;
    }

    @Override
    public void freeSlot() {
        slotAssociated = null;
    }
}