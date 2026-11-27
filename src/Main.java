
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Main class for the Budget Tracker application.
 * Allows users to add, view, edit, and compare expenses, as well as manage their monthly budget.
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //Load previously saved expenses and monthly budget
        ArrayList<Expense> expenses = ExpenseFileManager.loadExpenses();
        double monthlyBudget = ExpenseFileManager.loadBudget();

        boolean running = true;

        //Display the welcome message
        System.out.println("================================");
        System.out.println("     Welcome to Budget Tracker");
        System.out.println("================================");

        //Continue displaying the menu until the user chooses to exit
        while (running) {
            System.out.println("\nSelect an Option:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Set Monthly Budget");
            System.out.println("4. View Remaining Budget");
            System.out.println("5. Monthly Spending Summary");
            System.out.println("6. Category Breakdown");
            System.out.println("7. Edit Existing Expense");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            //Get and validate the user's menu selection
            String choiceInput = input.nextLine();
            int choice = 0;

            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number from 1-8.");
                continue;
            }

            switch (choice) {

                //Add a new expense
                case 1:
                    String name = "";

                    //Make sure the expense name is not empty
                    while (name.trim().isEmpty()) {
                        System.out.print("Enter expense name: ");
                        name = input.nextLine();

                        if (name.trim().isEmpty()) {
                            System.out.println("Name cannot be empty.");
                        }
                    }

                    double amount = 0;
                    boolean validAmount = false;

                    //Get a positive amount with no more than two decimal places
                    while (!validAmount) {
                        System.out.print("Enter amount: ");
                        String amountInput = input.nextLine();

                        try {
                            amount = Double.parseDouble(amountInput);

                            if (amount > 0 && amountInput.matches("\\d+(\\.\\d{1,2})?")) {
                                validAmount = true;
                            } else {
                                System.out.println("Enter a positive amount with up to 2 decimal places.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }

                    String category = "";

                    //Make sure the expense category is not empty
                    while (category.trim().isEmpty()) {
                        System.out.print("Enter category: ");
                        category = input.nextLine();

                        if (category.trim().isEmpty()) {
                            System.out.println("Category cannot be empty.");
                        }
                    }

                    LocalDate date = null;
                    boolean validDate = false;

                    //Get a valid date in YYYY-MM-DD format
                    while (!validDate) {
                        System.out.print("Enter date (YYYY-MM-DD): ");
                        String dateInput = input.nextLine();

                        try {
                            date = LocalDate.parse(dateInput);
                            validDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Invalid date. Use YYYY-MM-DD.");
                        }
                    }

                    //Create the expense, add it to the list, and save the updated list
                    Expense expense = new Expense(amount, name, category, date);
                    expenses.add(expense);
                    ExpenseFileManager.saveExpenses(expenses);

                    System.out.println("Expense added successfully!");
                    break;

                //Display all recorded expenses
                case 2:
                    System.out.println("\nYour Expenses:");

                    if (expenses.isEmpty()) {
                        System.out.println("No expenses recorded.");
                    } else {
                        //Display each expense with its list number
                        for (int i = 0; i < expenses.size(); i++) {
                            System.out.println((i + 1) + ". " + expenses.get(i));
                        }
                    }
                    break;

                //Set and save the monthly budget
                case 3:
                    double newBudget = 0;
                    boolean validBudget = false;

                    //Make sure the budget is positive and has at most two decimal places
                    while (!validBudget) {
                        System.out.print("Enter your monthly budget: ");
                        String budgetInput = input.nextLine();

                        try {
                            newBudget = Double.parseDouble(budgetInput);

                            if (newBudget > 0 && budgetInput.matches("\\d+(\\.\\d{1,2})?")) {
                                validBudget = true;
                            } else {
                                System.out.println("Enter a positive budget with up to 2 decimal places.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Please enter a valid number.");
                        }
                    }

                    //Update the budget and save it to the file
                    monthlyBudget = newBudget;
                    ExpenseFileManager.saveBudget(monthlyBudget);

                    System.out.println("Monthly budget saved successfully!");
                    break;

                //Display the current month's remaining budget
                case 4:
                    //Calculate the total expenses for the current month
                    double totalExpenses = getMonthlyTotal(expenses, YearMonth.now());

                    if (monthlyBudget == 0) {
                        System.out.println("You haven't set a monthly budget yet.");
                    } else {
                        System.out.printf("\nMonthly Budget: $%.2f%n", monthlyBudget);
                        System.out.printf("Total Expenses: $%.2f%n", totalExpenses);
                        System.out.printf("Remaining Budget: $%.2f%n", monthlyBudget - totalExpenses);
                    }
                    break;

                //Display a spending summary for a selected month
                case 5:
                    YearMonth summaryMonth = getMonth(input);

                    if (summaryMonth != null) {
                        double summaryTotal = getMonthlyTotal(expenses, summaryMonth);

                        System.out.println("\nMonthly Spending Summary: " + summaryMonth);
                        System.out.printf("Total Spent: $%.2f%n", summaryTotal);

                        //Display the budget and remaining amount if a budget has been set
                        if (monthlyBudget > 0) {
                            System.out.printf("Monthly Budget: $%.2f%n", monthlyBudget);
                            System.out.printf("Remaining: $%.2f%n", monthlyBudget - summaryTotal);
                        }
                    }
                    break;

                //Show how much was spent in each category
                case 6:
                    YearMonth categoryMonth = getMonth(input);

                    if (categoryMonth != null) {
                        showCategoryBreakdown(expenses, categoryMonth);
                    }
                    break;

                //Allow the user to edit an existing expense
                case 7:
                    editExpense(input, expenses);
                    break;

                //Exit the application
                case 8:
                    running = false;
                    System.out.println("Thank you for using Budget Tracker!");
                    break;

                //Handle menu selections that are not between 1 and 8
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }

        //Close the Scanner when the program ends
        input.close();
    }

    /**
     * Gets a month from the user in YYYY-MM format.
     *
     * @param input Scanner used to read the user's input
     * @return the selected YearMonth, or null if the input is invalid
     */
    public static YearMonth getMonth(Scanner input) {
        System.out.print("Enter month (YYYY-MM): ");
        String monthInput = input.nextLine();

        try {
            return YearMonth.parse(monthInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid month. Please use YYYY-MM (example: 2026-11).");
            return null;
        }
    }

    /**
     * Calculates the total amount spent during a specific month.
     *
     * @param expenses the list of recorded expenses
     * @param month the month to calculate expenses for
     * @return the total amount spent during that month
     */
    public static double getMonthlyTotal(ArrayList<Expense> expenses, YearMonth month) {
        double total = 0;

        //Add the amount of each expense that belongs to the selected month
        for (Expense e : expenses) {
            if (YearMonth.from(e.getDate()).equals(month)) {
                total += e.getAmount();
            }
        }

        return total;
    }

    /**
     * Displays the total amount spent in each category during a selected month.
     *
     * @param expenses the list of recorded expenses
     * @param month the month to display the category breakdown for
     */
    public static void showCategoryBreakdown(ArrayList<Expense> expenses, YearMonth month) {
        //Store each category and its total spending in a HashMap
        HashMap<String, Double> categories = new HashMap<>();

        //Go through the expenses and group their amounts by category
        for (Expense e : expenses) {
            if (YearMonth.from(e.getDate()).equals(month)) {
                String category = e.getCategory();

                //Add to the category's existing total or create a new entry
                if (categories.containsKey(category)) {
                    categories.put(category, categories.get(category) + e.getAmount());
                } else {
                    categories.put(category, e.getAmount());
                }
            }
        }

        System.out.println("\nCategory Breakdown for " + month + ":");

        if (categories.isEmpty()) {
            System.out.println("No expenses recorded for this month.");
        } else {
            //Display the total spending for each category
            for (Map.Entry<String, Double> entry : categories.entrySet()) {
                System.out.printf("%s: $%.2f%n", entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * Allows the user to select and edit an existing expense.
     * The user can update the name, amount, category, and date.
     *
     * @param input Scanner used to read the user's input
     * @param expenses the list of expenses that can be edited
     */
    public static void editExpense(Scanner input, ArrayList<Expense> expenses) {
        //Check whether there are any expenses to edit
        if (expenses.isEmpty()) {
            System.out.println("There are no expenses to edit.");
            return;
        }

        //Display all expenses with their corresponding numbers
        System.out.println("\nYour Expenses:");

        for (int i = 0; i < expenses.size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(i));
        }

        //Ask the user which expense they want to edit
        System.out.print("Enter the number of the expense to edit: ");
        String selection = input.nextLine();

        int expenseNumber;

        //Convert the selection into an integer
        try {
            expenseNumber = Integer.parseInt(selection);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }

        //Make sure the selected number matches an existing expense
        if (expenseNumber < 1 || expenseNumber > expenses.size()) {
            System.out.println("Invalid expense number.");
            return;
        }

        //Get the selected expense from the ArrayList
        Expense selectedExpense = expenses.get(expenseNumber - 1);

        System.out.println("Press Enter to keep the current value.");

        //Get a new name if the user wants to change it
        System.out.print("Enter new name (" + selectedExpense.getName() + "): ");
        String newName = input.nextLine();

        if (!newName.trim().isEmpty()) {
            selectedExpense.setName(newName);
        }

        //Get a new amount if the user wants to change it
        System.out.print("Enter new amount (" + selectedExpense.getAmount() + "): ");
        String newAmount = input.nextLine();

        if (!newAmount.trim().isEmpty()) {
            try {
                double amount = Double.parseDouble(newAmount);

                //Validate the new amount before updating the expense
                if (amount > 0 && newAmount.matches("\\d+(\\.\\d{1,2})?")) {
                    selectedExpense.setAmount(amount);
                } else {
                    System.out.println("Invalid amount. Keeping the old amount.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid amount. Keeping the old amount.");
            }
        }

        //Get a new category if the user wants to change it
        System.out.print("Enter new category (" + selectedExpense.getCategory() + "): ");
        String newCategory = input.nextLine();

        if (!newCategory.trim().isEmpty()) {
            selectedExpense.setCategory(newCategory);
        }

        //Get a new date if the user wants to change it
        System.out.print("Enter new date (" + selectedExpense.getDate() + "): ");
        String newDate = input.nextLine();

        if (!newDate.trim().isEmpty()) {
            try {
                LocalDate date = LocalDate.parse(newDate);
                selectedExpense.setDate(date);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date. Keeping the old date.");
            }
        }

        //Save the updated expense list to the CSV file
        ExpenseFileManager.saveExpenses(expenses);

        System.out.println("Expense updated successfully!");
    }
}