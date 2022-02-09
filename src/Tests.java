public class Tests extends AdditionalOperations {

    public Tests(IExamination newExamination) {
        super(newExamination);
    }

    public String getDescription() {
        return tempExamination.getDescription() + "tests ";
    }

    public int cost() {
        return tempExamination.cost() + 7;
    }

}
