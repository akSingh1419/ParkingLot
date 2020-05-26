import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class FileBasedInput {
    public static Scanner getScanner(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        return new Scanner(file);
    }
}

class InteractiveInput {
    public static Scanner getScanner() {
        return new Scanner(System.in);
    }
}

public class InputMode {
    public static Scanner getScanner(String... args) throws FileNotFoundException {
        if (args.length == 0) {
            return InteractiveInput.getScanner();
        } else {
            return FileBasedInput.getScanner(args[0]);
        }
    }
}