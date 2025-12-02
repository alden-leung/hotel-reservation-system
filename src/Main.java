/*
Project Name: Hotel Reservation System Simulation
Date: December 3, 2025, WS 8:30-10:00 AM (Laboratory) / 1:30-2:30 PM (Lecture)
Instructor: Janet V. Itliong

Group 3 Members:
1. Asuero, Luke Sebastian Lee
2. Gabriel, Erron John A.
3. Intendencia, Jared Wayne L.
4. Lardizabal, Eloise Althea
5. Leung, Alden
6. Rodriguez, Gabriel
7. Tumolva, Iana Klarize

Algorithm:

1. Main Menu

2.1 Display all room types (Standard, Deluxe, Suite) and prompt user to select one.
    • If the user selects the option 4 → go back to Main Menu.
2.2 Set the selected Room Type based on the user’s choice.
2.3 Retrieve the total number of rooms and the room status array for the chosen Room Type.
2.4 Count how many rooms are currently marked as:
        • "Available"
        • "Booked"
        • (Ignore rooms marked as "Occupied" for availability purposes)
2.5 Display the Room Availability Summary:
        • Room Type
        • Total number of rooms for that type
        • Number of Available rooms
        • Number of Booked rooms
        • Price per night
        • Table
2.6 Return to the Main Menu.
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
6.1 Prompt the user to input the payment amount.
6.2 If payment is insufficient, prompts the user to try again until valid amount.
6.3 Once inputs is valid, check if there's change due, if so, calculates and display the change.
6.4 Print a receipt with the guest name, room, total amount due, amount paid, and if applicable, the change.
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
                input = Integer.parseInt(getUserInput("Enter your choice: "));                  //Choice for Main Menu

                if (input < 1 || input > 5) {                                                   // Condition for Error Input of Choices
                    System.out.println("\nPlease choose an option from 1 to 5 only...");
                }
            } while (input < 1 || input > 5); //Condition to be Loop

            if (input == 5) {                                                                   // Exit Application
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
                input = Integer.parseInt(getUserInput("Enter your choice: "));  //With Method of Input

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

            if (input == 4) {
                System.out.println("Back to Main Menu...");
                return;
            }

            if (input < 1 || input > 4) {
                System.out.println("\nPlease choose an option from 1 to 4 only...");
            }
        } while (input < 1 || input > 4);

        String[][] roomArray = getRoomArray(input);

        String[] roomDetails = getRoomTypeDetails(roomArray);
        String roomPrefix = getRoomPrefix(input);
        printTable(roomArray, roomPrefix);

        //NAME VALIDATION (ADDED)

        boolean nameExists;
        do {
            guestName = getUserInput("Input Guest Name: ").trim();
            nameExists = false;

            // Check all rooms & dates for duplicate name
            for (int r = 0; r < roomArray.length; r++) {
                for (int c = 0; c < roomArray[0].length; c++) {
                    if (roomArray[r][c] != null) {
                        String[] parts = roomArray[r][c].split("\\|");
                        if (parts.length == 2) {
                            String storedName = parts[1];
                            if (storedName.equals(guestName)) { // case-sensitive
                                nameExists = true;
                                break;
                            }
                        }
                    }
                }
                if (nameExists) break;
            }

            if (nameExists) {
                System.out.println("Error: Guest name already exists. Please use another name.");
            }
            if (guestName.isEmpty()) {
                System.out.println("Guest name cannot be empty.");
                nameExists = true;
            }

        } while (nameExists);

         // NUMBER OF DAYS VALIDATION

        do {
            numOfDays = Integer.parseInt(getUserInput("Input Number of Days (1–10): "));
            if (numOfDays < 1 || numOfDays > 10) {
                System.out.println("Error: Number of days must be 1 to 10 only.");
            }
        } while (numOfDays < 1 || numOfDays > 10);

        pricePerNight = Integer.parseInt(roomDetails[3]);
        totalPrice = pricePerNight * numOfDays;

        //DATE VALIDATION (ADDED)

        String[] datesToReserve = new String[numOfDays];
        String validStart = "11/17/2025";
        String validEnd = "11/26/2025";

        for (int i = 1; i <= numOfDays; i++) {
            boolean validDate;
            String date;

            do {
                date = getUserInput("Date " + i + ": ").trim();
                validDate = false;

                // check if valid among the 10 allowed dates
                for (String d : dates) {
                    if (d.equals(date)) {
                        validDate = true;
                        break;
                    }
                }

                if (!validDate) {
                    System.out.println("Error: Invalid date. Allowed dates: 11/17/2025 to 11/26/2025.");
                }

            } while (!validDate);

            datesToReserve[i - 1] = date;
        }

        System.out.println("\nProcessing Reservation...");

        boolean successful = false;

        for (int r = 0; r < roomArray.length; r++) {
            boolean roomAvailable = true;

            for (String date : datesToReserve) {
                int c = getColIdx(date);

                if (roomArray[r][c] != null) {
                    roomAvailable = false;
                    break;
                }
            }

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
    //initialize variables
    String roomType = "";
    int bill = 0, input, numOfNights, payment;
    String guestName, roomNumber = "";

    // Room type menu
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
        // back to menu
        if (input == 4) {
            System.out.println("Back to Main Menu...");
            return false;
        }
        //validation for room type menu
        if (input < 1 || input > 4) {
            System.out.println("\nPlease choose an option from 1 to 4 only...");
        }
    } while (input < 1 || input > 4);

    // case for room type & bill
    switch (input) {
        case 1 -> { bill = 2500; roomType = "Standard"; }
        case 2 -> { bill = 4000; roomType = "Deluxe"; }
        case 3 -> { bill = 8000; roomType = "Suite"; }
    }
    // get room array and prefix of room
    String[][] roomArray = getRoomArray(input);
    String roomPrefix = getRoomPrefix(input);

    // Guest name input
    do {
        guestName = getUserInput("Input Guest Name: ").trim(); //input guest name
        if (guestName.isEmpty()) System.out.println("Guest name cannot be empty. Please try again."); // if input is empty try again ahh
    } while (guestName.isEmpty());

    // Number of nights
    do {
        numOfNights = Integer.parseInt(getUserInput("Input nights booked: ")); //input number of nights
        if (numOfNights <= 0 || numOfNights >= 10) System.out.println("Invalid input, try again."); // validation for number of nights
    } while (numOfNights <= 0 || numOfNights >= 10);

    System.out.println("Processing Walk-in Check-In... Checking for available " + roomType + " rooms (Php " + bill + "/night)...");

    // Search for available room
    for (int row = 0; row < roomArray.length; row++) { //iteration of the rows
        int availableNights = 0; // check available nights
        for (int col = 0; col < roomArray[0].length; col++) { //iteration of the columns
            if (roomArray[row][col] == null) availableNights++; //if null then available nights is +1
            else break;
        }

        if (numOfNights <= availableNights) { // if number of nights is less than or equal to available nights
            roomNumber = roomPrefix + (100 + row + 1); // generate room number
            System.out.println("Found: " + roomNumber);

            // Process payment
            do {
                payment = Integer.parseInt(getUserInput(
                        "Input Payment (Room Only, Php " + bill + " * " + numOfNights + " = " + bill*numOfNights + "): "
                ));
                if (payment < bill * numOfNights) {
                    System.out.println("Insufficient payment. Try again.");
                }
            } while (payment < bill * numOfNights);

            // Book the room & store payment
            for (int col = 0; col < numOfNights; col++) { // iterate through number of nights
                roomArray[row][col] = "Occupied|" + guestName + "|" + payment;
            }

            System.out.println("""
                    Payment Successful.
                    --- Check-In Successful ---
                    Update Status: Room """ + roomNumber + " is now set to 'Occupied' by " + guestName + ".");

            return true;
        }
    }

    System.out.println("No available rooms for the requested nights.");
    return false;
}


    private static boolean checkOut() {
        //initialize variables
    String room, guestInput;
    String[] status;
    String realGuestName = "";
        // room & name validation

    do {
        room = getUserInput("Enter room number for checkout: ").toUpperCase().trim();
        status = getCheckoutInfoArray(room);

        if (status == null) {
            System.out.println("Invalid room or no guest found. Try again.\n");
            continue;
        }

        realGuestName = status[0];
        guestInput = getUserInput("Enter guest name for verification: ").trim();

        if (!guestInput.equalsIgnoreCase(realGuestName)) {
            System.out.println("Guest name does not match the room record. Try again.\n");
            status = null;
        }
    } while (status == null);
    
    //extracting values from status array
    int nights = Integer.parseInt(status[1]);
    double price = Double.parseDouble(status[2]);
    double subtotal = Double.parseDouble(status[3]);
    int roomIndex = Integer.parseInt(status[4]);
    String occupiedColString = status[5];
    double paidAmount = Double.parseDouble(status[6]);

    //calculate total 
    double serviceFee = 250;
    double totalBeforeTax = subtotal + serviceFee;
    double tax = totalBeforeTax * 0.10; // add 10% tax
    double total = totalBeforeTax + tax; // subtract payment made at check-in

    // Deduct payment made at check-in
    total -= paidAmount;
    if (total < 0) total = 0;

    System.out.println("─────────────────────────");
    System.out.println("Bill Calculation");
    System.out.println("Rate Per Night: \t₱" + price);
    System.out.println("Nights Stayed:\t\t" + nights);
    System.out.println("Subtotal: \t\t\t₱" + subtotal);
    System.out.println("Fixed Service Fee: \t₱" + serviceFee);
    System.out.println("Tax (10%): \t\t\t₱" + tax);
    System.out.println("Total Due: \t\t\t₱" + total);
    System.out.println("─────────────────────────");

    payment(room, realGuestName, total);
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
                System.out.println("Insufficient amount. Try again.");
            }
        } while(payment < 0 || payment < bill);

        System.out.println("Payment: ₱"+payment+" received.");
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
        System.out.print(message);
        return kbd.nextLine();
    }

    private static int getColIdx(String date) {
        int day = Integer.parseInt(date.split("/")[1].trim());
        return day - 17;
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
    char prefix = roomNumber.charAt(0);
    int row = Integer.parseInt(roomNumber.substring(1)) - 100 - 1;

    String[][] roomArray;
    int price;

    if (prefix == 'S') { roomArray = standard; price = 2500; }
    else if (prefix == 'D') { roomArray = deluxe; price = 4000; }
    else if (prefix == 'T') { roomArray = suite; price = 8000; }
    else return null;

    String guestName = null;
    String occColumns = "";
    int nights = 0;
    double paidAmount = 0;

    for (int c = 0; c < 10; c++) {
        if (roomArray[row][c] == null) continue;

        String[] parts = roomArray[row][c].split("\\|");    //checks occupied or booked | name
        if (parts.length < 2) continue;

        String status = parts[0];
        String name = parts[1];

        if (!status.equals("Occupied") && !status.equals("Booked")) continue;

        if (guestName == null) {
            guestName = name;
            nights = 1;
            occColumns = String.valueOf(c);
            if (parts.length >= 3) paidAmount = Double.parseDouble(parts[2]);
        } else if (guestName.equals(name)) {
            nights++;
            occColumns += "," + c;
        }
    }
    if (guestName == null) return null;
    double subtotal = nights * price;
    return new String[]{
            guestName,                // [0]
            String.valueOf(nights),   // [1]
            String.valueOf(price),    // [2]
            String.valueOf(subtotal), // [3]
            String.valueOf(row),      // [4]
            occColumns,               // [5]
            String.valueOf(paidAmount) // [6] payment already made
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
