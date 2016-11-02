import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;

public class Optimiser {
	
	private static HashMap<String, FunctionBlock> allFuns;
	boolean printo;
//	private static HashMap<String, CompilerObj> globalVals;
	
	public Optimiser(HashMap<String, FunctionBlock> allFuns, HashMap<String, CompilerObj> globalVals, boolean doPrint) {
		
		Optimiser.allFuns = allFuns;
		printo = doPrint;
//		Optimiser.globalVals = globalVals;
	}
	
	public void run() {
		//remove redundant functions (ones that are never called)
		cleanFns();
		
		//basically update scopes to indicate whether a CompilerObj is used in the scope or in a subscope
		updateVariableUsage();
		
		//propagate constants and remove dead var instances
		cleanVars();
	}
	
	private void cleanFns() {
		//work list algorithm - simply find every time we find a fun call in a function we can reach
		Set<String> usedFns = new TreeSet<String>();	
		Set<String> reachable = new TreeSet<String>();
		reachable.add("main");
		
		do {
			usedFns.addAll(reachable);
			Set<String> tmpList = new TreeSet<String>();
			for (String s : reachable) {
				tmpList.addAll(allFuns.get(s).findFunCalls());
			}
			tmpList.removeAll(usedFns);
			reachable = tmpList;
		} while (!reachable.isEmpty());
		
		
		ArrayList<String> unused = new ArrayList<>();
		for (String s : allFuns.keySet()) {
			unused.add(s);
		}
		for (String s : usedFns) {
			unused.remove(s);
		}
		
		if (printo) System.out.print("** OPTIMISATION : removing fns: ");
		if (printo && unused.size() == 0) System.out.print("none!");
		for (String s : unused) {
			if (printo) System.out.print(s + " ");
			allFuns.remove(s);
		}
		if (printo) System.out.println(" **");
	}

	private void updateVariableUsage() {
		//create table of variables for each scope indicating whether variables are read/written or both in subscopes
		
	}
	
	private void cleanVars() {
		//for each of the scopes - if there exists variables which are not read from in the defined scope or deeper
			//if the variable is written to, remove the statement that writes to it (as it is redundant execution)
		
		//while no more changes can be made
			//if there exist variables which are assigned constants
				//replace the future reads from that var with the constant (future being until the variable is written to again)
			
		
	}
}
