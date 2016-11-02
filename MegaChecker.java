import java.util.ArrayList;
import java.util.HashMap;

public class MegaChecker extends ILPLBaseVisitor<TypeContainer> {
	
	HashMap<String, FunContainer> fnStates;
	ScopeContainer subRoot;
	
	
	String currentFunctionState = null;
	
	public MegaChecker(HashMap<String, FunContainer> fnS, ScopeContainer subRoot) {
		fnStates = fnS;
		this.subRoot = subRoot;
	}
	
	@Override 
	public TypeContainer visitProgram(ILPLParser.ProgramContext ctx) {
		visitChildren(ctx);
		return null;
	};
	
	@Override
	public TypeContainer visitBigbody(ILPLParser.BigbodyContext ctx) { 
		subRoot = subRoot.subScopes.get(subRoot.uniqId++);
		visit(ctx.body());
		
		subRoot = subRoot.parent;
		
		return null;
	}
	
	//return an array list of all the types of the args
	public ArrayList<TypeContainer> scrapeArgs(ILPLParser.Expression_listContext ctx) {
		
		ArrayList<TypeContainer> retList = new ArrayList<TypeContainer>();
		
		retList.add(visit(ctx.expression()));
		
		if (ctx.expression_list2().children != null) {
			retList.addAll(scrapeArgs2(ctx.expression_list2()));
		}
		
		return retList;
	}
	
	//helper for the above fn
	public ArrayList<TypeContainer> scrapeArgs2(ILPLParser.Expression_list2Context ctx) {
		ArrayList<TypeContainer> retList = new ArrayList<>();
		retList.add(visit(ctx.expression()));
		if (ctx.expression_list2().children != null) {
			retList.addAll(scrapeArgs2(ctx.expression_list2().expression_list2()));
		}
		return retList;
	}
	
