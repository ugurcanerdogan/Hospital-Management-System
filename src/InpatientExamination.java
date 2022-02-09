public class InpatientExamination implements IExamination { // Inpatient examination type with its unique values.

    @Override
    public String getDescription() {
        return "Inpatient\t";
    }

    @Override
    public int cost() {
        return 10;
    }

}