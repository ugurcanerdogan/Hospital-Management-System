public class DoctorVisit extends AdditionalOperations {

    public DoctorVisit(IExamination newExamination){
        super(newExamination);
    }

    public String getDescription() {
        return tempExamination.getDescription() + "doctorvisit ";
    }

    public int cost() {
        return tempExamination.cost() +  15;
    }

}
