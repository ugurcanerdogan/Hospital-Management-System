public class Measurements extends AdditionalOperations {

    public Measurements(IExamination newExamination){
        super(newExamination);
    }

    public String getDescription() {
        return tempExamination.getDescription() + "measurements ";
    }

    public int cost() {
        return tempExamination.cost() +  5;
    }

}
