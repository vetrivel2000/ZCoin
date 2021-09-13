import accounts.AccountDetails;
import admin.ZEmployee;
import customer.CustomerDetails;
import logical.LogicalLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ZCoinRunner {
    static LogicalLayer logical= new LogicalLayer();
    static Scanner scan = new Scanner(System.in);
    public static void main(String[] args)
    {
        ArrayList<CustomerDetails> userList= new ArrayList<>();
        ArrayList<AccountDetails> accountList = new ArrayList<>();
        int option;
        do{
            System.out.println("Enter your option");
            System.out.println("1.CreateAccount\n2.UserLogin\n3.ZEAgentLogin");
            option=scan.nextInt();
            switch (option)
            {
                case 1:
                {
                    System.out.println("Enter your name:");
                    String name = scan.next();
                    System.out.println("Enter your emailId:");
                    String email = scan.next();
                    System.out.println("Enter your mobile:");
                    String  mobileNo = scan.next();
                    System.out.println("Enter a password to create your account:");
                    String password= scan.next();
                    System.out.println("Enter humanId");
                    int h_Id= scan.nextInt();
                    System.out.println("Enter InitialDeposit:");
                    double amount= scan.nextDouble();
                    boolean result=logical.userRules(name,email,mobileNo,password);
                    if(!result)
                    {
                        System.out.println("Enter password with at least one of uppercase,and at least a special character with minimum 8 characters");
                        return;
                    }
                    int z_Id=logical.generateZId();
                    System.out.println("Your ZId is"+z_Id);
                    CustomerDetails customerObject= logical.getCustomerObject(name,email,mobileNo,password,h_Id,amount);
                    userList.add(customerObject);
                    AccountDetails accountObject = logical.getAccountObject(name,email,mobileNo,z_Id,amount);
                    accountList.add(accountObject);
                    logical.addToUserMap(userList);
                    logical.addToAccountMap(accountList);
                    break;
                }
                case 2:
                {
                    System.out.println("Enter EmailId");
                    String email = scan.next();
                    System.out.println("Enter password");
                    String password = scan.next();
                    boolean flag=logical.checkList(email,password);
                    if(!flag)
                    {
                        System.out.println("Check credentials");
                        return;
                    }
                    System.out.println("Successfully login");
                    int choice;
                    do{
                        System.out.println("Enter your choice");
                        System.out.println("1.ShowAccountInfo\n2.Withdraw\n3.Deposit\n4.Transaction\n5.ChangePassword");
                        choice= scan.nextInt();
                        switch (choice)
                        {
                            case 1:
                            {
                                showInfo();
                                break;
                            }
                            case 2:
                            {
                                withdraw();
                                break;
                            }
                            case 3:
                            {
                                deposit();
                                break;
                            }
                            case 4:
                            {
                                transaction();
                                break;
                            }
                            case 5:
                            {
                                changePassword();
                                break;
                            }
                        }
                    }while(choice<6);
                    break;
                }
                case 3:
                {
                    System.out.println("Enter EmailId");
                    String email = scan.next();
                    System.out.println("Enter password");
                    String password = scan.next();
                    boolean checkFlag=logical.checkAgent(email,password);
                    if(!checkFlag)
                    {
                        System.out.println("Check credentials");
                        return;
                    }
                    System.out.println("Successfully login");
                    int choice1;
                    do {
                        System.out.println("Enter your choice");
                        System.out.println("1.ChangeConversionRate\n");
                        choice1= scan.nextInt();
                        ZEmployee employee = new ZEmployee();
                        switch (choice1)
                        {
                            case 1:
                            {
                                System.out.println("Enter your conversion rate:");
                                int newRate= scan.nextInt();
                                employee.changeRates(newRate);
                            }
                        }
                    }while (choice1<3);
                    break;
                }
                default:
                {
                    System.out.println("No such case");
                }
            }
        }while (option<4);
    }
    public static void showInfo()
    {
        System.out.println("Enter your Z-Id");
        int z_Id= scan.nextInt();
        boolean check=logical.checkZId(z_Id);
        if(!check)
        {
            System.out.println("Check your ZId");
            return;
        }
        HashMap<Integer,AccountDetails> accountDetailsHashMap= logical.getAccountDetails();
        System.out.println(accountDetailsHashMap.get(z_Id));
    }
    public static void withdraw()
    {
        System.out.println("Enter your Id");
        int yourId= scan.nextInt();
        boolean check1= logical.checkZId(yourId);
        if(!check1)
        {
            System.out.println("Check your id");
            return;
        }
        System.out.println("Enter the amount you want to withdraw");
        double amount = scan.nextDouble();
        boolean checkAmount=logical.checkAmount(yourId,amount);
        if(!checkAmount)
        {
            System.out.println("InsufficientBalance");
            return;
        }
        logical.updateBalance(yourId,amount);
    }
    public static void deposit()
    {
        System.out.println("Enter your Id");
        int yourId= scan.nextInt();
        boolean check1= logical.checkZId(yourId);
        if(!check1)
        {
            System.out.println("Check your id");
            return;
        }
        System.out.println("Enter the amount you want to deposit");
        double amount = scan.nextDouble();
        logical.updateDepositBalance(yourId,amount);
    }
    public static void transaction()
    {
        System.out.println("Enter your z_id");
        int yourId= scan.nextInt();
        System.out.println("Enter your receivers id:");
        int receiverId= scan.nextInt();
        boolean check1= logical.checkZId(yourId);
        boolean check2= logical.checkZId(receiverId);
        if(!check1)
        {
            System.out.println("Check your id");
            return;
        }
        if(!check2)
        {
            System.out.println("Check your receiver id");
            return;
        }
        System.out.println("Enter ZCoin that you want to transfer");
        double zCoin = scan.nextDouble();
        logical.checkAmount(yourId,zCoin);
        logical.updateBalance(yourId,zCoin);
        logical.updateDepositBalance(receiverId,zCoin);
    }
    public static void changePassword()
    {
        System.out.println("Enter EmailId");
        String email = scan.next();
        System.out.println("Enter old password");
        String password = scan.next();
        boolean flag=logical.checkList(email,password);
        if(!flag)
        {
            System.out.println("Check credentials");
            return;
        }
        System.out.println("Enter new password");
        String newPassword=scan.next();
        logical.updatePassWord(email,newPassword);
        System.out.println("Password changed");
    }
}
