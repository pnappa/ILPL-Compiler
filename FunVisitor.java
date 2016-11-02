import java.util.ArrayList;
import java.util.HashMap;

public class FunVisitor extends ILPLBaseVisitor<TypeContainer>{

	public HashMap<String, FunContainer> retTypes = new HashMap<String, FunContainer>();
	private HashMap<String, TypeContainer> typeColluder;	
	
	ArrayList<TypeContainer> args;
	ArrayList<String> argNames;
	
	public FunVisitor(HashMap<String, TypeContainer> inTypes) {
		this.typeColluder = inTypes;
	}
	
	
	@Override
	public TypeContainer visitFunction_declaration(ILPLParser.Function_declarationContext ctx) {
		//generate all the function names and respective data types
		
		String fnName = ctx.IDENT().getText();
		
		if (typeColluder.containsKey(fnName)) {
			System.out.println("Function name clashes with type: " + fnName +".");
			System.exit(-1);
		}
		
		if (retTypes.containsKey(fnName)) {
			System.out.println("Function name " + fnName + " already exists.");
			System.exit(-1);
		}
		
		args = new ArrayList<TypeContainer>();
		argNames = new ArrayList<String>();
		FunContainer embedded = new FunContainer();
		
		TypeContainer fnRetType = visit(ctx.function_declaration2());

		embedded.argTypes = args;
		embedded.argNames = argNames;
		embedded.retType = fnRetType;

		retTypes.put(fnName, embedded);
		
		return null;
	}
	
	@Override 
	public TypeContainer visitArgument(ILPLParser.ArgumentContext ctx) {
		args.add(visit(ctx.type()));
		argNames.add(ctx.IDENT().getText());
		return null;
	}
	
	@Override
	public TypeContainer visitArgFun(ILPLParser.ArgFunContext ctx) {
		visit(ctx.argument_list());
		
		if (typeColluder.containsKey(ctx.type().getText())) {
			return typeColluder.get(ctx.type().getText());
		}
		
		return visit(ctx.type());
	}
	
	@Override
	public TypeContainer visitNoArgFun(ILPLParser.NoArgFunContext ctx) {
		return visit(ctx.type());
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
	
}
