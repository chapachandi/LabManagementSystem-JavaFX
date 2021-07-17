package entity;

public class Test implements SuperEntity{
    private String testCode;
    private String testName;
    private double price;

    public Test() {
    }

    public Test(String testCode, String testName, double price) {
        this.testCode = testCode;
        this.testName = testName;
        this.price = price;
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

    @Override
    public String toString() {
        return "Test{" +
                "testCode='" + testCode + '\'' +
                ", testName='" + testName + '\'' +
                ", price=" + price +
                '}';
    }
}
