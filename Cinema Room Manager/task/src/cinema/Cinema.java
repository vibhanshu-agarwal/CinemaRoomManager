package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {
        // Write your code here
        System.out.println("Enter the number of rows:");
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        scanner = new Scanner(System.in);
        int seatsPerRow = scanner.nextInt();

        //Build the seating map
        String[][] seatingMap = buildSeatingMap(rows,
                seatsPerRow);

        int menuChoice = 0;
        do {
            //Print Menu
            System.out.println();
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            scanner = new Scanner(System.in);
            menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    printSeatingMap(rows,
                            seatsPerRow,
                            seatingMap);
                    break;
                case 2:
                    buyTicket(rows,
                            seatsPerRow,
                            seatingMap);
                    break;
                case 3:
                    printStatistics(rows,
                            seatsPerRow,
                            seatingMap);
                    break;
                case 0:
                    break;
            }
        } while (menuChoice != 0);

    }

    private static void printStatistics(int rows, int seatsPerRow, String[][] seatingMap) {
        //1. Number of purchased tickets
        int purchasedTickets = 0;
        int totalSeats = rows * seatsPerRow;
        int currentIncome = 0;
        int totalIncome = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                int currTicketPrice = getTicketPrice(rows,
                        i + 1,
                        totalSeats);
                if (seatingMap[i][j].equals("B")) {
                    purchasedTickets++;
                    currentIncome += currTicketPrice;
                }
                totalIncome += currTicketPrice;
            }
        }

        System.out.printf("Number of purchased tickets: %d ",
                purchasedTickets);
        System.out.println();
        System.out.printf("Percentage: %.2f%c",
                (double) purchasedTickets / totalSeats * 100,
                '%');
        System.out.println();
        System.out.printf("Current income: %c%d",
                '$',
                currentIncome);
        System.out.println();
        System.out.printf("Total income: %c%d",
                '$',
                totalIncome);
        System.out.println();

    }

    private static void buyTicket(int rows, int seatsPerRow, String[][] seatingMap) {
        System.out.println("Enter a row number:");
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        System.out.println("Enter a seat number in that row:");
        scanner = new Scanner(System.in);
        int seat = scanner.nextInt();
        //Disallow coordinates that are out of bounds
        if (row > rows || seat > seatsPerRow || row < 1 || seat < 1) {
            System.out.println("Wrong input!");
            buyTicket(rows,
                    seatsPerRow,
                    seatingMap);
        }  //Since arrays are 0 based we need to subtract -1 from both row and column
        else if (seatingMap[row - 1][seat - 1].equals("B")) {
            System.out.println("That ticket has already been purchased!");
            buyTicket(rows,
                    seatsPerRow,
                    seatingMap);
        } else {
            int ticketPrice = getTicketPrice(rows,
                    row,
                    rows * seatsPerRow);
            System.out.printf("Ticket price: $%d",
                    ticketPrice);
            System.out.println();
            seatingMap[row - 1][seat - 1] = "B";
        }
    }

    private static int getTicketPrice(int rows, int row, int totalSeats) {
        int ticketPrice = 0;
        if (totalSeats <= 60) {
            ticketPrice = 10;
        } else {
            int frontRows = rows / 2;
            int frontRowsTicketPrice = 10;
            int backRows = rows / 2 + rows % 2;
            int backRowsTicketPrice = 8;
            if (row <= frontRows) {
                ticketPrice = frontRowsTicketPrice;
            } else {
                ticketPrice = backRowsTicketPrice;
            }
        }
        return ticketPrice;
    }

    private static String[][] buildSeatingMap(int rows, int seatsPerRow) {
        String[][] seatingMap = new String[rows][seatsPerRow];
        //Populate seatingMap array with "S" for seat
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seatsPerRow; j++) {
                seatingMap[i][j] = "S";
            }
        }
        return seatingMap;
    }

    private static void printSeatingMap(int rows, int seatsPerRow, String[][] seatingMap) {
        System.out.println("Cinema:");
        //Print seatingMap header
        for (int i = 0; i <= seatsPerRow; i++) {
            if (i == 0) {
                System.out.print(" ");
            } else {
                System.out.print(" " + i);
            }
        }
        System.out.println();

        //Print seatingMap array
        for (int i = 0; i < rows; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < seatsPerRow; j++) {
                System.out.print(seatingMap[i][j] + " ");
            }
            System.out.println();
        }
    }
}