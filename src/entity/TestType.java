package entity;

public class TestType implements SuperEntity{
    private String testTypeId;
    private String testCode;
    private String typeName;
    private String referenceRange;

    public TestType() {
    }

    public TestType(String testTypeId, String testCode, String typeName, String referenceRange) {
        this.testTypeId = testTypeId;
        this.testCode = testCode;
        this.typeName = typeName;
        this.referenceRange = referenceRange;
    }

    public String getTestTypeId() {
        return testTypeId;
    }

    public void setTestTypeId(String testTypeId) {
        this.testTypeId = testTypeId;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getReferenceRange() {
        return referenceRange;
    }

    public void setReferenceRange(String referenceRange) {
        this.referenceRange = referenceRange;
    }

    @Override
    public String toString() {
        return "TestType{" +
                "testTypeId='" + testTypeId + '\'' +
                ", testCode='" + testCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", referenceRange='" + referenceRange + '\'' +
                '}';
    }
}