	@Override
	public TypeContainer visitIDENTEXP(ILPLParser.IDENTEXPContext ctx) {
		if (ctx.expression2().children == null) {
			//we variable bois
			TypeContainer rt = subRoot.get(ctx.IDENT().getText());
			if (rt == null) {
				System.out.println("Variable non existent");
				System.exit(-1);
			}
			return rt;
		}
		
		if (ctx.expression2().expression3().children.size() == 1) {
			//function with no args
			String funcName = ctx.IDENT().getText();
			FunContainer fc = fnStates.get(funcName);
			if (fc == null) {
				System.out.println("Function does not exist.");
				System.exit(-1);
			}
			
			if (fc.argTypes.size() != 0) {
				System.out.println("Incorrect function arity.");
				System.exit(-1);
			}
			
			return fc.retType;
		}
		
		ArrayList<TypeContainer> argIns = scrapeArgs(ctx.expression2().expression3().expression_list());
		
		//if eq
		if (ctx.IDENT().getText().equals("eq")) {
			if (argIns.size() != 2) {
				System.out.println("Incorrect args to equal.");
				System.exit(-1);
			}
			
			String a1Name = "";
			String a2Name = "";
			
			//check if we have something not a funcall/ident?
			int numChild1 = ctx.expression2().expression3().expression_list().expression().children.size();
			if (numChild1 == 2) {
				a1Name = ((ILPLParser.IDENTEXPContext) ctx.expression2().expression3().expression_list().expression()).IDENT().getText();
				
			}
			
			int numChild2 =  ctx.expression2().expression3().expression_list().expression_list2().expression().children.size();
			if (numChild2 == 2) {
				a2Name = ((ILPLParser.IDENTEXPContext) ctx.expression2().expression3().expression_list().expression_list2().expression()).getText();
			}
			
			
			//enforce that eq and neq cannot be inside this fn
			if (a1Name.equals("eq") || a1Name.equals("neq") || a2Name.equals("eq") || a2Name.equals("neq")) {
				System.out.println("Nested eq/neq detected.");
				System.exit(-1);
			}
			
			if (!(typeUp(argIns.get(0), argIns.get(1)) || typeUp(argIns.get(1), argIns.get(0)))) {
				System.out.println("Argument mismatch provided to equal");
				System.exit(-1);
			}
			
			return null;
		}
		
		//if neq
		if (ctx.IDENT().getText().equals("neq")) {
			if (argIns.size() != 2) {
				System.out.println("Incorrect args to not equal.");
				System.exit(-1);
			}
			
			String a1Name = "";
			String a2Name = "";
			
			//what if we have something not a funcall/ident?
			int numChild1 = ctx.expression2().expression3().expression_list().expression().children.size();
			if (numChild1 == 2) {
				a1Name = ((ILPLParser.IDENTEXPContext) ctx.expression2().expression3().expression_list().expression()).IDENT().getText();
			}
			
			int numChild2 =  ctx.expression2().expression3().expression_list().expression_list2().expression().children.size();
			if (numChild2 == 2) {
				a2Name = ((ILPLParser.IDENTEXPContext) ctx.expression2().expression3().expression_list().expression_list2().expression()).getText();
			}
			
			if (a1Name.equals("eq") || a1Name.equals("neq") || a2Name.equals("eq") || a2Name.equals("neq")) {
				System.out.println("Nested eq/neq detected.");
				System.exit(-1);
			}
			
			if (!(typeUp(argIns.get(0), argIns.get(1)) || typeUp(argIns.get(1), argIns.get(0)))) {
				System.out.println("Argument mismatch provided to not equal");
				System.exit(-1);
			}
			
			return null;
		}
		
		//if cons
		if (ctx.IDENT().getText().equals("cons")) {
			if (argIns.size() != 2) {
				System.out.println("Incorrect args to cons");
				System.exit(-1);
			}
			
			//now for cons, we need to check whether the first arg is +1 castable within the second
			TypeContainer lhs = argIns.get(0);
			TypeContainer rhs = argIns.get(1);
			
			if (rhs.isUnknown && lhs.isUnknown) {
				//simply upcast left or right depending on whichever's smaller
				if (lhs.numLists + 1 < rhs.numLists) {
					//upcast left
					return rhs;
				} else if (lhs.numLists + 1 >= rhs.numLists) {
					//upcast right
					rhs.numLists = lhs.numLists + 1;
					return rhs;
				}
			} else if (rhs.isUnknown) {
				//we can cast the rhs up if need be
				if (lhs.numLists + 1 >= rhs.numLists) {
					rhs.numLists = lhs.numLists + 1;
					return rhs;
				} else {
					//err
					System.out.println("Invalid type mismatch in cons (lhs >= rhs)");
					System.exit(-1);
				}
			} else if (lhs.isUnknown) {
				//we can cast the lhs if need be? and if we can??
				if (lhs.numLists + 1 <= rhs.numLists) {
					return rhs;
				} else {
					System.out.println("Invalid type mismatch in cons (lhs >= rhs");
					System.exit(-1);
				}
			} else {
				//we've gotta make sure that the first is exactly one less arity than the second
				if (rhs.numLists == lhs.numLists+1) return rhs; 
				
				System.out.println("Invalid type mismatch - concrete types in cons invalid arity");
				System.exit(-1);
			}
		}
		
		//if first
		if (ctx.IDENT().getText().equals("first")) {
			if (argIns.size() != 1) {
				System.out.println("Invalid arity to first.");
				System.exit(-1);
			}
			
			TypeContainer x = new TypeContainer(argIns.get(0).numLists, argIns.get(0).baseType, argIns.get(0).isUnknown);			
		
			if (x.isUnknown) { 
				//if unknown we've got to make sure we don't dip down into negatives
				if (x.numLists <= 0) x.numLists = 1;
				
				x.numLists--;
				
				return x;
			} else {
				if (x.numLists <= 0) {
					System.out.println("Cannot `first` an unnested type");
					System.exit(-1);
				}
				x.numLists--;
				return x;
			}
		}
		
		//if rest
		if (ctx.IDENT().getText().equals("rest")) {
			if (argIns.size() != 1) {
				System.out.println("Invalid arity to rest.");
				System.exit(-1);
			}
			
			//must make sure that the thing returned by rest has at least 1 arity
			TypeContainer ret = argIns.get(0);
			
			if (ret.isUnknown) {
				if (ret.numLists <= 0) ret.numLists = 1;
				
				return ret;
			} else {
				if (ret.numLists == 0) {
					System.out.println("Cannot rest an unnested type");
					System.exit(-1);
				}
				return ret;
			}
			
		}
		
		//if user defined function
		String funcName = ctx.IDENT().getText();
		FunContainer fc = fnStates.get(funcName);
		
		if (fc == null) {
			System.out.println("Invalid function provided:" + funcName + ".");
			System.exit(-1);
		}
		
		if (fc.argTypes.size() != argIns.size()) {
			System.out.println("Incorrect args provided to " + funcName + " should be " + fc.argTypes.size() + "but was " + argIns.size());
			System.exit(-1);
		}
		
		//check type match for all types
		for (int i = 0; i < argIns.size(); ++i) {
			assertTypeUp(fc.argTypes.get(i), argIns.get(i), "function:" + funcName);
		}
		
		return fc.retType;
	}

