package dto;

public class TestDTO {
    private String testCode;
    private String testName;
    private double price;

    public TestDTO() {
    }

    public TestDTO(String testCode, String testName, double price) {
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
        return "TestDTO{" +
                "testCode='" + testCode + '\'' +
                ", testName='" + testName + '\'' +
                ", price=" + price +
                '}';
    }
}
