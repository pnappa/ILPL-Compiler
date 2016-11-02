
public class IfStatement extends Statement {
	//the statements to be run if the conditional fails
	//public ArrayList<Statement> elseStatements = new ArrayList<>(); 
	public CondScope cs;
	//simply a scope for the else if
	public CondScope elseScope;

	@Override 
	public CompilerObj run() {

		//set scope frame
		cs.scopeFrame = CompiledTypes.duplicateExclude(cs.parent.scopeFrame, cs.redefinedTypes);
		elseScope.scopeFrame = CompiledTypes.duplicateExclude(cs.parent.scopeFrame, cs.redefinedTypes);

		CompilerObj res = cs.condExp.run(cs.parent);
		if (res.retVal) {
			for (Statement stmt : cs.containedStatements) {
				CompilerObj statres = stmt.run();
				if (statres != null && CompiledTypes.getAboveFunc(cs).isReturning) {
					return statres;
				}
			}
		} else if (elseScope != null && !elseScope.containedStatements.isEmpty()) {
			for (Statement stmt : elseScope.containedStatements) {
				CompilerObj statres = stmt.run();
				if (statres != null && CompiledTypes.getAboveFunc(elseScope).isReturning) {
					return statres;
				}
			}
		}
		return null;
	}
}