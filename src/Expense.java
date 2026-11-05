import java.time.LocalDate;

public class Expense {
    private double amount;
    private String name;
    private String category;
    private LocalDate date;

    public Expense(double amount, String name, String category, LocalDate date) {
        this.amount = amount;
        this.name = name;
        this.category = category;
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String toCSV() {
        return amount + "," + name + "," + category + "," + date;
    }

    @Override
    public String toString() {
        return name + " | $" + amount + " | " + category + " | " + date;
    }
}