	@Override
	public TypeContainer visitSTRINGEXP(ILPLParser.STRINGEXPContext ctx) {
		return new TypeContainer(0, "string");
	}
	
	@Override
	public TypeContainer visitNILEXP(ILPLParser.NILEXPContext ctx) {
		return new TypeContainer(1, null, true);
	}
	
	@Override 
	public TypeContainer visitFunction_declaration(ILPLParser.Function_declarationContext ctx) {
		//simply change the current Function state
		currentFunctionState = ctx.IDENT().getText();
		
		return visitChildren(ctx);
	}
	
	@Override
	public TypeContainer visitIFTAG(ILPLParser.IFTAGContext ctx) {
		
		if (ctx.expression().children.size() != 2) {
			System.out.println("Supplied non-eq/neq function to if statment: " + ctx.expression().getText());
			System.exit(-1);
		}
		
		String fnName = ((ILPLParser.IDENTEXPContext) ctx.expression()).IDENT().getText();
		
		if (!fnName.equals("eq") && !fnName.equals("neq")) {
			System.out.println("Supplied non-boolean statement to if statement: " + fnName);
			System.exit(-1);
		}
		
		visitChildren(ctx);
		
		return null;
	}
	
	@Override
	public TypeContainer visitWHILETAG(ILPLParser.WHILETAGContext ctx) {
		
		if (ctx.expression().children.size() != 2) {
			System.out.println("Supplied non-eq/neq function to if statment: " + ctx.expression().getText());
			System.exit(-1);
		}
		
		String fnName = ((ILPLParser.IDENTEXPContext) ctx.expression()).IDENT().getText();
		
		if (!fnName.equals("eq") && !fnName.equals("neq")) {
			System.out.println("Supplied non-boolean statement to if statement: " + fnName);
			System.exit(-1);
		}
		
		visitChildren(ctx);
		
		return null;
	}
	
	public void assertTypeUp(TypeContainer concreteT, TypeContainer virtualT, String descriptor) {
		//make sure that the virtual is either equal to concrete, or upcastable
		if (!typeUp(concreteT, virtualT)) {
			System.out.println("Type mismatch for " + descriptor);
			System.exit(-1);
		}
	}
	
	//check whether we can upcast from fromType -> toType
	public boolean typeUp(TypeContainer toType, TypeContainer fromType) {
		return (toType.numLists == fromType.numLists && !fromType.isUnknown) || (fromType.isUnknown && toType.numLists >= fromType.numLists);
	}
	
	@Override
	public TypeContainer visitRETURNTAG(ILPLParser.RETURNTAGContext ctx) {
		
		TypeContainer requestedReturn = fnStates.get(currentFunctionState).retType;
		TypeContainer actualReturn = visit(ctx.expression());
		
		//if we can upcast or if the expression is the same type
		assertTypeUp(requestedReturn, actualReturn, "return");
		
		return null;
	}
	
	@Override
	public TypeContainer visitASSIGNTAG(ILPLParser.ASSIGNTAGContext ctx) {
		
		TypeContainer lhs = subRoot.get(ctx.IDENT().getText());
		TypeContainer rhs = visit(ctx.expression());
		
		//if we can upcast or expression is same type
		assertTypeUp(lhs, rhs, "assignment");
		
		return null;
	}
	
	
}
