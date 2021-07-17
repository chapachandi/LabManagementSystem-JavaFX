package dto;

public class TestDetailDTO {
    private String requestId;
    private int paymentId;
    private String testCode;
    private String unitCode;
    private String testName;
    private double price;


    public TestDetailDTO() {
    }

    public TestDetailDTO(String requestId, int paymentId, String testCode, String unitCode, String testName, double price) {
        this.setRequestId(requestId);
        this.setPaymentId(paymentId);
        this.setTestCode(testCode);
        this.setUnitCode(unitCode);
        this.setTestName(testName);
        this.setPrice(price);
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

    @Override
    public String toString() {
        return "TestDetailDTO{" +
                "requestId='" + requestId + '\'' +
                ", paymentId=" + paymentId +
                ", testCode='" + testCode + '\'' +
                ", unitCode='" + unitCode + '\'' +
                ", testName='" + testName + '\'' +
                ", price=" + price +
                '}';
    }
}
