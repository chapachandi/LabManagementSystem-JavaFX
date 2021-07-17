package view.tm;

import javafx.scene.control.Button;

public class TestDetailTM {
    private String requestId;
    private int paymentId;
    private String testCode;
    private String unitCode;
    private String testName;
    private double price;
    private Button btn;

    public TestDetailTM() {
    }

    public TestDetailTM(String requestId, int paymentId, String testCode, String unitCode, String testName, double price, Button btn) {
        this.requestId = requestId;
        this.paymentId = paymentId;
        this.testCode = testCode;
        this.unitCode = unitCode;
        this.testName = testName;
        this.price = price;
        this.btn = btn;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
