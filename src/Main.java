
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Expense> expenses = new ArrayList<>();

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

            String choiceInput = input.nextLine();
            int choice = 0;

            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number from 1-5.");
                continue;
            }

            switch (choice) {
                case 1:
                    String name = "";

                    while (name.trim().isEmpty()) {
                        System.out.print("Enter expense name: ");
                        name = input.nextLine();

                        if (name.trim().isEmpty()) {
                            System.out.println("Name cannot be empty.");
                        }
                    }

                    double amount = 0;
                    boolean validAmount = false;

                    while (!validAmount) {
                        System.out.print("Enter amount: ");
                        String amountInput = input.nextLine();

                        try {
                            amount = Double.parseDouble(amountInput);

                            if (amount > 0) {
                                validAmount = true;
                            } else {
                                System.out.println("Amount must be greater than 0.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }

                    String category = "";

                    while (category.trim().isEmpty()) {
                        System.out.print("Enter category: ");
                        category = input.nextLine();

                        if (category.trim().isEmpty()) {
                            System.out.println("Category cannot be empty.");
                        }
                    }

                    LocalDate date = null;
                    boolean validDate = false;

                    while (!validDate) {
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String dateInput = input.nextLine();

                        try {
                            date = LocalDate.parse(dateInput);
                            validDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println(
                                    "Invalid date. Please use YYYY-MM-DD (example: 2026-11-24)."
                            );
                        }
                    }

                    Expense expense = new Expense(amount, name, category, date);
                    expenses.add(expense);

                    System.out.println("Expense added successfully!");
                    break;

                case 2:
                    System.out.println("\nYour Expenses:");

                    if (expenses.isEmpty()) {
                        System.out.println("No expenses recorded.");
                    } else {
                        for (Expense e : expenses) {
                            System.out.println(e);
                        }
                    }
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
