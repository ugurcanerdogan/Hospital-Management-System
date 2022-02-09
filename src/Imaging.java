public class Imaging extends AdditionalOperations {

    public Imaging(IExamination newExamination){
        super(newExamination);
    }

    public String getDescription() {
        return tempExamination.getDescription() + "imaging ";
    }

    public int cost() {
        return tempExamination.cost() +  10;
    }

}
