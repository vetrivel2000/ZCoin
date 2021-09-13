package logical;

import accounts.AccountDetails;
import admin.ZEmployee;
import customer.CustomerDetails;
import load.LoadToMemory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class LogicalLayer {
    LoadToMemory load = new LoadToMemory();
    ZEmployee employee= new ZEmployee();
    static int z_Id=0;
    public boolean userRules(String name,String email,String mobileNo,String password)
    {
        String regex="^(?=.*[0-9])"
                      +"(?=.*[a-z])(?=>*[A-Z])"
                      +"(?=.*[!#%?<>&*])"
                      +"(?=\\S+$).{8,20}$";
        boolean result= Pattern.matches(regex,password);

        if(!(password.contains(name)&&password.contains(email)&&password.contains(mobileNo)) && result)
            return true;
        else
            return false;
    }
    public int generateZId()
    {
        z_Id++;
        return z_Id;
    }
    public CustomerDetails getCustomerObject(String name,String email,String mobileNo,String password,int h_Id,double amount)
    {
        CustomerDetails object=new CustomerDetails();
        object.setFullName(name);
        object.setEmailId(email);
        object.setMobileNo(mobileNo);
        object.setPassword(password);
        object.setH_ID(h_Id);
        object.setInitialRealCurrency(amount);
        return object;
    }
    public AccountDetails getAccountObject(String name,String email,String mobileNo,int z_Id,double balance)
    {
        AccountDetails object = new AccountDetails();
        object.setFullName(name);
        object.setEmailId(email);
        object.setMobileNo(mobileNo);
        object.setZ_Id(z_Id);
        object.setBalance(balance);
        double zCoin= employee.RCtoZC(balance);
        object.setzCoin(zCoin);
        return object;
    }
    public void addToUserMap(ArrayList<CustomerDetails> list)
    {
        load.insertToUserMap(list);
    }
    public void addToAccountMap(ArrayList<AccountDetails> list)
    {
        load.insertToAccountMap(list);
    }
    public boolean checkList(String email,String password)
    {
        boolean value=load.checkUser(email,password);
        return value;
    }
    public boolean checkAgent(String email,String password)
    {
        boolean value= load.checkAgent(email,password);
        return value;
    }
    public boolean checkAmount(int z_Id,double amount)
    {
        HashMap<Integer,AccountDetails> accountDetailsHashMap = load.getAccountMap();
        AccountDetails accountDetails = accountDetailsHashMap.get(z_Id);
        if(amount>accountDetails.getBalance())
        {
            return false;
        }
        return true;
    }
    public void updateBalance(int z_Id,double amount)
    {
        HashMap<Integer,AccountDetails> accountDetailsHashMap = load.getAccountMap();
        AccountDetails accountDetails = accountDetailsHashMap.get(z_Id);
        double newAmount = accountDetails.getBalance()-amount;
        accountDetails.setBalance(newAmount);
    }
    public void updateDepositBalance(int z_Id,double amount)
    {
        HashMap<Integer,AccountDetails> accountDetailsHashMap = load.getAccountMap();
        AccountDetails accountDetails = accountDetailsHashMap.get(z_Id);
        double newAmount = accountDetails.getBalance()+amount;
        accountDetails.setBalance(newAmount);
    }
    public void updatePassWord(String email,String newPassWord)
    {
        HashMap<String,String> userMap= load.getUserMap();
        userMap.replace(email,newPassWord);
    }
    public HashMap<Integer,AccountDetails> getAccountDetails()
    {
        HashMap<Integer,AccountDetails> map = load.getAccountMap();
        return map;
    }
    public boolean checkZId(int z_Id)
    {
        boolean key=load.checkZId(z_Id);
        return key;
    }
}
