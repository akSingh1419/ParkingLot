import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParseInputTest {

    public static ParseInput parse;

    @BeforeEach
    public void init() {
        parse = new ParseInput();
    }

    @Test
    @DisplayName("File not found")
    void fileModeFailure() {
        String path = "some_invalid_path";
        assertFalse(parse.fileMode(path));
    }

    @Test
    @DisplayName("Negative number of slot")
    void createParkingLotFailure() {
        assertThrows(IllegalArgumentException.class, () -> parse.createParkingLot(-10));
    }
}