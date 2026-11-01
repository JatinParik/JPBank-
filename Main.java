import java.util.Scanner;

final class JPBank {
    public int accountNumber;
    private int pin;
    private int balance;

    JPBank(int accountNumber, int pin, int initialDeposit) throws Exception {
        if(accountNumber < 0) 
            throw new Exception("Enter a valid account number.");
        this.pin = pin;
        if(initialDeposit < 0) 
            throw new Exception("Please deposit a valid balance.");
        this.accountNumber = accountNumber;
        this.balance = initialDeposit;
    }

    public void resetPin(int oldPin, int newPin) throws Exception {
        if(oldPin != this.pin) 
            throw new Exception("Wrong PIN, can't reset the PIN.");
        this.pin = newPin;
    }

    public int withdraw(int pin, int amount) throws Exception {
        if(this.pin != pin) 
            throw new Exception("Enter a valid PIN");
        if(this.balance<amount) 
            throw new Exception("Insufficient Balance");
        this.balance -= amount;
        return this.balance;
    }

    public int deposit(int pin, int amount) throws Exception {
        if(this.pin != pin) 
            throw new Exception("Enter a valid PIN");
        this.balance += amount;
        return this.balance;
    }

    public int checkBalance(int pin) throws Exception {
        if(this.pin != pin) 
            throw new Exception("Enter a valid PIN");
        return this.balance;
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);

    private static void initialGreeting() {
        System.out.println("Welcome to JPBank. Your cash safety is our priority.");
    }

    private static void endGreeting() {
        System.out.println("Thankyou for Visiting!!");
    }

    private static int setAccountNumber() {
        System.out.print("Set your account number : ");
        return sc.nextInt();
    }

    private static int askForInitialDeposit() {
        System.out.print("Please enter your Initial Deposit : $");
        return sc.nextInt();
    }

    private static void askForInitialMenu(JPBank user) throws Exception {
        System.out.print("Press 1 to go back to initial menu(otherwise press any other number) : ");
        int input = sc.nextInt();
        if(input == 1) 
            initialMenu(user);
        else 
            endGreeting();
    }

    private static int setPin() {
        System.out.print("Set your PIN : ");
        return sc.nextInt();
    }

    private static int askForPin() {
        System.out.print("Enter your PIN : ");
        return sc.nextInt();
    }

    private static int askForAmount() {
        System.out.print("Enter amount : $");
        return sc.nextInt();
    }

    private static void initialMenu(JPBank user) throws Exception {
        System.out.println("Please enter an input : ");
        System.out.println("Press 1 to check your balance");
        System.out.println("Press 2 for amount deposit");
        System.out.println("Press 3 for cash withdrawl");
        System.out.println("Press any other number to Exit");

        int input = sc.nextInt();

        switch(input) {
            case 1 : checkBalance(user);
            break;
            case 2 : deposit(user);
            break;
            case 3 : withdraw(user);
            break;
            default : endGreeting();
        }
    }

    private static void checkBalance(JPBank user) throws Exception {
        int pin = askForPin();
        int currBalance = user.checkBalance(pin);
        System.out.println("Your current balance is " + currBalance);
        askForInitialMenu(user);
    }

    private static void deposit(JPBank user) throws Exception {
        int amount = askForAmount();
        int pin = askForPin();
        int updatedBalance = user.deposit(pin, amount);
        System.out.println("$" + amount + " successfully deposited to your account.");
        System.out.println("Updated Balance : $" + updatedBalance);
        askForInitialMenu(user);
    }

    private static void withdraw(JPBank user) throws Exception {
        int amount = askForAmount();
        int pin = askForPin();
        int updatedBalance = user.withdraw(pin, amount);
        System.out.println("Here is your money : $" + amount);
        System.out.println("Updated Balance : $" + updatedBalance);
        askForInitialMenu(user);
    }

    public static void main(String[] args) throws Exception {

        initialGreeting();
        int accountNumber = setAccountNumber();
        int pin = setPin();
        int initialDeposit = askForInitialDeposit();

        JPBank user = new JPBank(accountNumber, pin, initialDeposit);
        System.out.println("Congratulations, your account is successfully Created.");
        

        initialMenu(user);
        sc.close();
    }
}