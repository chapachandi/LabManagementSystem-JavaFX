package view.tm;

import javafx.scene.control.Button;
import rex.utils.S;

public class PaymentTM {
    private int paymentId;
    private String patientId;
    private String testName;
    private String date;
    private String time;
    private double subtotal;
    private double amount;
    private double balance;
    private String paymentType;
    private Button btn;

    public PaymentTM() {
    }

    public PaymentTM(int paymentId, String patientId, String testName, String date, String time, double subtotal, double amount, double balance, String paymentType, Button btn) {
        this.paymentId = paymentId;
        this.patientId = patientId;
        this.testName = testName;
        this.date = date;
        this.time = time;
        this.subtotal = subtotal;
        this.amount = amount;
        this.balance = balance;
        this.paymentType = paymentType;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
