import java.io.IOException;
import java.util.*;

public class CLI {
    private static Scanner input = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        Bank bank=new Bank("UIC Bank");
        int choice;//the variable that will be used by almost all the choices
        String name;
       while (true) {//the loop
            choice=readPosInt("Type an action (total:1 add:2 list:3 withdraw:4 deposit:5 quit:6): ");
            switch (choice){
                case 1:
                    System.out.println("Total amount of money in the bank: "+bank.totalMoney());//the current money
                    continue;//keep loop
                case 2:
                    int account_type=readPosInt("Type the account type (credit:1 student:2):");
                    int initial_amount;
                    if(account_type==1||account_type==2){//judge
                        name=readLine("Enter the name of the customer: ");
                        initial_amount=readPosInt("Enter the initial amount of money: ");
                        if(account_type==1){//nested
                            bank.addAccount(new CreditAccount(name,initial_amount));
                        }else {
                            try {
                                bank.addAccount(new StudentAccount(name, initial_amount));
                            }catch(NotEnoughMoneyException e){//An Exception that will never be thrown
                                System.out.println("BUG! This must never happen!");
                                System.exit(1);
                            }
                        }
                        System.out.println((account_type==1?"Credit":"Student")+" account for "+name+" with "+initial_amount+" yuan "+"has been added");
                    }else{
                        System.out.println("Unknown type of account!");
                    }
                    continue;
                case 3:
                    name=readLine("Enter the name of the customer: ");
                    try{
                        System.out.println(name+" has "+bank.getMoney(name)+" yuan in the bank");
                    }catch (UnknownCustomerException e){
                        System.out.println("Customer "+name+" unknown");
                    }
                    continue;
                case 4:
                    name=readLine("Enter the name of the customer: ");
                    int withdraw_amount=readPosInt("Enter the amount of money to withdraw: ");
                    try{
                        bank.withdraw(name,withdraw_amount);
                    }catch (UnknownCustomerException e1){//deal with different exceptions
                        System.out.println("Customer "+name+" unknown");
                    }catch (NotEnoughMoneyException e2){
                        System.out.println(e2.getMessage());
                    }
                    continue;
                case 5:
                    name=readLine("Enter the name of the customer: ");
                    int deposit_money=readPosInt("Enter the amount of money to deposit: ");
                    try{
                        bank.withdraw(name,-1*deposit_money);//use the negative number
                    }catch (UnknownCustomerException e1){
                        System.out.println("Customer "+name+" unknown");
                    }catch (NotEnoughMoneyException e2){
                        System.out.println("BUG! This must never happen!");
                        System.exit(1);
                    }
                    continue;
                case 6:
                	bank.saveData();
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Unknown action!");
                    continue;
            }

        }
    }
    private static String readLine(String str){
        System.out.print(str);
        String line1=input.nextLine();//the string
        return line1;
    }
    private static int readPosInt(String str) {
        int integer;
        while (true){
            try {
                System.out.print(str);
                integer = input.nextInt();
            } catch (InputMismatchException e) {
                input.nextLine();//read the newline character
                System.out.println("You must type an integer!");
                continue;//keep loop
            }
            if(integer>=0){
                input.nextLine();//read the newline character
                return integer;
            }
            System.out.println("Positive integers only!");
        }
    }

}
