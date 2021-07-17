package view.tm;


import javafx.scene.control.Button;

public class PatientTM {
    private String patientId;
    private String title;
    private String name;
    private String address;
    private String gender;
    private String age;
    private String nic;
    private int mobile;
    private Button btn;

    public PatientTM() {
    }

    public PatientTM(String patientId, String title, String name, String address, String gender, String age, String nic, int mobile, Button btn) {
        this.patientId = patientId;
        this.title = title;
        this.name = name;
        this.address = address;
        this.gender = gender;
        this.age = age;
        this.nic = nic;
        this.mobile = mobile;
        this.btn = btn;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public int getMobile() {
        return mobile;
    }

    public void setMobile(int mobile) {
        this.mobile = mobile;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }
}
