import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class TicketTest {
    public static Ticket ticket;
    public static Car car;
    public static Injector injector;

    @BeforeAll
    public static void init() {
        injector = Guice.createInjector(new AppModule());
        car = injector.getInstance(Car.class);
        ticket = new Ticket(car);
    }

    @Test
    void testCarAssociated() {
        assertSame(car, ticket.getCarAssociated());
    }

    @Test
    void testTicketNumber() {
        assertNotNull(ticket.getTicketNumber());
    }
}