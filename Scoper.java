import java.util.ArrayList;
import java.util.HashMap;

//class to represent environments which have scope 
	public abstract class Scoper {
		//mapping representing the variable's values within this scope
		public HashMap<String, CompilerObj> scopeFrame;
		ArrayList<Statement> containedStatements = new ArrayList<>();

		//variables introduced in this scope
		ArrayList<String> redefinedTypes = new ArrayList<>();

		public Scoper parent;
		public CompiledTypes.ScopeType thisScope;

		private void uninitialisedVarErr(String ident) {
			System.out.println("Access to uninitialised variable " + ident);
			System.exit(-1);
		}

		//get and return a var if its been initialised in the local or global scope
		public CompilerObj getVar(String ident) {
			
			if (!scopeFrame.containsKey(ident)) {
				//check the global
				if (Compiler.globalVals.containsKey(ident)) {

					if (Compiler.globalVals.get(ident) == null) {
						uninitialisedVarErr(ident);
					}

					return Compiler.globalVals.get(ident).duplikator();
				} else {
					uninitialisedVarErr(ident);
				}
			} else {
				if (scopeFrame.get(ident) == null) uninitialisedVarErr(ident);

				return scopeFrame.get(ident).duplikator();
			}
			
			return null;
		}

		//set the value of the variable within the scope (or global if there)
		public void setVar(String ident, CompilerObj value) {
			if (scopeFrame.containsKey(ident)) {
				if (scopeFrame.get(ident) == null) {
					scopeFrame.put(ident, value);
				} else {
					scopeFrame.get(ident).inPlaceSet(value);
				}
			} else {
				if (Compiler.globalVals.get(ident) == null) {
					Compiler.globalVals.put(ident, value);
				} else {
					//check the global
					Compiler.globalVals.get(ident).inPlaceSet(value);
				}
			}
		}
		
		//return list of all functions used in this scope
		public ArrayList<String> findFunCalls() {
			ArrayList<String> retList = new ArrayList<>();
			
			for (Statement x : containedStatements) {
				
				if (x instanceof IfStatement) {
					
					IfStatement y = (IfStatement) x;
					
					//add the condexp if necessary (in if)
					retList.addAll(y.cs.condExp.findFunCalls());
					
					//find the funCalls in if
					retList.addAll(y.cs.findFunCalls());
					
					//find the funCalls in else
					retList.addAll(y.elseScope.findFunCalls());
					
				} else if (x instanceof WhileStat) {
					
					WhileStat y = (WhileStat) x;

					
					//add the condexp if necessary (in while)
					retList.addAll(y.cs.condExp.findFunCalls());
					
					//find the funcalls in while
					retList.addAll(y.cs.findFunCalls());
					
				} else if (x instanceof Assignment) {
					//add the funcalls in this statement
					Assignment y = (Assignment) x;
					
					retList.addAll(y.rhs.findFunCalls());
				} else if (x instanceof ReturnStat) {
					ReturnStat y = (ReturnStat) x;
					
					retList.addAll(y.returnExp.findFunCalls());
				}

			}
			
			return retList;
		}
	}

	