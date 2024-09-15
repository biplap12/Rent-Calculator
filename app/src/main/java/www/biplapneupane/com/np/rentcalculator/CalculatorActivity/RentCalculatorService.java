package www.biplapneupane.com.np.rentcalculator.CalculatorActivity;

public class RentCalculatorService {
    public double calculateRent(double roomRent, double electricityStart, double electricityEnd, double waterBill, double dustCharge) {
        double electricityCharge = electricityEnd - electricityStart;
        double totalRent = roomRent + (electricityCharge*15) + waterBill + dustCharge;
        return totalRent;
    }
}
