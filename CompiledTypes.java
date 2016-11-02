import java.util.ArrayList;
import java.util.HashMap;

public class CompiledTypes {

	//helper function to return the closest above function
	public static FunctionBlock getAboveFunc(Scoper inScope) {
		Scoper curr = inScope;
		
		while (curr.thisScope != ScopeType.FUNCTION) {
			curr = curr.parent;
		}
		return (FunctionBlock) curr;
	}

	//return the scope obj except all excludes
	public static HashMap<String, CompilerObj> duplicateExclude(HashMap<String, CompilerObj> old, ArrayList<String> excludes) {
		HashMap<String, CompilerObj> ret = new HashMap<>(old);
		for (String exc : excludes) {
			ret.put(exc, null);
		}
		return ret;
	}

	public enum ScopeType {
		FUNCTION, CONDITIONAL
	};

}