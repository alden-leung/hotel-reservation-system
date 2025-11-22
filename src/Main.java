import java.util.Scanner;

public class Main {

    private static final Scanner kbd = new Scanner(System.in);         // user input reader
    private static final String[][] standard = new String[15][10];     // standard rooms for 10 days
    private static final String[][] deluxe = new String[10][10];       // deluxe rooms for 10 days
    private static final String[][] suite = new String[5][10];         // suite rooms for 10 days

    public static void main(String[] args) {
        //TODO: User Interface
        checkOut();
    }

    /*
    MAIN PROCESSES
     */
    private static void roomAvailability(){
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

        return ""; // just a placeholder to avoid errors
    }

    private static void printTable() {
        //TODO: print table layout. (using the standard, deluxe, and suite 2D-arrays)

    }
}