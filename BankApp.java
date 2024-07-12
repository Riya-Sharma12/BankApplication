package Classes;

import javax.sound.midi.InvalidMidiDataException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class BankApp {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<String> BasicDetails = new ArrayList<>();
    static ArrayList<ArrayList<String>> Accounts = new ArrayList<>();
    static int cnt = 0;
    static int userId = 0;
    static ArrayList<String> helper = new ArrayList<>();
    static String id;
    static ArrayList<String> check = new ArrayList<String>();
    static int updateOption;
    static int useOption;
    static int TransferAccountId;
    static int TransferAccountIndex;
    static int amount;
    static int convertedAmount;
    static ArrayList<String> checkAccount = new ArrayList<String>();
    static ArrayList<String> delArr = new ArrayList<>();
    static ArrayList<String> password = new ArrayList<>();
    static int passCount = 0;
    static String passwordChecking;
    private static ArrayList<Integer> delAccountList = new ArrayList<>();
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    static int TransferredAmnt = 0;
    static int WithdrawnAmnt = 0;
    static int DepositedAmnt = 0;
    static int TotalAmnt = 0;
    static ArrayList<ArrayList<Integer>> transactionDetails = new ArrayList<>();
    static int c = 0;
    static ArrayList<Integer> help = new ArrayList<>();
    static ArrayList<Integer> h = new ArrayList<>();

    public static void main(String[] args) throws InvalidMidiDataException {
        System.out.println("Welcome \uD83D\uDC4B to the Bank Application");
        System.out.println("RSM Bank \uD83C\uDFE6 aapka swagat krta h");
        System.out.println("What do you want??");
        int choice;

        do {
            System.out.println("Please choose an Option");
            System.out.println("1.Create Account ✎");
            System.out.println("2.Use Account \uD83D\uDCDC ");
            System.out.println("3.Update Account ✅");
            System.out.println("4.Delete Account \uD83D\uDDD1\uFE0F");
            System.out.println("5.Exit ❌");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("Welcome! Let's create your account-");
                    Accounts.add(cnt, CreateAccount());
                    System.out.println(Accounts);
                    break;
                case 2:
                    System.out.println("Account usage");
                    useAccount(Accounts);
                    System.out.println(Accounts);
                    break;
                case 3:
                    System.out.println("Account Updation");
                    updateAccount(Accounts);
                    System.out.println(Accounts);
                    break;
                case 4:
                    deleteAccount(Accounts);
                    break;
                case 5:
                    System.out.println("Thank You for visiting our Application. Looking forward to your valuable feedback");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid choice");
            }
        } while (choice >= 0);
    }

    public static ArrayList<String> CreateAccount() {
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter password:");
        String pass = sc.nextLine();
        System.out.print("Enter Balance: ");
        String IniBal = sc.nextLine();
        BasicDetails.add(name);
        password.add(passCount, pass);
        BasicDetails.add(IniBal);
        userId++;
        id = String.valueOf(userId);
        System.out.println(" Hello " + name + " You have been assigned a Account Id" + " " + id);
        BasicDetails.add(id);
        helper = (ArrayList) BasicDetails.clone();
        BasicDetails.clear();
        ++cnt;
        ++passCount;
        return helper;
    }


    public static void deleteAccount(ArrayList<ArrayList<String>> Accounts) {
        System.out.println(" To Delete your account, we need to verify your details");
        System.out.println("Enter the Account Id assigned to you");
        int index = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your password");
        String checkPassword = sc.nextLine();
        if (index <= 0) {
            System.out.println("Invalid UserId. Please enter a valid UserId.");
        } else {
            check = Accounts.get(index - 1);
            passwordChecking = password.get(index - 1);
            if (passwordChecking.equals(checkPassword)) {
                Accounts.set(index - 1, delArr);
                delAccountList.add(index);
                System.out.println("Account successfully deleted.");
            } else {
                System.out.println("Invalid Password. Can't delete your account.");
            }
        }
    }

    public static void updateAccount(ArrayList<ArrayList<String>> Accounts) throws InvalidMidiDataException {
        System.out.println(" To Update your account, we need to verify your details");
        System.out.println("Enter the  Account Id assigned to you");
        int index = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your password");
        String checkPassword = sc.nextLine();
        if (index <= 0) {
            System.out.println("Invalid UserId. Please enter a valid UserId.");
        } else if (delAccountList.contains(index)) {
            throw new InvalidMidiDataException("Can not use this account");
        } else {
            check = Accounts.get(index - 1);
            passwordChecking = password.get(index - 1);
            if (passwordChecking.equals(checkPassword)) {
                System.out.println(check);
                do {
                    System.out.println("What do you want to update?");
                    System.out.println("1.Name of the holder");
                    System.out.println("2.Password");
                    System.out.println("Press -1 to exit");
                    updateOption = sc.nextInt();
                    switch (updateOption) {
                        case 1:
                            System.out.println("we will shortly inform your changed Name");
                            System.out.println("Your current Name is-");
                            System.out.println(check.get(0));
                            sc.nextLine();
                            System.out.println("Enter your new Name-");
                            String newUpdatedName = sc.nextLine();
                            check.set(0, newUpdatedName);
                            System.out.println("Your updated account details are as follows");
                            System.out.println(check);
                            break;
                        case 2:
                            System.out.println("we will shortly update your password");
                            System.out.println("Enter your new password-");
                            String newUpdatedPassword = sc.next();
                            password.set(index - 1, newUpdatedPassword);
                            System.out.println("We have successfully updated your password");
                            break;
                    }
                } while (updateOption != -1);
            } else {
                System.out.println("Wrong Password , can't update. ");
            }
        }
    }


    public static void useAccount(ArrayList<ArrayList<String>> Accounts) throws InvalidMidiDataException {
        System.out.println("If you want to use your account, we need to verify your details");
        System.out.println("Enter the  Account Id assigned to you");
        int index = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter your password");
        String checkPassword = sc.nextLine();
        if (index <= 0) {
            System.out.println("Invalid UserId. Please enter a valid UserId.");
        } else if (delAccountList.contains(index)) {
            throw new InvalidMidiDataException("Can not use this account");
        } else {
            check = Accounts.get(index - 1);
            passwordChecking = password.get(index - 1);
            if (passwordChecking.equals(checkPassword)) {
                System.out.println(check);
                do {
                    System.out.println("What do you want to do?");
                    System.out.println("1.Transfer");
                    System.out.println("2.Deposit");
                    System.out.println("3.Withdraw");
                    System.out.println("Press -1 to exit");
                    useOption = sc.nextInt();

                    switch (useOption) {
                        case 1:
                            System.out.println("Transferring the money-");
                            System.out.println();
                            System.out.println("Enter the id of account holder to whose account you want to transfer the money-");
                            TransferAccountId = sc.nextInt();
                            TransferAccountIndex = TransferAccountId - 1;
                            System.out.println("Available balance in your account  is:");
                            System.out.println(check.get(1));
                            System.out.println("Enter the amount you want to transfer");
                            amount = sc.nextInt();
                            convertedAmount = Integer.parseInt(check.get(1));
                            TransferredAmnt = amount;
                            if (TransferAccountIndex == index - 1) {
                                System.out.println("You can't transfer money to yourself");
                            } else {
                                if (amount > convertedAmount) {
                                    System.out.println("Can't transfer due to low available balance");
                                } else {
                                    System.out.println("Initial bank details of the account holder to whom you are transferring the money");
                                    System.out.println(Accounts.get(TransferAccountIndex));
                                    checkAccount = Accounts.get(TransferAccountIndex);
                                    checkAccount.set(1, String.valueOf(Integer.parseInt(checkAccount.get(1)) + amount));
                                    System.out.println("New bank details of the account holder to whom you are transferring the money");
                                    System.out.println(checkAccount);
                                    check.set(1, String.valueOf(Integer.parseInt(check.get(1)) - amount));
                                    TotalAmnt = Integer.parseInt(check.get(1));
                                    System.out.println("Your Account details are now as followed-");
                                    System.out.println(check);
                                    help.add(TransferredAmnt);
                                    help.add(DepositedAmnt);
                                    help.add(WithdrawnAmnt);
                                    help.add(TotalAmnt);
                                    PrintTransactons(help);
                                    c++;
                                    TransferredAmnt = 0;
                                }
                            }

                            break;
                        case 2:
                            System.out.println("Depositing money-");
                            System.out.println("Your available balance is :" + " " + check.get(1));
                            System.out.println("Enter the amount you want to Deposit");
                            amount = sc.nextInt();
                            check.set(1, String.valueOf(Integer.parseInt(check.get(1)) + amount));
                            System.out.println("Now your available balance is");
                            System.out.println(check.get(1));
                            DepositedAmnt = amount;
                            TotalAmnt = Integer.parseInt(check.get(1));
                            System.out.println("Your Account details are now as followed-");
                            System.out.println(check);
                            help.add(TransferredAmnt);
                            help.add(DepositedAmnt);
                            help.add(WithdrawnAmnt);
                            help.add(TotalAmnt);
                            PrintTransactons(help);
                            c++;
                            DepositedAmnt = 0;
                            break;
                        case 3:
                            System.out.println("Withdrawing money-");
                            System.out.println("Your available balance is :" + " " + check.get(1));
                            System.out.println("Enter the amount you want to Withdraw");
                            amount = sc.nextInt();
                            convertedAmount = Integer.parseInt(check.get(1));
                            if (amount > convertedAmount) {
                                System.out.println("Not enough balance-");
                            } else {
                                check.set(1, String.valueOf(Integer.parseInt(check.get(1)) - amount));
                                System.out.println("Now your available balance is");
                                System.out.println(check.get(1));
                                TotalAmnt = Integer.parseInt(check.get(1));
                                WithdrawnAmnt = amount;
                                System.out.println("Your Account details are now as followed-");
                                System.out.println(check);
                                help.add(TransferredAmnt);
                                help.add(DepositedAmnt);
                                help.add(WithdrawnAmnt);
                                help.add(TotalAmnt);
                                PrintTransactons(help);
                                c++;
                                WithdrawnAmnt = 0;
                            }
                            break;
                    }
                } while (useOption != -1);
            } else {
                System.out.println("Invalid Password, you cannot use your account");
            }
        }

    }

    public static void PrintTransactons(ArrayList<Integer> help) {
        h = (ArrayList<Integer>) help.clone();
        transactionDetails.add(c, h);
        System.out.println("+-------------------+-------------------+-------------------+-------------------+");
        System.out.println("|Transferred Amount | Deposited Amount  | Withdrawn Amount  | Total Amount      |");
        System.out.println("+-------------------+-------------------+-------------------+-------------------+");
        for (ArrayList<Integer> row : transactionDetails) {
            System.out.print("|");
            for (int element : row) {
                System.out.printf(" %17d |", element);
            }
            System.out.println();
            System.out.println("+-------------------+-------------------+-------------------+-------------------+");        }
            System.out.println(transactionDetails);
            help.clear();
    }
}


