import java.util.ArrayList;  // Admission class holds admission informations.

public class Admission {

    private int admissionID;
    private int patientID;                                              // <------ We can also access related patient object with patientID. ***
    private ArrayList<IExamination> inpatientExaminations;
    private ArrayList<IExamination> outpatientExaminations;

    public Admission(int admissionID, int patientID) {
        this.admissionID = admissionID;
        this.patientID = patientID;
        inpatientExaminations = new ArrayList<IExamination>();
        outpatientExaminations = new ArrayList<IExamination>();
    }

    public int getAdmissionID() {
        return admissionID;
    }

    public int getPatientID() {
        return patientID;
    }

    public ArrayList<IExamination> getInpatientExaminations() {
        return inpatientExaminations;
    }

    public ArrayList<IExamination> getOutpatientExaminations() {
        return outpatientExaminations;
    }

    public void setAdmissionID(int admissionID) {
        this.admissionID = admissionID;
    }

    public void setPatientID(int patientID) {
        this.patientID = patientID;
    }

}
