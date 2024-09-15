package www.biplapneupane.com.np.rentcalculator.SavedDataActivity;

public class RentRecord {
    private float roomRent;
    private float electricityStart;
    private float electricityEnd;
    private float waterBill;
    private float dustCharge;
    private long paymentDate;

    // Constructor
    public RentRecord(float roomRent, float electricityStart, float electricityEnd, float waterBill, float dustCharge, long paymentDate) {
        this.roomRent = roomRent;
        this.electricityStart = electricityStart;
        this.electricityEnd = electricityEnd;
        this.waterBill = waterBill;
        this.dustCharge = dustCharge;
        this.paymentDate = paymentDate;
    }

    // Getters and Setters

    public float getRoomRent() {
        return roomRent;
    }

    public void setRoomRent(float roomRent) {
        this.roomRent = roomRent;
    }

    public float getElectricityStart() {
        return electricityStart;
    }

    public void setElectricityStart(float electricityStart) {
        this.electricityStart = electricityStart;
    }

    public float getElectricityEnd() {
        return electricityEnd;
    }

    public void setElectricityEnd(float electricityEnd) {
        this.electricityEnd = electricityEnd;
    }

    public float getWaterBill() {
        return waterBill;
    }

    public void setWaterBill(float waterBill) {
        this.waterBill = waterBill;
    }

    public float getDustCharge() {
        return dustCharge;
    }

    public void setDustCharge(float dustCharge) {
        this.dustCharge = dustCharge;
    }

    public long getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(long paymentDate) {
        this.paymentDate = paymentDate;
    }
}
