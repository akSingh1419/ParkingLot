import javax.inject.Inject;

class Slot {
    private Integer slotNumber;
    private Car carAssociated;
    private Ticket ticketAssociated;
    private Boolean free;

    @Inject
    Slot() {
        free = true;
    }

    Slot(Integer slotNumber) {
        free = true;
        this.slotNumber = slotNumber;
    }

    public String toString() {
        return (slotNumber + 1) + " "
                + carAssociated.getRegNumber() + " "
                + carAssociated.getColor();
    }

    public Boolean isAvailable() {
        return free;
    }

    public void fillSlot(Car carAssociated, Ticket ticketAssociated) {
        free = false;
        this.carAssociated = carAssociated;
        this.ticketAssociated = ticketAssociated;
    }

    public void emptySlot() {
        free = true;
        this.carAssociated = null;
        this.ticketAssociated = null;
    }

    public String getCarColor() {
        return carAssociated.getColor();
    }

    public String getCarRegNumber() {
        return carAssociated.getRegNumber();
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public Ticket getTicketAssociated() {
        return ticketAssociated;
    }

    public Car getCarAssociated() {
        return carAssociated;
    }
}