package view.tm;

import javafx.scene.control.Button;

public class TestTypeTM {
    private String testTypeId;
    private String testCode;
    private String typeName;
    private String referenceRange;
    private Button btn;

    public TestTypeTM(String testTypeId, String testCode, String typeName, String referenceRange, Button btn) {
        this.testTypeId = testTypeId;
        this.testCode = testCode;
        this.typeName = typeName;
        this.referenceRange = referenceRange;
        this.btn = btn;
    }

    public TestTypeTM() {
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
