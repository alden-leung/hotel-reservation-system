import java.util.Scanner;

public class Main {

    private final Scanner kbd = new Scanner(System.in);         // user input reader
    private final String[][] standard = new String[15][10];     // standard rooms for 10 days
    private final String[][] deluxe = new String[10][10];       // deluxe rooms for 10 days
    private final String[][] suite = new String[5][10];         // suite rooms for 10 days

    public static void main(String[] args) {
        //TODO: User Interface

    }

    /*
    MAIN PROCESSES
     */
    private static void roomAvailability() {

    }

    private static boolean makeReservation() {
        //TODO: should return true if the reservation is successful and false if otherwise

        return false; // just a placeholder to avoid errors
    }

    private static boolean checkIn() {
        //TODO: should return true if check-in is successful and false if otherwise

        return false; // just a placeholder to avoid errors
    }

    private static boolean checkOut() {
        //TODO: should return true if check-out is successful and false if otherwise

        return false; // just a placeholder to avoid errors
    }

    private static boolean payment() {
        //TODO: should return true if payment is successful and false if otherwise

        return false; // just a placeholder to avoid errors
    }

    /*
    HELPER FUNCTIONS
     */
    private static String getUserInput(String message) {
        //TODO: print input message and return user input as a String

        return ""; // just a placeholder to avoid errors
    }

    private static void printTable() {
        //TODO: print table layout. (using the standard, deluxe, and suite 2D-arrays)

    }
}