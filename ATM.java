import java.util.Scanner;

class BankAccount 
{
    private Double balance; 

    BankAccount(Double balance) 
    {
        this.balance = balance;
    }

    void withdraw(double amount) 
    {
        if (amount <= 0) 
        {
            System.out.println("Amount must be greater than 0. Transaction failed.");
            return;
        }
        if (amount > balance) 
        {
            System.out.println("Insufficient Balance. Transaction failed.");
            return;
        }
        balance -= amount;
        System.out.println("Withdrawal Successful! New Balance: " + balance);
    }

    void deposit(double amount) 
    {
        if (amount <= 0) 
        {
            System.out.println("Amount must be greater than 0. Transaction failed.");
            return;
        }
        balance += amount;
        System.out.println("Deposit Successful! New Balance: " + balance);
    }

    double getBalance() 
    {
        return balance;
    }
}


public class ATM 
{
    public static void main(String[] args) 
    {
        Scanner scanner = new Scanner(System.in);
        BankAccount bankAccount = new BankAccount(1000.0);

        System.out.println("Welcome to the ATM");
        System.out.println("1. Withdraw\n2. Deposit\n3. Check Balance\n4. Exit");

        while (true) 
        {
            System.out.println("Enter your choice: ");
            int choice;
            try {
                choice = scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
                scanner.next();
                continue;
            }

            switch (choice) 
            {
                case 1:
                    double withdrawAmount = readAmount(scanner, "Enter the amount to withdraw: ");
                    bankAccount.withdraw(withdrawAmount);
                    break;
                case 2:
                    double depositAmount = readAmount(scanner, "Enter the amount to deposit: ");
                    bankAccount.deposit(depositAmount);
                    break;
                case 3:
                    System.out.println("Current Balance: " + bankAccount.getBalance());
                    break;
                case 4:
                    System.out.println("Thank you for using our banking system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static double readAmount(Scanner scanner, String message) 
    {
        System.out.println(message);
        double amount;
        while (true) 
        {
            try {
                amount = scanner.nextDouble();
                return amount;
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid amount.");
                scanner.next();
            }
        }
    }
}
