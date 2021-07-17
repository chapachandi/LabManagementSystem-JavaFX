package dto;

public class SampleTypeDTO {
    private String sampleId;
    private String typeName;
    private String unit;

    public SampleTypeDTO() {
    }

    public SampleTypeDTO(String sampleId, String typeName, String unit) {
        this.sampleId = sampleId;
        this.typeName = typeName;
        this.unit = unit;
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

    @Override
    public String toString() {
        return "SampleTypeDTO{" +
                "sampleId='" + sampleId + '\'' +
                ", typeName='" + typeName + '\'' +
                ", unit='" + unit + '\'' +
                '}';
    }
}
