import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Car Testing")
class CarTest {

    public static Vehicle car;
    public static String TEST_COLOR = "test-color";
    public static String TEST_REG_NUM = "reg-num";
    public static Slot slot = new Slot(10);
    public static Injector injector;

    @BeforeAll
    public static void init() {
        injector = Guice.createInjector(new AppModule());
        car = injector.getInstance(Car.class);
    }

    @Nested
    @DisplayName("Tests after initialisation")
    class WhenNew {

        @Test
        @DisplayName("Check Color Assigned to car")
        void testGetColor() {
            assertEquals(TEST_COLOR, car.getColor());
        }

        @Test
        @DisplayName("Check Registration Number Assigned to car")
        void testGetRegNumber() {
            assertEquals(TEST_REG_NUM, car.getRegNumber());
        }

        @Test
        void testDefaultSlotIsNull() {
            assertNull(car.slotAssociated);
        }

        @Nested
        @DisplayName("Test Methods After all Initialisation Tests are Complete")
        class After {

            @Test
            @DisplayName("Test Method for Allocation of Slot")
            void testSetSlot() {
                car.setSlot(slot);
                assertSame(car.slotAssociated, slot);
                car.freeSlot();
            }

            @Test
            @DisplayName("Test Method for Clearing of Slot")
            void testFreeSlot() {
                car.setSlot(slot);
                assertSame(car.slotAssociated, slot);
                car.freeSlot();
                assertNull(car.slotAssociated);
            }
        }
    }
}