package view.tm;

import javafx.scene.control.Button;

public class SampleTypeTM {
    private String sampleId;
    private String typeName;
    private String unit;
    private Button btn;

    public SampleTypeTM() {
    }

    public SampleTypeTM(String sampleId, String typeName, String unit, Button btn) {
        this.sampleId = sampleId;
        this.typeName = typeName;
        this.unit = unit;
        this.btn = btn;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
