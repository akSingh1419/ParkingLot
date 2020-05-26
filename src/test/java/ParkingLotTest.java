import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing ParkingLot")
class ParkingLotTest {

    public static final Integer NUM_OF_SLOTS = 6;
    public static ParkingLot parkingLot;
    public static String TEST_COLOR = "test-color";
    public static String TEST_REG_NUM = "reg-num";
    public static Injector injector;

    @BeforeAll
    public static void init_before() {
        injector = Guice.createInjector(new AppModule());
    }

    @BeforeEach
    public void init_before_each() {
        parkingLot = new ParkingLot(NUM_OF_SLOTS);
    }

    @Test
    void testBookSlotSuccess() {
        Integer expectedSlot = 1;
        Car car = injector.getInstance(Car.class);
        Integer slotNum = parkingLot.bookSlot(car);
        assertEquals(expectedSlot, slotNum);
    }

    @Test
    @DisplayName("Testing for fully filled Parking lot")
    void testNoSlotsLeft() {
        parkingLot = new ParkingLot(0);
        Car car = injector.getInstance(Car.class);
        Integer slotNum = parkingLot.bookSlot(car);
        assertEquals(-1, slotNum);
    }

    @Test
    void testClearSlot() {
        parkingLot = new ParkingLot(1);
        Car car = injector.getInstance(Car.class);
        Integer slotNum = parkingLot.bookSlot(car);
        assertTrue(parkingLot.clearSlot(slotNum));
    }

    @Test
    @DisplayName("Check if already empty slot throws NullPointerException while Clearing")
    void testClearSlotWhenSlotIsAlreadyEmptyButSlotIsAValidSlotNumber() {
        parkingLot = new ParkingLot(10);
        assertThrows(NullPointerException.class, () -> parkingLot.clearSlot(2));
    }

    @Test
    @DisplayName("Check if clearing Out of Bound empty slot throws IndexOutOfBoundsException")
    void testClearSlotWhenSlotIsNotAValidSlotNumber() {
        assertThrows(IndexOutOfBoundsException.class, () -> parkingLot.clearSlot(789));
    }

    @Nested
    @DisplayName("Testing for all Queries made")
    class QueryTest {

        @Test
        void testGetRegNumByColorSuccess() {
            Car car = injector.getInstance(Car.class);
            parkingLot.bookSlot(car);
            assertTrue(parkingLot.getRegNumByColor(TEST_COLOR));
        }

        @Test
        void testGetRegNumByColorFailure() {
            assertFalse(parkingLot.getRegNumByColor(TEST_COLOR));
        }

        @Test
        void testGetSlotNumByRegNumberSuccess() {
            Car car = injector.getInstance(Car.class);
            parkingLot.bookSlot(car);
            assertTrue(parkingLot.getSlotNumByRegNumber(TEST_REG_NUM));
        }

        @Test
        void testGetSlotNumByRegNumberFailure() {
            assertFalse(parkingLot.getSlotNumByRegNumber(TEST_REG_NUM));
        }

        @Test
        void testGetSlotNumByColorSuccess() {
            Car car = injector.getInstance(Car.class);
            parkingLot.bookSlot(car);
            assertTrue(parkingLot.getSlotNumByColor(TEST_COLOR));
        }

        @Test
        void testGetSlotNumByColorFailure() {
            assertFalse(parkingLot.getSlotNumByColor(TEST_COLOR));
        }
    }
}