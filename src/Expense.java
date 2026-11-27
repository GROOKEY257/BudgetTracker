import java.time.LocalDate;

/**
 * Represents an individual expense with an amount, name, category, and date.
 */
public class Expense {
    private double amount;
    private String name;
    private String category;
    private LocalDate date;

    /**
     * Creates a new expense with the given information.
     *
     * @param amount the amount of money spent
     * @param name the name or description of the expense
     * @param category the category of the expense
     * @param date the date the expense occurred
     */
    public Expense(double amount, String name, String category, LocalDate date) {
        this.amount = amount;
        this.name = name;
        this.category = category;
        this.date = date;
    }

    /**
     * Gets the amount of the expense.
     *
     * @return the expense amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Changes the amount of the expense.
     *
     * @param amount the new expense amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Gets the name of the expense.
     *
     * @return the expense name
     */
    public String getName() {
        return name;
    }

    /**
     * Changes the name of the expense.
     *
     * @param name the new expense name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the category of the expense.
     *
     * @return the expense category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Changes the category of the expense.
     *
     * @param category the new expense category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the date of the expense.
     *
     * @return the expense date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Changes the date of the expense.
     *
     * @param date the new expense date
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }

    /**
     * Converts the expense information into a comma-separated string
     * so it can be saved in a CSV file.
     *
     * @return the expense information in CSV format
     */
    public String toCSV() {
        return amount + "," + name + "," + category + "," + date;
    }

    /**
     * Creates a readable representation of the expense.
     *
     * @return the expense details formatted as a string
     */
    @Override
    public String toString() {
        return String.format("%s | $%.2f | %s | %s", name, amount, category, date);
    }
}