
public class WhileStat extends Statement {

	public CondScope cs;

	@Override 
	public CompilerObj run() {

		//set scope frame
		cs.scopeFrame = CompiledTypes.duplicateExclude(cs.parent.scopeFrame, cs.redefinedTypes);

		CompilerObj res = cs.condExp.run(cs.parent);

		while (res.retVal) {
			//iterate and eval all contained statements
			for (Statement stmt : cs.containedStatements) {
				CompilerObj statRes = stmt.run();
				//interrupt if we encounter a return
				if (statRes != null && CompiledTypes.getAboveFunc(cs).isReturning) {
					return statRes;
				}
			}

			res = cs.condExp.run(cs);
		}

		return null;
	}
}
