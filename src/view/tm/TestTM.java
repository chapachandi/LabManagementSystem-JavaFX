package view.tm;

import javafx.scene.control.Button;

public class TestTM {
    private String testCode;
    private String testName;
    private double price;
    private Button btn;

    public TestTM() {
    }

    public TestTM(String testCode, String testName, double price, Button btn) {
        this.testCode = testCode;
        this.testName = testName;
        this.price = price;
        this.btn = btn;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
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
