package load;

import accounts.AccountDetails;
import customer.CustomerDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class LoadToMemory {
    HashMap<String,String> userMap= new HashMap<>();
    HashMap<Integer,AccountDetails> accountMap= new HashMap<>();
    HashMap<String,String> agentMap= new HashMap<>();
    public LoadToMemory()
    {
        String email="vetrivel1999@gmail.com";
        String password="Constant#123";
        agentMap.put(email,password);
    }
    public void insertToUserMap(ArrayList<CustomerDetails> list)
    {
        for (int i = 0; i < list.size() ; i++) {
            CustomerDetails customerDetails = list.get(i);
            userMap.put(customerDetails.getEmailId(),customerDetails.getPassword());
        }
    }
    public void insertToAccountMap(ArrayList<AccountDetails> list)
    {
        for (int i = 0; i < list.size() ; i++) {
            AccountDetails accountDetails=list.get(i);
            accountMap.put(accountDetails.getZ_Id(),accountDetails);
        }
    }
    public boolean checkAgent(String email,String password)
    {
        if(agentMap.get(email)==null)
        {
            return false;
        }
        else if(!(agentMap.containsKey(email)))
        {
            return false;
        }
        else return Objects.equals(agentMap.get(email), password);
    }
    public HashMap<Integer,AccountDetails> getAccountMap()
    {
        return accountMap;
    }
    public HashMap<String,String> getUserMap()
    {
        return userMap;
    }
    public boolean checkZId(int z_Id)
    {
        return accountMap.get(z_Id) != null;
    }
    public boolean checkUser(String email,String password)
    {
        if(userMap.get(email)==null)
        {
            return false;
        }
        else if(!(userMap.containsKey(email)))
        {
            return false;
        }
        else return Objects.equals(userMap.get(email), password);
    }
}
