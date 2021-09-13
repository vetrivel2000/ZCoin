package admin;

public class ZEmployee {
    int conversionRate=2;
    public void changeRates(int newRate)
    {
        conversionRate=newRate;
    }
    public double RCtoZC(double realCurrency)
    {
        return realCurrency/conversionRate;
    }
}
