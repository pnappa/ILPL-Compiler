import java.util.HashMap;

public class FunctionBlock extends Scoper {

	public boolean isReturning = false;
	public FunctionBlock() {
		this.thisScope = CompiledTypes.ScopeType.FUNCTION;
	}

	public CompilerObj run(HashMap<String, CompilerObj> argsIn) {

		//make the scope contain only argsIn
		this.scopeFrame = new HashMap<>(argsIn);
		//then add null values for redefined types
		for (String vars : this.redefinedTypes) {
			this.scopeFrame.put(vars, null);
		}

		//iterate over all contained Statements, then return if return flag set
		//if we iterate over everything, and no return statements are hit -> runtime error
		for (Statement stmt : this.containedStatements) {
			CompilerObj res = stmt.run();
			if (res != null && this.isReturning) {
				return res;
			}
		}

		System.out.println("Runtime error: reached end of function without return.");
		System.exit(-1);
		return null;
	}
}
