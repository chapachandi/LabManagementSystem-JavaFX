package entity;

public class Patient implements SuperEntity {
    private String patientId;
    private String title;
    private String name;
    private String address;
    private String gender;
    private String age;
    private String nic;
    private int mobile;

    public Patient() {
    }

    public Patient(String patientId, String title, String name, String address, String gender, String age, String nic, int mobile) {
        this.setPatientId(patientId);
        this.setTitle(title);
        this.setName(name);
        this.setAddress(address);
        this.setGender(gender);
        this.setAge(age);
        this.setNic(nic);
        this.setMobile(mobile);
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

    @Override
    public String toString() {
        return "Patient{" +
                "patientId='" + patientId + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", nic='" + nic + '\'' +
                ", mobile=" + mobile +
                '}';
    }
}
