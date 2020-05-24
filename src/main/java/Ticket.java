import javax.inject.Inject;

class Ticket {
    private static Integer ticketCounter = 0;
    private Integer ticketNumber;
    private Car carAssociated;

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