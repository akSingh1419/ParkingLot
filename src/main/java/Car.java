import javax.inject.Inject;

class Car extends Vehicle {
    @Inject
    Car(@RegNumValue String regNumber, @ColorValue String color) {
        super(regNumber, color);
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