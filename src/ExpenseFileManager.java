import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Handles saving and loading expenses and the monthly budget
 * using CSV files.
 */
public class ExpenseFileManager {

    //Names of the files used to store expenses and the budget
    private static String file_name = "expenses.csv";
    private static String budget_file = "budget.csv";

    /**
     * Saves all expenses to the expenses CSV file.
     * Rewrites the file with the current list of expenses.
     *
     * @param expenses the list of expenses to save
     */
    public static void saveExpenses(ArrayList<Expense> expenses) {
        try {
            FileWriter writer = new FileWriter(file_name);

            //Write the column headers
            writer.write("Amount,Name,Category,Date\n");

            //Write each expense to the file
            for (Expense expense : expenses) {
                writer.write(expense.toCSV() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }

    /**
     * Loads previously saved expenses from the CSV file.
     *
     * @return an ArrayList containing all loaded expenses
     */
    public static ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_name));

            //Skip the column headers
            String line = reader.readLine();

            //Read each expense from the file
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                //Convert the saved information into the correct data types
                double amount = Double.parseDouble(data[0]);
                String name = data[1];
                String category = data[2];
                LocalDate date = LocalDate.parse(data[3]);

                //Create an Expense object and add it to the list
                Expense expense = new Expense(amount, name, category, date);
                expenses.add(expense);
            }

            reader.close();

        } catch (IOException e) {

        }

        return expenses;
    }

    /**
     * Saves the monthly budget to the budget CSV file.
     *
     * @param budget the monthly budget amount to save
     */
    public static void saveBudget(double budget) {
        try {
            FileWriter writer = new FileWriter(budget_file);

            //Write the budget header and amount
            writer.write("MonthlyBudget\n");
            writer.write(budget + "\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving budget.");
        }
    }

    /**
     * Loads the previously saved monthly budget.
     *
     * @return the saved budget, or 0 if no budget file exists
     */
    public static double loadBudget() {
        double budget = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(budget_file));

            //Skip the header
            reader.readLine();

            //Read the saved budget amount
            String line = reader.readLine();

            if (line != null) {
                budget = Double.parseDouble(line);
            }

            reader.close();

        } catch (IOException e) {

        }

        return budget;
    }
}
