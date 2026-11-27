# Personal Budgeting App

A Java command-line budgeting application for recording expenses, managing a monthly budget, and reviewing spending by month and category.

## Project Goal

The goal of this project is to create a free personal budgeting app that helps users keep track of their spending without requiring a paid subscription. Many budgeting apps put useful features behind subscriptions, so this project aims to provide a free alternative for users to manage their expenses and monthly budgets.

## Tech Stack

- **Language:** Java
- **Libraries / APIs:** Java standard library only:
  - `java.util.Scanner` for command-line input
  - `java.util.ArrayList`, `HashMap`, and `Map` for storing and organizing expenses
  - `java.time.LocalDate`, `YearMonth`, and `DateTimeParseException` for dates and month-based calculations
  - `java.io.FileReader`, `FileWriter`, `BufferedReader`, and `IOException` for file persistence
- **External tools:** Git, GitHub, and IntelliJ IDEA
- **Data storage:** Local CSV files (`expenses.csv` and `budget.csv`)

## Setup and Run

### Prerequisites

Install a Java Development Kit version 8 or newer. Verify that both the Java compiler and runtime are available:

```bash
java -version
javac -version
```

### Clone the repository

```bash
git clone https://github.com/GROOKEY257/BudgetTracker.git
cd BudgetTracker
```

### Compile

From the repository root, run:

```bash
javac -d out src/*.java
```

### Run

```bash
java -cp out Main
```

The program creates `expenses.csv` and `budget.csv` in the current working directory when data is saved. Keep these files if you want your data to remain available the next time you run the program. They are local data files and are not included automatically in a fresh clone.

## Example Run

The following is an illustrative session. The values shown are example input, not preloaded application data.

```text
================================
     Welcome to Budget Tracker
================================

Select an Option:
1. Add Expense
2. View Expenses
3. Set Monthly Budget
4. View Remaining Budget
5. Monthly Spending Summary
6. Category Breakdown
7. Edit Existing Expense
8. Exit
Enter your choice: 1
Enter expense name: Groceries
Enter amount: 42.50
Enter category: Food
Enter date (YYYY-MM-DD): 2026-11-20
Expense added successfully!

Enter your choice: 3
Enter your monthly budget: 500
Monthly budget saved successfully!

Enter your choice: 4

Monthly Budget: $500.00
Total Expenses: $42.50
Remaining Budget: $457.50
```

For the example above, the expense is saved in `expenses.csv` in a row like:

```csv
Amount,Name,Category,Date
42.5,Groceries,Food,2026-11-20
```

The monthly budget is saved in `budget.csv`:

```csv
MonthlyBudget
500.0
```

To see all menu options, start the program and select options 1–8. For monthly summaries and category breakdowns, enter a month in `YYYY-MM` format (for example, `2026-11`).

## Roadmap

- [x] Create the `Expense` class to represent expense amount, name, category, and date.
- [x] Build a menu-driven command-line interface.
- [x] Add input validation for expense names, positive amounts, categories, dates, and menu selections.
- [x] Add new expenses and save them to `expenses.csv`.
- [x] Load previously saved expenses when the program starts.
- [x] Set a monthly budget and save it to `budget.csv`.
- [x] Calculate and display the current month's remaining budget.
- [x] View all recorded expenses.
- [x] Generate a spending total for a selected month.
- [x] Display spending totals grouped by category for a selected month.
- [x] Edit an existing expense and save the changes.
