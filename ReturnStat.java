

public class ReturnStat extends Statement {
	public Expression returnExp;

	@Override 
	public CompilerObj run() {
		//what this needs to do, is simply set a flag for the parent function to return this value

		//indicate that the fn needs to be interrupted
		CompiledTypes.getAboveFunc(this.parent).isReturning = true;

		return returnExp.run(this.parent);
	}
}

