package customer;

public class CustomerDetails {
    private String fullName;
    private String emailId;
    private String mobileNo;
    private String password;
    private int h_ID;
    private double initialRealCurrency;

    public String getPassword() {
        return password;
    }

    public int getH_ID() {
        return h_ID;
    }

    public void setH_ID(int h_ID) {
        this.h_ID = h_ID;
    }

    public double getInitialRealCurrency() {
        return initialRealCurrency;
    }

    public void setInitialRealCurrency(double initialRealCurrency) {
        this.initialRealCurrency = initialRealCurrency;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "CustomerDetails{" +
                "fullName='" + fullName + '\'' +
                ", emailId='" + emailId + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", password='" + password + '\'' +
                ", h_ID=" + h_ID +
                ", initialRealCurrency=" + initialRealCurrency +
                '}';
    }
}
