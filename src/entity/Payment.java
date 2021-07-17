package entity;

public class Payment implements SuperEntity{
    private int paymentId;
    private String patientId;
    private String testName;
    private String date;
    private String time;
    private double subtotal;
    private double amount;
    private double balance;
    private String paymentType;

    public Payment() {
    }

    public Payment(int paymentId, String patientId, String testName, String date, String time, double subtotal, double amount, double balance, String paymentType) {
        this.paymentId = paymentId;
        this.patientId = patientId;
        this.testName = testName;
        this.date = date;
        this.time = time;
        this.subtotal = subtotal;
        this.amount = amount;
        this.balance = balance;
        this.paymentType = paymentType;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", patientId='" + patientId + '\'' +
                ", testName='" + testName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", subtotal=" + subtotal +
                ", amount=" + amount +
                ", balance=" + balance +
                ", paymentType='" + paymentType + '\'' +
                '}';
    }
}
