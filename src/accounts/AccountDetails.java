package accounts;

public class AccountDetails {
    private String fullName;
    private String emailId;
    private String mobileNo;
    private int z_Id;
    private double balance;
    private double zCoin;
    public int getZ_Id() {
        return z_Id;
    }

    public void setZ_Id(int z_Id) {
        this.z_Id = z_Id;
    }

    public double getBalance() {
        return balance;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getzCoin() {
        return zCoin;
    }

    public void setzCoin(double zCoin) {
        this.zCoin = zCoin;
    }

    @Override
    public String toString() {
        return "AccountDetails{" +
                "fullName='" + fullName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", z_Id=" + z_Id +
                ", balance=" + balance +
                ", zCoin=" + zCoin +
                '}';
    }
}
