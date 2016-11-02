import java.util.HashMap;


public class VarVisitor extends ILPLBaseVisitor<TypeContainer> {

	ScopeContainer currScope = new ScopeContainer();
	
	HashMap<String, TypeContainer> typeColluder;
	HashMap<String, FunContainer> fnSigs;
	
	//generating the compiled program
	
	
	boolean argfunflag = false;
	
	public VarVisitor(HashMap<String, TypeContainer> allTypes, HashMap<String, FunContainer> fnSignatures) {
		typeColluder = allTypes;
		fnSigs = fnSignatures;
	}

	@Override
	public TypeContainer visitVar_declaration(ILPLParser.Var_declarationContext ctx) {
		
		String varName = ctx.IDENT().getText();
		
		//conflicting names
		if (typeColluder.containsKey(varName)) {
			System.out.println("Variable name in use by type: " + varName + ".");
			System.exit(-1);
		}
		
		if (fnSigs.containsKey(varName)) {
			System.out.println("Variable name in use by type: " + varName + ".");
			System.exit(-1);
		}
		
		
		//redefinition of types
		if (currScope.variableTypes.containsKey(varName)) {
			System.out.println("Conflicting variable types within scope: " + varName);
			System.exit(-1);
		}
		
		String typeName = ctx.type().getText();
		
		//invalid types
		if (!this.typeColluder.containsKey(typeName)) {
			TypeContainer varsTrueType = visit(ctx.type());
			
			if ((varsTrueType.baseType != null)) {
				if (!typeColluder.containsKey(varsTrueType.baseType)) {
					System.out.println("Non-existing type referenced: " + typeName);
					System.exit(-1);		
				}
			}
			
			currScope.variableTypes.put(varName, varsTrueType);
			
		} else {
			currScope.variableTypes.put(varName, this.typeColluder.get(typeName));
		}
		
		return null;		
	}
	
	@Override
	public TypeContainer visitBasicIdentify(ILPLParser.BasicIdentifyContext ctx) {
		
		String ident = ctx.IDENT().getText();
		
		if (typeColluder.containsKey(ident)) {
			return typeColluder.get(ident);
		}
		
		TypeContainer retType = new TypeContainer(0, ctx.IDENT().getText());
		
		return retType;
	}
	
	@Override
	public TypeContainer visitRecursiveIdentify(ILPLParser.RecursiveIdentifyContext ctx) {
		//get the TypeContainer that's contained within this list
		TypeContainer internalListType = visit(ctx.type());
		//now it's obviously surrounded by another list!
		internalListType.numLists += 1;
		
		return internalListType;
	}

	@Override
	public TypeContainer visitArgFun(ILPLParser.ArgFunContext ctx) {
		
		argfunflag = true;
		
		//add the argument list and wrapper body to a scope
		visit(ctx.bigbody());
		visit(ctx.argument_list());
		
		argfunflag = false;
		
		return null;
	}
	
	@Override
	public TypeContainer visitArgument_list(ILPLParser.Argument_listContext ctx) {
		
		if (argfunflag) {
			
			//set the scope to the most recently added block scope
			currScope = currScope.subScopes.get(currScope.subScopes.size() - 1);
			
			visit(ctx.argument());
			visit(ctx.argument_list2());
			
			currScope = currScope.parent;
			
		}
		return null;
	}
	
	@Override
	public TypeContainer visitArgument(ILPLParser.ArgumentContext ctx) {
		
		String varName = ctx.IDENT().getText();
		String typeName = ctx.IDENT().getText();
		
		//check if the var has already been defined in the scope
		if (currScope.variableTypes.containsKey(varName)) {
			System.out.println("Type clash: " + varName + " already exists.");
			System.exit(-1);
		}
		
		//invalid types
		if (!this.typeColluder.containsKey(typeName)) {
			TypeContainer varsTrueType = visit(ctx.type());
			
			if ((varsTrueType.baseType != null)) {
				if (!typeColluder.containsKey(varsTrueType.baseType)) {
					System.out.println("Non-existing type referenced: " + typeName);
					System.exit(-1);		
				}
			}
			
			currScope.variableTypes.put(varName, varsTrueType);
			
		} else {
			currScope.variableTypes.put(varName, this.typeColluder.get(typeName));
		}
		
		return null;
	}
	
	@Override
	public TypeContainer visitBigbody(ILPLParser.BigbodyContext ctx) {
		ScopeContainer newBlock = new ScopeContainer();
		currScope.subScopes.add(newBlock);
		newBlock.parent = currScope;
		currScope = newBlock;
		
		visit(ctx.body());
		
		currScope = currScope.parent;
		
		return null;
	}	
}
