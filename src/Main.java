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
    private static void roomAvailability(){
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
        4. Check-In Guest (walk-in)
        Input: input Guest Name, Room type and number, and payment (room only)
        Process: Verify room availability. Update room status to 'Occupied'.
        Output: Display "Check-In Successful”, Guest [Name] is now occupying Room [Number].

        Process:
        1. input name of walk in gues
        2. input what type of room they will get
        3. input night booked
        4. verify if there is room availability. update room stat to occupied
        5. display check in successful with guest name is now occupying room num    
         */

        //guest name
        String guestName = getUserInput("Input Guest Name: "); 
        
        String roomType = "";
        int roomRate = 0;
        //room type
        do {
            roomType = getUserInput("Input Room Type: (1. Standard, 2. Deluxe, 3. Suite): ");

            switch (roomType) {
                case "1" -> {checkRoomStatus(standard); roomRate = 2500; roomType = "Standard";}
                case "2" -> {checkRoomStatus(deluxe); roomRate = 4000;roomType = "Deluxe";}
                case "3" -> {checkRoomStatus(suite); roomRate = 8000;roomType = "Suite";}
                default -> {
                    System.out.println("Invalid room type, try again");
                    roomType = "False";
                }
            }

        } while (roomType.equals("False"));
        
        //night booked
        System.out.print("input nights booked: ");
        int nightBooked = Integer.parseInt(kbd.nextLine());

        System.out.println("Processing Walk-in Check-In... Checking for available " + roomType + " rooms"+ "(Php "+roomRate +"/night)...");

        /*
        need to do tomorrow:
        - check availability for the room type selected
        - if available, proceed to payment
        - if payment successful, update room status to occupied
        - display check-in successful message with guest name and room number
         */
        

        return false; // just a placeholder to avoid errors
    }

    private static boolean checkOut() {
        //TODO: should return true if check-out is successful and false if otherwise

        //!!!NOT FINISH!!!
        /**
        TO ADD:
        1. validation for a room
        2. loop when invalid input
        3.
         */

        double bill = 8000; //bill should be determined at reservation / check-in =)
        String name = "gabriel"; //name should be from reservation / check-in
        int sFee = 250; //service fee
        double tBill = bill+sFee; //bill added with fee
        double tax = tBill*0.1; //10% tax to bill w/ fee
        double fBill = tBill + tax; //add the 10%
        double payment, change = 0; //initial values

        System.out.print("Input room number for checkout: ");
        String room = kbd.nextLine();
        //will add validation when final table is finished.
        System.out.println("Verifying checkout for room " + room);

        //bill + checkout calculation
        System.out.println("\n--- Bill Checkout ---");
        System.out.println("Subtotal (Room Rate Only): P" + bill);
        System.out.println("Fixed Service Fee: P" + sFee);
        System.out.println("Subtotal + Fee: P" + tBill);
        System.out.println("Tax (10% of P" + tBill + "): P" + tax);
        System.out.println("Total Amount Due: P" + tBill + " + P" + tax + " = P" +fBill);

        //payment
        System.out.println("*****call payment method here****\n");
//        System.out.println(payment(room)); // call payment method

        //room vacant announcement
        System.out.println("\nRoom " +room+ " is now available");
        //update room table to blank/available

        return true; // just a placeholder to avoid errors
    }

    private static boolean payment(String room) {
        //TODO: should return true if payment is successful and false if otherwise
        double bill = 0; //bill should be determined at reservation / check-in =)
        String name = "name"; //name should be from reservation / check-in
        int sFee = 250;
        double tBill = bill+sFee;
        double tax = tBill*0.1;
        double fBill = tBill + tax;
        double payment, change = 0;

        do {
            System.out.print("Input Payment Amount: ");
            payment = Double.parseDouble(kbd.nextLine());
            if (payment<fBill) {
                System.out.println("Amount input is less than balance.");
            }
        } while(payment < 0 || payment < fBill);

        System.out.println("Payment: P"+payment+" received.");
        if(payment>fBill) {
            change = payment-fBill;
            System.out.println("Change Calculation: P" + payment + " + P" + fBill + " = P" + change);
        }
        //final receipt
        System.out.println("\n--- Final Bill / Receipt ---");
        System.out.println("Guest: " + name + " | " + "Room: " + room);
        System.out.println("TOTAL AMOUNT DUE: P"+ fBill);
        System.out.println("Amount Paid: P" + payment);
        if (payment>fBill) {
            System.out.println("Change: P" + change);
        }

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

        return ""; // just a placeholder to avoid errors
    }

    private static void printTable() {
        //TODO: print table layout. (using the standard, deluxe, and suite 2D-arrays)

    }
}