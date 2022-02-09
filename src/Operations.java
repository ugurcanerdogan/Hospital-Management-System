import java.io.File;                    // Importing essential packages.
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Operations {
    public static void main(String arg1) throws IOException {


        File outpObj = new File("output.txt");            // Creating output files.
        File patoutObj = new File("patientout.txt");
        File adoutObj = new File("admissionout.txt");

        FileWriter outpWriter = new FileWriter("output.txt"); // Writer objects of output files.
        FileWriter patoutWriter = new FileWriter("patientout.txt");
        FileWriter adoutWriter = new FileWriter("admissionout.txt");

        File readInput = new File(arg1);        // Reading input files.
        File readPatient = new File("patient.txt");
        File readAdmission = new File("admission.txt");

        Scanner inputScanner = new Scanner(readInput);
        Scanner patientScanner = new Scanner(readPatient);
        Scanner admissionScanner = new Scanner(readAdmission);

        IPatientDAO patientDAO = new PatientDAOImpl();                      // Creating patient data access object.
        ArrayList<Admission> admissions = new ArrayList<Admission>();               // Creating admissions arraylist for holding

        while (patientScanner.hasNextLine()) {

            String patientLine = patientScanner.nextLine();                                    // Splitting and saving patient infos line by line.
            String[] patientWords = patientLine.split("\\s+", 6);                   //
            String address = "";

            for (int j = 5; j < patientWords.length; j++) {
                address += patientWords[j];
            }
            Patient patient = new Patient(Integer.parseInt(patientWords[0]), patientWords[1], patientWords[2], patientWords[3], address);
            patientDAO.getAllPatients().add(patient);
        }

        int currentID = 0;
        while (admissionScanner.hasNextLine()) {

            String admissionLine = admissionScanner.nextLine();                                    // Splitting and saving admission infos.
            String[] admissionWords = admissionLine.split("\\s+");                           //

            if (admissionWords[0].equalsIgnoreCase("Outpatient")) {                                 // Outpatient admission check block.

                IExamination examination = decoratorCheck(admissionWords, new OutpatientExamination(), 1);
                for (Admission admission : admissions) {
                    if (admission.getAdmissionID() == currentID) {
                        admission.getOutpatientExaminations().add(examination);
                        break;
                    }
                }
            } else if (admissionWords[0].equalsIgnoreCase("Inpatient")) {                           // Inpatient admission check block.

                IExamination examination = decoratorCheck(admissionWords, new InpatientExamination(), 1);
                for (Admission admission : admissions) {
                    if (admission.getAdmissionID() == currentID) {
                        admission.getInpatientExaminations().add(examination);
                        break;
                    }
                }
            } else {                                                                                            // Creating new admission object.
                Admission admission = new Admission(Integer.parseInt(admissionWords[0]), Integer.parseInt(admissionWords[1]));
                admissions.add(admission);
                currentID = Integer.parseInt(admissionWords[0]);
            }
        }


        while (inputScanner.hasNextLine()) {                    // Reading input commands line by line.
            String inputLine = inputScanner.nextLine();
            String[] inputWords = inputLine.split("\\s+");


                            //*****************************************************************//   CHECK BLOCKS FOR EACH COMMAND
            if (inputWords[0].equalsIgnoreCase("AddPatient")) {
                String adress = "";
                for (int j = 5; j < inputWords.length; j++) {
                    adress += inputWords[j] + " ";
                }
                Patient patient = new Patient(Integer.parseInt(inputWords[1]), inputWords[2], inputWords[3], inputWords[4], adress);
                patientDAO.getAllPatients().add(patient);
                outpWriter.write("Patient " + patient.getPatientID() + " " + patient.getPatientName() + " added" + "\n");
            }
                            //*****************************************************************//
            else if (inputWords[0].equalsIgnoreCase("RemovePatient")) {
                for (Patient patient : patientDAO.getAllPatients()) {
                    if (patient.getPatientID() == Integer.parseInt(inputWords[1])) {
                        patientDAO.deletePatient(patient);
                        outpWriter.write("Patient " + patient.getPatientID() + " " + patient.getPatientName() + " removed" + "\n");
                        break;
                    }
                }
                for (Admission admission : admissions) {
                    if (admission.getPatientID() == Integer.parseInt(inputWords[1])) {
                        admissions.remove(admission);
                        break;
                    }
                }
            }
                            //*****************************************************************//
            else if (inputWords[0].equalsIgnoreCase("CreateAdmission")) {
                Admission admission = new Admission(Integer.parseInt(inputWords[1]), Integer.parseInt(inputWords[2]));
                admissions.add(admission);
                outpWriter.write("Admission " + admission.getAdmissionID() + " created" + "\n");
            }
                            //*****************************************************************//
            else if (inputWords[0].equalsIgnoreCase("AddExamination")) {
                if (inputWords[2].equalsIgnoreCase("Inpatient")) {
                    IExamination examination = decoratorCheck(inputWords, new InpatientExamination(), 3);
                    for (Admission admission : admissions) {
                        if (admission.getAdmissionID() == Integer.parseInt(inputWords[1])) {
                            admission.getInpatientExaminations().add(examination);
                            outpWriter.write("Inpatient examination added to admission " + admission.getAdmissionID() + "\n");
                            break;
                        }
                    }
                } else if (inputWords[2].equalsIgnoreCase("Outpatient")) {
                    IExamination examination = decoratorCheck(inputWords, new OutpatientExamination(), 3);
                    for (Admission admission : admissions) {
                        if (admission.getAdmissionID() == Integer.parseInt(inputWords[1])) {
                            admission.getOutpatientExaminations().add(examination);
                            outpWriter.write("Outpatient examination added to admission " + admission.getAdmissionID() + "\n");
                            break;
                        }
                    }
                }
            }
                                //*****************************************************************//
            else if (inputWords[0].equalsIgnoreCase("TotalCost")) {
                for (Admission admission : admissions) {
                    if (admission.getAdmissionID() == Integer.parseInt(inputWords[1])) {
                        int inpatientTotal = 0;
                        int outpatientTotal = 0;
                        String inpatientDescription = "";
                        String outpatientDescription = "";
                        for (IExamination examination : admission.getInpatientExaminations()) {
                            inpatientTotal += examination.cost();
                            inpatientDescription += examination.getDescription() + " ";
                        }
                        for (IExamination examination : admission.getOutpatientExaminations()) {
                            outpatientTotal += examination.cost();
                            outpatientDescription += examination.getDescription() + " ";
                        }
                        outpWriter.write("TotalCost for admission " + admission.getAdmissionID() + "\n");
                        if (inpatientTotal != 0) {
                            outpWriter.write("\t" + inpatientDescription + inpatientTotal + "$" + "\n");// yanyana basıyor hata gelecek
                        }
                        if (outpatientTotal != 0) {
                            outpWriter.write("\t" + outpatientDescription + outpatientTotal + "$" + "\n");
                        }
                        outpWriter.write("\t" + "Total: " + (inpatientTotal + outpatientTotal) + "$" + "\n");
                        break;
                    }
                }

            }
                            //*****************************************************************//
            else if (inputWords[0].equalsIgnoreCase("ListPatients")) {
                Collections.sort(patientDAO.getAllPatients(), Comparator.comparing(Patient::getPatientName));
                outpWriter.write("Patient List:" + "\n");
                for (Patient patient : patientDAO.getAllPatients()) {
                    outpWriter.write(patient.getPatientID() + " " + patient.getPatientName() + " " + patient.getPatientSurname() + " " + patient.getPhoneNumber() + " " + "Address: " + patient.getAdress() + "\n");
                }
            }
        }

        Collections.sort(admissions, Comparator.comparing(Admission::getAdmissionID));      // Writing in admissionout file.
        for (Admission admission : admissions) {
            String inpatientDescription = "";
            String outpatientDescription = "";
            adoutWriter.write(admission.getAdmissionID() + "\t" + admission.getPatientID() + "\n");
            for (IExamination examination : admission.getInpatientExaminations()) {
                inpatientDescription += examination.getDescription() + "\n";
            }
            for (IExamination examination : admission.getOutpatientExaminations()) {
                outpatientDescription += examination.getDescription() + "\n";
            }
            if (inpatientDescription.length() != 0) {
                adoutWriter.write(inpatientDescription);
            }
            if (outpatientDescription.length() != 0) {
                adoutWriter.write(outpatientDescription);
            }


        }

        Collections.sort(patientDAO.getAllPatients(), Comparator.comparing(Patient::getPatientID)); // Writing in patientout file.
        for (Patient patient : patientDAO.getAllPatients()) {
            patoutWriter.write(patient.getPatientID() + "\t" + patient.getPatientName() + " " + patient.getPatientSurname() + "\t" + patient.getPhoneNumber() + "\t" + "Address: " + patient.getAdress() + "\n");
        }

        outpWriter.close();                         // Closing the files after essential writing operations.
        patoutWriter.close();
        adoutWriter.close();
    }


    public static IExamination decoratorCheck(String[] lines, IExamination examination, int splitter) {     // Decorator pattern usage function.
        for (int i = splitter; i < lines.length; i++) {

            if (lines[i].equalsIgnoreCase("doctorvisit")) {             // It wraps and returns the related examination type in the end.
                examination = new DoctorVisit(examination);
            } else if (lines[i].equalsIgnoreCase("tests")) {
                examination = new Tests(examination);
            } else if (lines[i].equalsIgnoreCase("imaging")) {
                examination = new Imaging(examination);
            } else if (lines[i].equalsIgnoreCase("measurements")) {
                examination = new Measurements(examination);
            }
        }
        return examination;
    }
}