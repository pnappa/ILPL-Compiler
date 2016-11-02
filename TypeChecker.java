

import java.io.IOException;
import java.util.HashMap;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

public class TypeChecker {
	
	private static boolean debug = false;
	
	public static void main(String[] args) throws IOException {
		
		boolean printComp = false;
		if (args.length > 0) {
			if (args[0].equals("-opt")) {
				printComp = true;
			} else {
				System.out.println("Invalid arguments (only -opt is accepted [print optimisations], stdin is program input).");
				System.exit(-1);
			}
		}
		
		//setup antlr - like parser, inputstream, etc
		ILPLLexer lexer = new ILPLLexer(new ANTLRInputStream(System.in));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		ILPLParser ps = new ILPLParser(tokens);
		ParseTree parseTree = ps.program();
		if (ps.getNumberOfSyntaxErrors() > 0) {
			System.out.println("Syntax error. Aborting");
			System.exit(-1);
		}
		
		//visit all the types in order to generate the reference type containers
		TypeVisitor tv = new TypeVisitor();
		tv.visit(parseTree);
		
		//grab the type containers and resolve them (finding circular references)
		resolveTypes(tv.foundTypes);

		//grok all the fun arg types and return type 
		FunVisitor fv = new FunVisitor(tv.foundTypes);
		fv.visit(parseTree);
		
		//form scope tree and check var collisions
		VarVisitor sv = new VarVisitor(tv.foundTypes, fv.retTypes);
		sv.visit(parseTree);
		ScopeContainer globalScope = sv.currScope;
		
		//check all the types unify correctly
		MegaChecker mc = new MegaChecker(fv.retTypes, globalScope);
		mc.visit(parseTree);	
		
		//run the compiler to generate the class hierarchy of all the function stuff
		Compiler comp = new Compiler(globalScope, fv.retTypes, tv.foundTypes);
		comp.visit(parseTree);
		
		if (!Compiler.allfuns.keySet().contains("main")) {
			System.out.println("Missing main function.");
			System.exit(-1);
		}

		//add the builtins to the function env
		Builtins.addBuiltins(Compiler.allfuns);
		
		//optimise out redundancies
		Optimiser opt = new Optimiser(Compiler.allfuns, Compiler.globalVals, printComp);
		opt.run();

		//run the resulting program (also allow some args to be pushed in)
		HashMap<String, CompilerObj> mainArgs = new HashMap<>();
		
//		//example input for program
//		/*
//		 * fun main(name : string) : list(string)
//		 * return cons('welcome to my program', cons(name, nil));
//		 * endfun
//		 */
//		
//		CompilerObj name = new CompilerObj();
//		name.isList = false;
//		name.nilInst = false;
//		name.strVal = "'patrick'";
//		mainArgs.put("name", name);
		
		if (mainArgs.size() != fv.retTypes.get("main").argNames.size()) {
			System.out.println("Incorrect args supplied to main.");
			System.exit(-1);
		}
		
		CompilerObj retVal = Compiler.allfuns.get("main").run(mainArgs);
		if (debug) System.out.println("Result:");
		printObj(retVal);
		System.out.println("");
	}
	
	private static void printObj(CompilerObj val) {

		if (val.nilInst) {
			System.out.print("nil");
			return;
		}
		
		if (val.isList) {
			System.out.print("(");
				
			for (CompilerObj x : val.listVal) {
				if (x == null || x.nilInst) break;
				printObj(x);
				System.out.print(" ");
			}
			
			System.out.print(")");
		} else {
			System.out.print(val.strVal);
		}
	}
	
	private static void resolveTypes(HashMap<String, TypeContainer> intypes) {
		
		//assign each instance in the intypes map with a concrete type and numLists
		
		boolean madeChanges = true;
		while (madeChanges) {
			madeChanges = false;
			
			for (String stc : intypes.keySet()) {
				
				TypeContainer tc = intypes.get(stc);
				
				//we need to resolve this type
				if (tc.baseType != null) {
					TypeContainer pointedType = intypes.get(tc.baseType);
					if (pointedType == null) {
						//error, the identifier this type points to doesn't exist
						System.out.println("Error: dangling type: " + stc);
						System.exit(-1);
					}
					
					//if the one we're pointing to is concrete
					if (pointedType.baseType == null) {
						intypes.get(stc).baseType = null;
						intypes.get(stc).numLists += pointedType.numLists;
						madeChanges = true;
					}
				}
			}
		}
		
		//now need to check that everything has been resolved to a concrete type
		for (String stc : intypes.keySet()) {
			if (intypes.get(stc).baseType != null) {
				System.out.println("Circular type inference detected: " + stc);
				System.exit(-1);
			}
		}
	}
}
