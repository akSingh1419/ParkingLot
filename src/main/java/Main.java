import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = null;
        try {
            sc = InputMode.getScanner(args);
        } catch (Exception e) {
            System.out.println("Error arguments not valid.");
            System.exit(0);
        }
        ParseInput parser = new ParseInput(sc);
        parser.manageParkingLot();
    }
}