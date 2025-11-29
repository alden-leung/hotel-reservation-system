import java.util.Scanner;

public class Main {

    private static final Scanner kbd = new Scanner(System.in);         // user input reader
    private static final String[][] standard = new String[15][10];     // standard rooms for 10 days
    private static final String[][] deluxe = new String[10][10];       // deluxe rooms for 10 days
    private static final String[][] suite = new String[5][10];         // suite rooms for 10 days
    private static final String[] dates = {
            "11/17/2025", "11/18/2025", "11/19/2025", "11/20/2025",
            "11/21/2025", "11/22/2025", "11/23/2025", "11/24/2025",
            "11/25/2025", "11/26/2025"
    };

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

        // Reservation Process
        // 1. get guest name
        // 2. get room type
        // 3. display room type status table
        // 4. get number of days to reserve
        // 5. get the dates based on the number of days
        // 6. set successful = false
        // 7. Loop through every "ROOMS" (i.e. the "ROWS" of the array), for each loop:
        //      8.1. Check the columns that matches the dates
        //      8.2. If those columns are available:
        //              8.2.1 Assign the guest with the current "ROOM" for those columns (i.e. Dates) in the format ("1|<name>")
        //              8.2.2 Set successful = true
        //              8.2.3 Stop the loop
        // 9. If successful = false, print a statement to notify guest that no rooms are available for those dates

        String guestName = getUserInput("Input Guest Name: ");
        String roomType = "";

        do {
            roomType = getUserInput("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");

            switch (roomType) {
                case "1" -> checkRoomStatus(standard);
                case "2" -> checkRoomStatus(deluxe);
                case "3" -> checkRoomStatus(suite);
                default -> {
                    System.out.println("Invalid room type, try again");
                    roomType = "False";
                }
            }

        } while (roomType.equals("False"));

        // printTable()

        int numberOfDays = Integer.parseInt(getUserInput("Input number of days: "));
        String[] dates = new String[10];

        for (int i = 0; i < numberOfDays; i++) {
            dates[i] = getUserInput("Date " + (i + 1) + ": ");
        }

        System.out.println("Processing Reservation...");

        return false; // just a placeholder to avoid errors
    }

    private static boolean checkIn() {
    /*
    Process:
    1. Input name of walk-in guest
    2. Input what type of room they will get
    3. Input number of nights booked
    4. Verify if there is room availability
    5. Update room status to occupied
    6. Payment for room
    7. Display check-in successful with guest name and room number
    */

    // 1. Input name of walk-in guest
         String guestName = getUserInput("Input Guest Name: ");

    // 2. Input what type of room they will get

        String roomType = "";
        int bill = 0;

         do {
            roomType = getUserInput("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");

            switch (roomType) {
                case "1" -> {checkRoomStatus(standard); bill = 2500; roomType = "Standard";}
                case "2" -> {checkRoomStatus(deluxe); bill = 4000;roomType = "Deluxe";}
                case "3" -> {checkRoomStatus(suite); bill = 8000;roomType = "Suite";}
                default -> {
                    System.out.println("Invalid room type, try again");
                    roomType = "False";
                }
            }

        } while (roomType.equals("False"));

    // 3. Input of nights booked

        String nBooked = getUserInput("input nights booked:");
        System.out.println("Processing Walk-in Check-In... Checking for available " + roomType + " rooms"+ "(Php "+bill +"/night)...");
        
        
    // 5. Update room status to occupied

    // 6. Payment for room

    // 7. Display check-in successful with guest name and room number

    return false;// just a placeholder to avoid errors
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
        System.out.print(message);
        return kbd.nextLine();
    }

    private static int getColIdx(String date) {
        int day = Integer.parseInt(date.split("/")[1].trim());

        return day - 17;
    }

    private static String checkRoomStatus(String[][] room) {
    
    return "No rooms found."; // fallback
}

    private static void printTable() {
        //TODO: print table layout. (using the standard, deluxe, and suite 2D-arrays)

    }

}