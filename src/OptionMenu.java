import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.InputMismatchException;

public class OptionMenu {
    static Scanner menuInput = new Scanner(System.in);
    static DecimalFormat moneyFormat = new DecimalFormat("'$'###,##50.00");
    static HashMap<Integer,Account> data = new HashMap<Integer, Account>();

    public static void getLogin() throws IOException {
       boolean end = false;
       int customerNumber = 0;
       int pinNumber = 0;
       while(!end){
           try{
               System.out.println("\n Enter your customer number : ");
               customerNumber = menuInput.nextInt();
               System.out.println("\n Enter your PIN number : ");
               pinNumber = menuInput.nextInt();
               Iterator it = data.entrySet().iterator();

               while(it.hasNext()){
                   Map.Entry pair = (Map.Entry) it.next();
                   Account acc = (Account) pair.getValue();
                   if(data.containsKey(customerNumber) && pinNumber == acc.getPinNumber());
                   {
                       getAccountType(acc);
                       end = true;
                       break;
                   }
               }
               if(!end){
                   System.out.println("\nWrong Customer Number or Pin Number");
               }
           }
           catch(InputMismatchException e){
               System.out.println("\nInvalid Character(s).Only Numbers.");
           }
       }
    }

    public static void getAccountType(Account acc){
        boolean end = false;
        while(!end){
            try{
                System.out.println("\nSelect the account you want to access : ");
                System.out.println("Type 1 - Checking Account");
                System.out.println("Type 2 - Saving Account");
                System.out.println("Type 3 - Exit");
                System.out.println("\nChoice : ");

                int selection = menuInput.nextInt();

                switch (selection){
                    case 1:
                        getChecking(acc);
                        break;
                    case 2:
                        getSaving(acc);
                        break;
                    case 3:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice");
                }
            }
            catch(InputMismatchException e){
                System.out.println("\nInvalid Choice");
                menuInput.next();
            }
        }
    }

    public static void getSaving(Account acc){
        boolean end = false;
        while(!end){
            try{
                System.out.println("\nSavings Account");
                System.out.println("Type 1 - View Balance");
                System.out.println("Type 2 - Withdraw Balance");
                System.out.println("Type 3 - Deposit Balance");
                System.out.println("Type 4 - Transfer Funds");
                System.out.println("Type 5 - Exit");
                System.out.print("Choice: ");
                int selection = menuInput.nextInt();
                switch (selection){
                    case 1:
                        System.out.println("\nSavings Account Balance : " +moneyFormat.format(acc.getSavingBalance()));
                    case 2 :
                        acc.getSavingWithdrawInput();
                        break;
                    case 3:
                        acc.getSavingDepositInput();
                        break;
                    case 4 :
                        acc.getTransferInput("Savings");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("\nInvalid Choice");
                menuInput.next();
            }
        }
    }

    public static void getChecking(Account acc){
        boolean end = false;
        while(!end){
            try{
                System.out.println("\nChecking Account");
                System.out.println("Type 1 - View Balance");
                System.out.println("Type 2 - Withdraw Balance");
                System.out.println("Type 3 - Deposit Balance");
                System.out.println("Type 4 - Transfer Funds");
                System.out.println("Type 5 - Exit");
                System.out.print("Choice: ");
                int selection = menuInput.nextInt();
                switch (selection){
                    case 1:
                        System.out.println("\nChecking Account Balance : " +moneyFormat.format(acc.getCheckingBalance()));
                    case 2 :
                        acc.getCheckingWithdrawInput();
                        break;
                    case 3:
                        acc.getCheckingDepositInput();
                        break;
                    case 4 :
                        acc.getTransferInput("Checking");
                        break;
                    case 5:
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("\nInvalid Choice");
                menuInput.next();
            }
        }
    }

    public static void createAccount() throws IOException{
        int cst_no =0;
        boolean end = false;
        while(!end){
            try{
                System.out.println("\nEnter your customer number");
                cst_no = menuInput.nextInt();
                Iterator it = data.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry pair = (Map.Entry) it.next();
                    if(!data.containsKey (cst_no)){
                        end = true;
                    }
                }
                if(!end){
                    System.out.println("\nThis Customer number is Already Registered");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("\nInvalid Choice");
                menuInput.next();
            }
        }
        System.out.println("\nEnter PIN to be registered ");
        int pin = menuInput.nextInt();
        data.put(cst_no,new Account(952141, 191904));
        System.out.println("\nYour New Account has been successfully registered!!");
        System.out.println("\nRedirecting to login.........");
        getLogin();
    }

    public static void mainMenu() throws IOException{
        data.put(952141,new Account(952141,191904));
        data.put(123,new Account(123, 123));
        boolean end = false;
        while (!end){
            try{
                System.out.println("\n Type 1 - Login");
                System.out.println("\n Type 2 - Create Account");
                System.out.println("\nChoice : ");
                int choice = menuInput.nextInt();
                switch (choice){
                    case 1 :
                        getLogin();
                        end = true;
                        break;
                    case 2:
                        createAccount();
                        end = true;
                        break;
                    default:
                        System.out.println("\nInvalid Choice");
                }
            }
            catch(InputMismatchException e) {
                System.out.println("\nInvalid Choice");
                menuInput.next();
            }
        }
    System.out.println("\nThank You For using this ATM.\n");
    menuInput.close();
    System.exit(0);
    }
}
