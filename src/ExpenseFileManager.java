
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ExpenseFileManager {

    private static String file_name = "expenses.csv";
    private static String budget_file = "budget.csv";

    public static void saveExpenses(ArrayList<Expense> expenses) {
        try {
            FileWriter writer = new FileWriter(file_name);

            writer.write("Amount,Name,Category,Date\n");

            for (Expense expense : expenses) {
                writer.write(expense.toCSV() + "\n");
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving expenses.");
        }
    }

    public static ArrayList<Expense> loadExpenses() {
        ArrayList<Expense> expenses = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file_name));

            String line = reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",", -1);

                double amount = Double.parseDouble(data[0]);
                String name = data[1];
                String category = data[2];
                LocalDate date = LocalDate.parse(data[3]);

                Expense expense = new Expense(amount, name, category, date);

                expenses.add(expense);
            }

            reader.close();

        } catch (IOException e) {

        }

        return expenses;
    }

    public static void saveBudget(double budget) {
        try {
            FileWriter writer = new FileWriter(budget_file);

            writer.write("MonthlyBudget\n");
            writer.write(budget + "\n");

            writer.close();

        } catch (IOException e) {
            System.out.println("Error saving budget.");
        }
    }

    public static double loadBudget() {
        double budget = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(budget_file));

            reader.readLine();

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