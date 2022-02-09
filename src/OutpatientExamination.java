public class OutpatientExamination implements IExamination{ // Outpatient examination type with its unique values.

    @Override
    public String getDescription() {
        return "Outpatient\t";
    }

    @Override
    public int cost() {
        return 15;
    }

}