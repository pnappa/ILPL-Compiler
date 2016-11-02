import java.util.ArrayList;
import java.util.HashMap;


//simply where all the builtin functions live
public class Builtins {
	
	public static ArrayList<String> biNames = new ArrayList<>();
	
	//add the builtins to the environment
	public static void addBuiltins(HashMap<String, FunctionBlock> vals) {
		vals.put("eq", new eq());
		vals.put("neq", new neq());
		vals.put("cons", new cons());
		vals.put("first", new first());
		vals.put("rest", new rest());
		
		biNames.add("eq");
		biNames.add("neq");
		biNames.add("cons");
		biNames.add("first");
		biNames.add("rest");
	}

	public static class eq extends FunctionBlock {
		public CompilerObj run(HashMap<String, CompilerObj> argsIn) {
			CompilerObj rVal = new CompilerObj();
			
			ArrayList<CompilerObj> inputto = new ArrayList<CompilerObj>(argsIn.values());
			
			rVal.retVal = inputto.get(0).isEquivalent(inputto.get(1));
			//System.out.println(rVal.retVal);
			return rVal;
		}
	}
	
	public static class neq extends FunctionBlock {
		public CompilerObj run(HashMap<String, CompilerObj> argsIn) {
			CompilerObj rVal = new CompilerObj();
			ArrayList<CompilerObj> inputto = new ArrayList<CompilerObj>(argsIn.values());

			rVal.retVal = !inputto.get(0).isEquivalent(inputto.get(1));
			return rVal;	
		}
	}
	
	public static class cons extends FunctionBlock {
		public CompilerObj run(HashMap<String, CompilerObj> argsIn) {
			ArrayList<CompilerObj> inputto = new ArrayList<CompilerObj>(argsIn.values());
			
			CompilerObj lhs = inputto.get(0).duplikator();
			CompilerObj rhs = inputto.get(1).duplikator();
			
			rhs.nilInst = lhs.nilInst && rhs.nilInst;
			rhs.listVal.add(0, lhs);

			//weird nil type semantics
			return rhs;
		}
	}
	
	public static class first extends FunctionBlock {
		public CompilerObj run(HashMap<String, CompilerObj> argsIn) {
			ArrayList<CompilerObj> inputto = new ArrayList<CompilerObj>(argsIn.values());

			CompilerObj a = inputto.get(0);

			if (a.nilInst || a.listVal.get(0) == null) {
				System.out.println("Runtime error, called first on nil type");
				System.exit(-1);
			}

			return a.listVal.get(0).duplikator();
		}
	}
	
	public static class rest extends FunctionBlock {
		public CompilerObj run(HashMap<String, CompilerObj> argsIn) {
			ArrayList<CompilerObj> inputto = new ArrayList<CompilerObj>(argsIn.values());

			CompilerObj a = inputto.get(0);

			if (a.nilInst || a.listVal.get(0) == null) {
				System.out.println("Runtime error, called rest on nil type");
				System.exit(-1);
			}

			CompilerObj ret = a.duplikator();
			ret.listVal = new ArrayList<>(ret.listVal.subList(1, ret.listVal.size()));
			//if we call rest and result with a nil type - don't forget to make it an indeterminate
			if ( ret.listVal.get(0) == null || ret.listVal.get(0).nilInst) {
				ret.nilInst = true;
			}

			return ret;
		}
	}
}
