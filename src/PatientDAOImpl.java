import java.util.ArrayList;     // Patient Data Access Object Implementation Class
import java.util.List;

public class PatientDAOImpl implements IPatientDAO {

    List<Patient> patients;

    public PatientDAOImpl(){
        patients = new ArrayList<Patient>();
    }

    @Override
    public void deletePatient(Patient patient) {
        patients.remove(patient);
    }
    @Override
    public List<Patient> getAllPatients() {
        return patients;
    }

    @Override
    public Patient getPatient(int rollNo) {
        return patients.get(rollNo);
    }

    @Override
    public void updatePatient(Patient patient) {
        patients.get(patient.getPatientID()).setPatientName(patient.getPatientName());
    }
}