public class Patient {              // Patient Class for holding patient informations.
    private int patientID;
    private String patientName;
    private String patientSurname;
    private String PhoneNumber;
    private String adress;

    public Patient(int patientID, String patientName, String patientSurname, String phoneNumber, String adress) {
        this.patientID = patientID;
        this.patientName = patientName;
        this.patientSurname = patientSurname;
        this.PhoneNumber = phoneNumber;
        this.adress = adress;
    }

    public int getPatientID() {
        return patientID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getPatientSurname() {
        return patientSurname;
    }

    public void setPatientSurname(String patientSurname) {
        this.patientSurname = patientSurname;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}

