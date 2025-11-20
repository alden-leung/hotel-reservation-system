import java.util.Scanner;

public class Main {

    private final Scanner kbd = new Scanner(System.in);         // user input reader
    private final String[][] standard = new String[15][10];     // standard rooms for 10 days
    private final String[][] deluxe = new String[10][10];       // deluxe rooms for 10 days
    private final String[][] suite = new String[5][10];         // suite rooms for 10 days

    public static void main(String[] args) {
        //TODO: User Interface

        checkIn();
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
        /**
         * 4. Check-In Guest (walk-in)
         * Input: input Guest Name, Room type and number, and payment (room only)
         * Process: Verify room availability. Update room status to 'Occupied'.
         * Output: Display "Check-In Successful”, Guest [Name] is now occupying Room [Number].
         *
         * algo
         * ask their name, room type (number)n and payment
         */

        // Scanner
        Scanner beep = new Scanner(System.in);

        // Name of Walk in guest
        System.out.print("input Guest Name: ");
        String gName = beep.nextLine();

        // Room Type
        System.out.print("input Room Type: (1. Standard, 2.Deluxe,3.Suite): ");
        int roomT = Integer.parseInt(beep.nextLine());

        // night booked
        System.out.print("input Nights Booked: ");
        int nightsB = Integer.parseInt(beep.nextLine());
        // need validation up to until 10 days

        //Payment
        System.out.println("input Payment");//add + method maybe? + for calcuu
        int payment = Integer.parseInt(beep.nextLine());

        //update stats
        //check in succ text
        //guest x is now occ room x for x night

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