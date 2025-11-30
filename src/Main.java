import java.util.Scanner;

public class Main {

    private static final Scanner kbd = new Scanner(System.in);  // user input reader
    private static final String[][] standard = new String[15][10]; // standard rooms for 10 days
    private static final String[][] deluxe = new String[10][10];   // deluxe rooms for 10 days
    private static final String[][] suite = new String[5][10];     // suite rooms for 10 days

    public static void main(String[] args) {
        //TODO: User Interface
        initializeTables();
    }

    /*
    MAIN PROCESSES
    */
    private static void roomAvailability() {

    }

    private static boolean makeReservation() {
        //TODO: should return true if the reservation is successful and false if otherwise
        return false; // placeholder
    }

    private static boolean checkIn() {
        //TODO: should return true if check-in is successful and false if otherwise
        return false; // placeholder
    }

    private static boolean checkOut() {
        //TODO: should return true if check-out is successful and false if otherwise
        return false; // placeholder
    }

    private static boolean payment() {
        //TODO: should return true if payment is successful and false if otherwise
        return false; // placeholder
    }

    /*
    HELPER FUNCTIONS
    */
    private static String getUserInput(String message) {
        //TODO: print input message and return user input
        return ""; // placeholder
    }

    private static void initializeTables() {
        for (int i = 0; i < standard.length; i++)
            for (int j = 0; j < 10; j++)
                standard[i][j] = "Available";

        for (int i = 0; i < deluxe.length; i++)
            for (int j = 0; j < 10; j++)
                deluxe[i][j] = "Available";

        for (int i = 0; i < suite.length; i++)
            for (int j = 0; j < 10; j++)
                suite[i][j] = "Available";
    }

    private static void standardTable() {
        System.out.println("__________________________________________________________________________________________________________________________");
        System.out.print("|Standard ");
        for (int d = 1; d <= 10; d++) System.out.print("|  Day " + d + "   ");
        System.out.println("|\n__________________________________________________________________________________________________________________________");

        for (int i = 0; i < standard.length; i++) {
            System.out.print("| S" + (101 + i) + "     ");
            for (int j = 0; j < 10; j++) {
                System.out.print("|" + standard[i][j] + " ");
            }
            System.out.println("|\n_________________________________________________________________________________________________________________________");
        }
    }

    private static void deluxeTable() {
        System.out.println("__________________________________________________________________________________________________________________________");
        System.out.print("| Deluxe  ");
        for (int d = 1; d <= 10; d++) System.out.print("|  Day " + d + "   ");
        System.out.println("|\n__________________________________________________________________________________________________________________________");

        for (int i = 0; i < deluxe.length; i++) {
            System.out.print("| D" + (201 + i) + "     ");
            for (int j = 0; j < 10; j++) {
                System.out.print("|" + deluxe[i][j] + " ");
            }
            System.out.println("|\n_________________________________________________________________________________________________________________________");
        }
    }

    private static void suiteTable() {
        String[] suitesNames = {"T301", "T302", "T303", "T304", "T305"};

        System.out.println("_____________________________________________________________________________________________________________________");
        System.out.print("|     Suite     ");
        for (int d = 1; d <= 10; d++) System.out.print("|  Day" + d + "   ");
        System.out.println("|\n_____________________________________________________________________________________________________________________");

        for (int i = 0; i < suite.length; i++) {
            System.out.print("| " + suitesNames[i] + " ");
            for (int j = 0; j < 10; j++) {
                System.out.print("|" + suite[i][j] + " ");
            }
            System.out.println("|\n_____________________________________________________________________________________________________________________");
        }
    }
}
