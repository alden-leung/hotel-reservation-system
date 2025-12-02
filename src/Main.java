/*
Project Name: Hotel Reservation System Simulation
Date: December 3, 2025, WS 8:30-10:00 AM (Laboratory) / 1:30-2:30 PM (Lecture)
Instructor: Janet V. Itliong

Group 3 Members:
1. Asuero, Luke Sebastian Lee
2. Gabriel, Erron John A.
3. Intendencia, Jared Wayne L.
4. Lardizabal, Elois Althea
5. Leung, Alden
6. Rodriguez, Gabriel
7. Tumolva, Iana Klarize

Algorithm:

1. Main Menu

2. Check Room Availability

3. Make New Reservation

4. Check-In (Walk-In)
4.1 Display room types and get user choice.
4.2 If choice = 4, return to main menu.
4.3 Set the selected room type and bill, then get roomArray and roomPrefix.
4.4 Input guest name and number of nights.
4.5 Search each room for enough consecutive free days.
  • If found → mark days as "Occupied", generate room number, proceed to payment.
4.6 Repeat payment until sufficient → on success, print confirmation and return true.
4.7 If no suitable room is found → print no availability and return false.

5. Check-Out Guest/ Generate Bill
5.1 Ask user to input their room number & name (for extra security)
5.2 Create a validation system that determines if the room is occupied & name is valid
5.3 Display room fee, number of nights stayed, 10% tax, fixed service fee, and final bill
5.4 Proceed to payment method
5.5 Create method that makes the room status "Available" in the system.

6. Payment

*/

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
        while(true) {
            int input;

            do {
                System.out.println("\n┌─────────────────────────────────────┐");
                System.out.println("│  Welcome to the Grand Hotel System  │");
                System.out.println("├─────────────────────────────────────┤");
                System.out.println("│ 1. Check Room Availability          │");
                System.out.println("│ 2. Make New Reservation             │");
                System.out.println("│ 3. Check-In Guest (Walk-in)         │");
                System.out.println("│ 4. Check-Out Guest / Generate Bill  │");
                System.out.println("├─────────────────────────────────────┤");
                System.out.println("│ 5. Exit Application                 │");
                System.out.println("└─────────────────────────────────────┘");
                input = Integer.parseInt(getUserInput("Enter your choice: "));

                if (input < 1 || input > 5) {
                    System.out.println("\nPlease choose an option from 1 to 5 only...");
                }
            } while (input < 1 || input > 5);

            if (input == 5) {
                System.out.println("Exiting Application...");
                System.exit(0);
            }

            switch (input) {
                case 1 -> roomAvailability();
                case 2 -> makeReservation();
                case 3 -> checkIn();
                case 4 -> checkOut();
                default -> System.out.println("Unable to process request. Please try again.");
            }
        }
    }

    /*
    MAIN PROCESSES
     */
    private static void roomAvailability() {
        while (true) {
            int input;

            // room availability menu
            do {
                System.out.println("\n┌───────────────────────────┐");
                System.out.println("│  Check Room Availability  │");
                System.out.println("├───────────────────────────┤");
                System.out.println("│ 1. Standard Rooms         │");
                System.out.println("│ 2. Deluxe Rooms           │");
                System.out.println("│ 3. Suite Rooms            │");
                System.out.println("├───────────────────────────┤");
                System.out.println("│ 4. GO BACK                │");
                System.out.println("└───────────────────────────┘");
                input = Integer.parseInt(getUserInput("Enter your choice: "));

                // invalid input message
                if (input < 1 || input > 4) {
                    System.out.println("\nPlease choose an option from 1 to 4 only...");
                }
            } while (input < 1 || input > 4);

            // back to menu
            if (input == 4) {
                System.out.println("Back to Main Menu...");
                break;
            }

            // room type details
            String[] details;
            switch (input) {
                case 1 -> details = getRoomTypeDetails(standard);
                case 2 -> details = getRoomTypeDetails(deluxe);
                case 3 -> details = getRoomTypeDetails(suite);
                default -> details = new String[4];
            }

            // summary
            System.out.println("\n┌────────────────────────────┐");
            System.out.println("│  Room Availability Status  │");
            System.out.println("├────────────────────────────┤");
            System.out.printf("│ %-26s │%n", "Room Type: " + details[0]);
            System.out.printf("│ %-26s │%n", "Total Rooms: " + details[1]);
            System.out.printf("│ %-26s │%n", "Available Rooms: " + details[2]);
            System.out.printf("│ %-26s │%n", "Price Per Night: ₱" + details[3]);
            System.out.println("└────────────────────────────┘");

            // room table
            printTable(getRoomArray(input), getRoomPrefix(input));
        }
    }

    private static void makeReservation() {
        int input, numOfDays, pricePerNight, totalPrice;
        String guestName, roomNumber = "";

        do {
            System.out.println("\n┌────────────────────────┐");
            System.out.println("│  Make New Reservation  │");
            System.out.println("├────────────────────────┤");
            System.out.println("│ 1. Standard Rooms      │");
            System.out.println("│ 2. Deluxe Rooms        │");
            System.out.println("│ 3. Suite Rooms         │");
            System.out.println("├────────────────────────┤");
            System.out.println("│ 4. GO BACK             │");
            System.out.println("└────────────────────────┘");
            input = Integer.parseInt(getUserInput("Enter your choice: "));

            // back to menu
            if (input == 4) {
                System.out.println("Back to Main Menu...");
                return;
            }

            if (input < 1 || input > 4) {
                System.out.println("\nPlease choose an option from 1 to 4 only...");
            }
        } while (input < 1 || input > 4);

        // room table
        String[][] roomArray = getRoomArray(input);

        // room details
        String[] roomDetails = getRoomTypeDetails(roomArray);
        String roomPrefix = getRoomPrefix(input);
        printTable(roomArray, roomPrefix);

        // guest name
        guestName = getUserInput("Input Guest Name: ").trim();

        // number of days
        numOfDays = Integer.parseInt(getUserInput("Input Number of Days: "));

        // price
        pricePerNight = Integer.parseInt(roomDetails[3]);
        totalPrice = pricePerNight * numOfDays;

        // dates
        String[] datesToReserve = new String[numOfDays];

        for (int i = 1; i <= numOfDays; i++) {
            String date = getUserInput("Date " + i + ": ");
            datesToReserve[i - 1] = date;
        }
        
        System.out.println("\nProcessing Reservation...");

        boolean successful = false;


        for (int r = 0; r < roomArray.length; r++) {
            boolean roomAvailable = false;

            // check if room is available for specified dates
            for (String date : datesToReserve) {
                int c = getColIdx(date);

                roomAvailable = roomArray[r][c] == null;
            }

            // if room is available, proceed with the reservation for that room
            if (roomAvailable) {
                for (String date : datesToReserve) {
                    int c = getColIdx(date);

                    roomArray[r][c] = "Booked|" + guestName;
                }

                roomNumber = roomPrefix + (100 + r + 1);

                System.out.println("\nFound: " + roomNumber);

                System.out.println(
                        "Reservation Fee (Room Rate Only): " +
                                "₱" + pricePerNight + " / night x " + numOfDays + " night/s = ₱" + totalPrice
                );

                successful = true;
                break;
            }
        }

        if (successful) {
            System.out.println("\n┌────────────────────────────────────────────┐");
            System.out.println("│             Reservation Summary            │");
            System.out.println("├────────────────────────────────────────────┤");
            System.out.printf("│ %-42s │%n", "Guest Name: " + guestName);
            System.out.printf("│ %-42s │%n", "Room Type: " + roomDetails[0]);
            System.out.printf("│ %-42s │%n", "Room Number: " + roomNumber);
            System.out.printf("│ %-42s │%n", "Total Reservation Fee: ₱" + totalPrice);
            System.out.println("└────────────────────────────────────────────┘");

            System.out.println("Update Status: Room " + roomNumber + " is now set to 'Booked' by " + guestName);
        } else {
            System.out.println("Unable to process reservation...");
        }
    }

    private static boolean checkIn() {
        String roomType = "";
        int bill = 0, input, numOfNights, payment;
       
        String guestName, roomNumber = "";
        //menu display
         do {
            System.out.println("\n┌────────────────────────┐");
            System.out.println("│Check-In Guest Walk (In)│");
            System.out.println("├────────────────────────┤");
            System.out.println("│ 1. Standard Rooms      │");
            System.out.println("│ 2. Deluxe Rooms        │");
            System.out.println("│ 3. Suite Rooms         │");
            System.out.println("├────────────────────────┤");
            System.out.println("│ 4. GO BACK             │");
            System.out.println("└────────────────────────┘");
            input = Integer.parseInt(getUserInput("Enter your choice: "));

            // this one goes to the main menu
            if (input == 4) {
                System.out.println("Back to Main Menu...");
                return false;
            }

            if (input < 1 || input > 4) {
                System.out.println("\nPlease choose an option from 1 to 4 only...");
            }
        } while (input < 1 || input > 4);
            // switch case that  change the value of bill and roomType
            switch (input) {
                case 1 -> {
                            bill = 2500; 
                            roomType = "Standard";
                        }
                case 2 -> {
                            bill = 4000;
                            roomType = "Deluxe";
                        }
                case 3 -> {
                            bill = 8000;
                            roomType = "Suite";
                        }
            }
    // room details
        String[][] roomArray = getRoomArray(input);
        String roomPrefix = getRoomPrefix(input);

    // guest name 
    do {
        guestName = getUserInput("Input Guest Name: ").trim();// input of user
    
        if (guestName.isEmpty()) {//validation of the name of the user if they gave a blank
            System.out.println("Guest name cannot be empty. Please try again."); 
        }

        } while (guestName.isEmpty());
        
    // number of nights bro wants to sleep in the hotel
    boolean finish = true;
    do{
        numOfNights = Integer.parseInt(getUserInput("input nights booked: "));

        if (numOfNights > 0 ){
            finish = false;
        }else{
            System.out.println("invalid input try again.");
        }   
    }while (finish);
        

    // procesing print to assure the user
        System.out.println("Processing Walk-in Check-In... Checking for available " + roomType + " rooms"+ "(Php "+bill +"/night)...");

    // check for available room and update status
        for (int rows = 0; rows < roomArray.length; rows++){ // for the rows ahh
            if (roomArray[rows][0] == null){ // check if the first column is available
                //counts available nights
                int availableNights = 0; //initializing the available nights
                for (int cols = 0; cols < roomArray[0].length; cols++){
                    if (roomArray[rows][cols] == null){
                        availableNights++;
                    }else{
                        break; //exit loop if occupied
                    }
                }
                
                //checks if nights book is less than or equal to available nights
                if (numOfNights <= availableNights){
                    //book the room for the number of nights
                    for (int cols = 0; cols < numOfNights; cols++){
                        roomArray[rows][cols] = "Occupied|" + guestName;
                        roomNumber = roomPrefix + (100 + rows + 1);
                    }
                    System.out.println("Found: " + roomNumber + ".");

                    //process payment
                    do{
                        payment = Integer.parseInt(getUserInput("Input Payment(Room Only, Php " + bill + " * " + numOfNights +" = " +bill*numOfNights + "): "));
                        if (payment >= bill * numOfNights){
                            System.out.println("""
                                               Payment Successful.
                                               --- Check-In Sccessful ---
                                               Update Status: Room """ + roomNumber + " is now set to 'Occupied' by " + guestName + ".");
                            System.out.println("Check-In Successful! Guest " + guestName +
                                                " is now occupying Room " + roomNumber + " room.");
                            return true;
                        }else{
                            System.out.println("Insufficient payment. Try again.");
                        }
                    }while (payment < bill * numOfNights);
                    break;
                }
            }
        }
        System.out.println("No available rooms for the requested nights.");
    return false;// just a placeholder to avoid errors
}

    private static boolean checkOut() {
        String room;
        String guestInput;
        String[] status;
        String realGuestName = "";

        do {
            room = getUserInput("Enter room number for checkout: ").toUpperCase().trim();
            // Validate room
            status = getCheckoutInfoArray(room);
            if (status == null) {
                System.out.println("Invalid room or no guest found. Try again.\n");
            } else {
                //Room is valid, now guest name
                realGuestName = status[0];
                guestInput = getUserInput("Enter guest name for verification: ").trim();

                if (!guestInput.equalsIgnoreCase(realGuestName)) {
                    System.out.println("Guest name does not match the room record. Try again.\n");
                    status = null; // ask again
                }
            }
            // Validate guest name (Case Insensitive)
        } while (status == null);

        // We passed validation then continue to billing
        int nights = Integer.parseInt(status[1]);
        double price = Double.parseDouble(status[2]);
        double subtotal = Double.parseDouble(status[3]);
        int roomIndex = Integer.parseInt(status[4]);
        String occupiedColString = status[5];

        double serviceFee = 250;
        double totalBeforeTax = subtotal + serviceFee;
        double tax = totalBeforeTax * 0.10;
        double total = totalBeforeTax + tax;

        System.out.println("─────────────────────────");
        System.out.println("Bill Calculation");
        System.out.println("Rate Per Night: \t₱" + price);
        System.out.println("Nights Stayed:\t\t" + nights);
        System.out.println("Subtotal: \t\t\t₱" + subtotal);
        System.out.println("Fixed Service Fee: \t₱" + serviceFee);
        System.out.println("Tax (10%): \t\t\t₱" + tax);
        System.out.println("TOTAL DUE: \t\t\t₱" + total);
        System.out.println("─────────────────────────");

        System.out.println(payment(room, realGuestName, total));

        clearRoomArray(room, roomIndex, occupiedColString);

        System.out.println("\nCheckout successful. Room " + room + " is now available.\n");
        return true;
    }

    private static String payment(String room, String guest, double bill) {
        double payment, change = 0;
        System.out.println("");

        do {
            System.out.print("Input Payment Amount: ");
            payment = Double.parseDouble(kbd.nextLine());
            if (payment<bill) {
                System.out.println("Amount input is less than balance.");
            }
        } while(payment < 0 || payment < bill);

        System.out.println("Payment: P"+payment+" received.");
        if(payment>bill) {
            change = payment-bill;
            System.out.println("Change Calculation: ₱" + payment + " - ₱" + bill + " = ₱" + change);
        }
        //final receipt
        System.out.println("Guest: " + guest + " | " + "Room: " + room);
        System.out.println("TOTAL AMOUNT DUE: ₱"+ bill);
        System.out.println("Amount Paid: ₱" + payment);
        if (payment>bill) {
            System.out.println("Change: ₱" + change);
        }

        return ""; // just a placeholder to avoid errors
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

    private static String[] getRoomTypeDetails(String[][] room) {
        // room type
        String roomType = room.length == 15 ? "Standard" : room.length == 10 ? "Deluxe" : "Suite";

        // total rooms
        int totalRooms = room.length;

        // available rooms
        int availableRooms = 0;
        for (String[] row : room) {
            for (String col : row) {
                if (col == null) {
                    availableRooms++;
                    break;
                }
            }
        }

        // room price per night
        int price = roomType.equals("Standard") ? 2500 : roomType.equals("Deluxe") ? 4000 : 8000;

        return new String[]{
                roomType,
                String.valueOf(totalRooms),
                String.valueOf(availableRooms),
                String.valueOf(price)
        };
    }

    private static String[][] getRoomArray(int input) {
        return switch (input) {
            case 1 -> standard;
            case 2 -> deluxe;
            case 3 -> suite;
            default -> new String[5][5];
        };
    }

    private static String getRoomPrefix(int input) {
        return switch (input) {
            case 1 -> "S";
            case 2 -> "D";
            case 3 -> "T";
            default -> "";
        };
    }

    private static void printTable(String[][] table, String roomType) {
        int rows = table.length;
        int cols = table[0].length;

        int cellWidth = 12;

        String divider = "├" + ("─".repeat(cellWidth) + "┼").repeat(cols) + ("─").repeat(cellWidth) + "┤";

        // column headers (dates)
        System.out.println("\n" + "┌" + ("─".repeat(cellWidth) + "┬").repeat(cols) + ("─".repeat(cellWidth) + "┐"));
        for (int i = -1; i < dates.length; i++) {
            if (i == -1) {
                System.out.print("│            ");
            } else {
                System.out.printf("│ %-10s ", dates[i]);
            }
        }
        System.out.println("│");
        System.out.println(divider);

        // table values
        for(int r = 0; r < rows; r++) {
            for(int c = -1; c < cols; c++) {
                if (c == -1) { // row header
                    String room = roomType + (100 + r + 1);
                    System.out.printf("│ %-10s ", room);
                } else { // row values
                    String val = table[r][c] == null ? "" : table[r][c].split("\\|")[0];
                    System.out.printf("│ %-10s ", val);
                }
            }
            System.out.println("│");

            if (r != rows - 1) {
                System.out.println(divider);
            }
        }
        System.out.println("└" + ("─".repeat(cellWidth) + "┴").repeat(cols) + ("─".repeat(cellWidth) + "┘")); // footer
    }
    private static String[] getCheckoutInfoArray(String roomNumber) {
        char prefix = roomNumber.charAt(0); //letter of room
        int row = Integer.parseInt(roomNumber.substring(1)) - 100 - 1; //converts number part to INDEX

        String[][] roomArray;
        int price;

        if (prefix == 'S') {
            roomArray = standard;
            price = 2500;
        } else {
            if (prefix == 'D') {
                roomArray = deluxe;
                price = 4000;
            } else {
                if (prefix == 'T') {
                    roomArray = suite;
                    price = 8000;
                } else {
                    return null;
                }
            }
        }

        String guestName = null;
        String occColumns = "";
        int nights = 0;

        //10 day loop
        for (int c = 0; c < 10; c++) {
            if (roomArray[row][c] == null) continue; //skip empty

            String[] parts = roomArray[row][c].split("\\|");
            if (parts.length < 2) continue;

            String status = parts[0];
            String name = parts[1];

            if (!status.equals("Occupied") && !status.equals("Booked")) continue;

            //guest stay determination
            if (guestName == null) {
                guestName = name; //save
                nights = 1;
                occColumns = String.valueOf(c);
            } else if (guestName.equals(name)) {
                nights++;
                occColumns += "," + c;
            }
        }

        if (guestName == null) return null;

        double subtotal = nights * price;

        return new String[]{
                guestName,                 // [0]
                String.valueOf(nights),    // [1]
                String.valueOf(price),     // [2]
                String.valueOf(subtotal),  // [3]
                String.valueOf(row),       // [4]
                occColumns                 // [5] this adds column list
        };
    }
    private static void clearRoomArray(String roomNumber, int row, String colList) {
        char prefix = roomNumber.charAt(0);
        String[][] roomArray;
        //room determination
        switch (prefix) {
            case 'S' -> roomArray = standard;
            case 'D' -> roomArray = deluxe;
            case 'T' -> roomArray = suite;
            default -> { return; }
        }

        String[] colIndexes = colList.split(",");

        for (String col : colIndexes) {
            int c = Integer.parseInt(col.trim());
            roomArray[row][c] = null;
        }
    }
}
