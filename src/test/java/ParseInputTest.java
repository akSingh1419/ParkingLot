import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ParseInputTest {

    public static ParseInput parse;

    @BeforeEach
    public void init() {
        parse = new ParseInput();
    }

    @Test
    @DisplayName("Negative number of slot")
    void createParkingLotFailure() {
        assertThrows(IllegalArgumentException.class, () -> parse.createParkingLot(-10));
    }
}