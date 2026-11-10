import java.util.Scanner;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;

public class Main {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);

            boolean running = true;

            System.out.println("================================");
            System.out.println("     Welcome to Budget Tracker");
            System.out.println("================================");

            while (running) {
                System.out.println("\nSelect an Option:");
                System.out.println("1. Add Expense");
                System.out.println("2. View Expenses");
                System.out.println("3. Set Monthly Budget");
                System.out.println("4. View Remaining Budget");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");

                int choice = input.nextInt();
                input.nextLine();

                switch (choice) {
                    case 1:
                        break;

                    case 2:
                        break;

                    case 3:
                        break;

                    case 4:
                        break;

                    case 5:
                        running = false;
                        System.out.println("Thank you for using Budget Tracker!");
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

            input.close();
        }
    }