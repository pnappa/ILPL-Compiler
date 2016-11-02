import java.util.ArrayList;
import java.util.HashMap;

//evaluate function call and return value
public class FunCall extends Expression {
	public String funcName;
	public ArrayList<Expression> funcArgs;

	@Override 
	public CompilerObj run(Scoper cScope) {

		//evaluate all args
		ArrayList<CompilerObj> inputVals = new ArrayList<>();
		for (Expression e : funcArgs) {
			inputVals.add(e.run(cScope));
		}

		//create a scope for the function consisting of the args only
		HashMap<String, CompilerObj> newScope = new HashMap<>();
		//we need to generate unique names for the hashmap for the args (starting with numbers, because invalid variable prefix
		String biNameGen = "1";
		for (int i = 0; i < inputVals.size(); ++i) {
			if (Builtins.biNames.contains(funcName)) {
				newScope.put(biNameGen, inputVals.get(i).duplikator());
				biNameGen = biNameGen + "_";
			} else {
				newScope.put(Compiler.funcResolve.get(funcName).argNames.get(i), inputVals.get(i).duplikator());
			}
		}

		//find, run, and return function's ret
		return Compiler.allfuns.get(funcName).run(newScope);
	}

	@Override
	public ArrayList<String> findFunCalls() {
		
		ArrayList<String> retVal = new ArrayList<String>();
		
		retVal.add(funcName);
		
		for (Expression e : funcArgs) {
			retVal.addAll(e.findFunCalls());
		}
		
		return retVal;
	}
}
