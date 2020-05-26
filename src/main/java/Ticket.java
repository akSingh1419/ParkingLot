import javax.inject.Inject;

class Ticket {
    private static Integer ticketCounter = 0;
    private final Integer ticketNumber;
    private final Car carAssociated;

    @Inject
    Ticket(Car carAssociated) {
        ticketCounter++;
        this.ticketNumber = ticketCounter;
        this.carAssociated = carAssociated;
    }

    public Integer getTicketNumber() {
        return ticketNumber;
    }

    public Car getCarAssociated() {
        return carAssociated;
    }
}