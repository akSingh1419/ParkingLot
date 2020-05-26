import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Slot Testing")
class SlotTest {

    public static Slot slot;
    public static Injector injector;

    @BeforeAll
    public static void init() {
        injector = Guice.createInjector(new AppModule());
        slot = injector.getInstance(Slot.class);
    }

    @Nested
    @DisplayName("Tests after initialisation")
    class WhenNew {

        @Test
        void testDeafultAvailability() {
            assertTrue(slot.isAvailable());
        }

        @Test
        void testDefaultSlotNumber() {
            assertNull(slot.getSlotNumber());
        }

        @Test
        void testDefaultTicketAssociated() {
            assertNull(slot.getTicketAssociated());
        }

        @Test
        void testDefaultCarAssociated() {
            assertNull(slot.getCarAssociated());
        }
    }
}