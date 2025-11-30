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
        int bill = 03, input, numOfNights, payment;
       
        String guestName = "", roomNumber = "";
        
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

            switch (input) {
                case 1 -> {checkRoomStatus(standard);
                            bill = 2500; 
                            roomType = "Standard";
                        }
                case 2 -> {checkRoomStatus(deluxe); 
                            bill = 4000;
                            roomType = "Deluxe";
                        }
                case 3 -> {checkRoomStatus(suite);
                            bill = 8000;
                            roomType = "Suite";
                        }
            }
    
    // room details
        String[][] roomArray = getRoomArray(input);
        String[] info = getRoomTypeDetails(roomArray);
        String roomPrefix = getRoomPrefix(input);
    // guest name 
        guestName = getUserInput("Input Guest Name: ");
        
        
    // number of nights
        numOfNights = Integer.parseInt(getUserInput("input nights booked:"));

        System.out.println("Processing Walk-in Check-In... Checking for available " + roomType + " rooms"+ "(Php "+bill +"/night)...");
    // check for available room and update status
        for (int rows = 0; rows < roomArray.length; rows++){ // for the rows ahh
            if (roomArray[rows][0] == null){ // check if the first column is available
                //counts available nights
                int availableNights = 0;
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
                        payment = Integer.parseInt(getUserInput("Input Payment(Room Only, Php " + bill + " * " + numOfNights +"): "));
                        if (payment >= bill * numOfNights){
                            System.out.println("Payment Successful." +
                                        "\n--- Check-In Sccessful ---" +
                                        "\nUpdate Status: Room " + roomNumber + " is now set to 'Occupied' by " + guestName + ".");
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
            System.out.println("No available rooms for the requested nights.");
        }
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
}
