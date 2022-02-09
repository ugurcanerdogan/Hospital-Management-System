public abstract class AdditionalOperations implements IExamination{ // Abstract class for decorator pattern.
                                                                    // It inherits measurements,tests,doctorvisit and imaging.
    protected IExamination tempExamination;

    public AdditionalOperations(IExamination newExamination){
        tempExamination = newExamination;

    }

    @Override
    public String getDescription() {
        return tempExamination.getDescription();
    }

    @Override
    public int cost() {
        return tempExamination.cost();
    }


}
