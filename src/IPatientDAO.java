import java.util.List;      // Patient Data Access Object Interface

public interface IPatientDAO {
    public List<Patient> getAllPatients();

    public Patient getPatient(int patientID);

    public void updatePatient(Patient patient);

    public void deletePatient(Patient patient);
}
