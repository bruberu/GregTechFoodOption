package gregtechfoodoption.recipe;

public interface IExpandableRecipeMap {
    public void setMinInputs(int newMinInputs);

    public void setMinOutputs(int newMinOutputs);

    public void setMinFluidInputs(int newMinFluidInputs);

    public void setMinFluidOutputs(int newMinFluidOutputs);

    public void setMaxInputs(int newMaxInputs);

    public void setMaxOutputs(int newMaxOutputs);

    public void setMaxFluidInputs(int newMaxFluidInputs);

    public void setMaxFluidOutputs(int newMaxFluidOutputs);
}
