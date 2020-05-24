public class Main {

    public static void main(String[] args) {
        ParseInput parser = new ParseInput();
        if (args.length > 0) {
            parser.fileMode(args[0]);
        } else {
            parser.interactiveMode();
        }
        parser.manageParkingLot();
    }
}