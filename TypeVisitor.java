

import java.util.HashMap;

public class TypeVisitor extends ILPLBaseVisitor<TypeContainer> {

	HashMap<String, TypeContainer> foundTypes = new HashMap<String, TypeContainer>();
	
	@Override
	public TypeContainer visitType_declaration(ILPLParser.Type_declarationContext ctx) {
		
		TypeContainer thisType = visit(ctx.type());
		String typeName = ctx.IDENT().getText();
		
		if (foundTypes.containsKey(typeName)) {
			System.out.println("Existing type for " + typeName + " already exists as " + (foundTypes.get(typeName)).getConcreteType());
			System.exit(-1);
		}
		
		foundTypes.put(typeName, thisType);
		
		return thisType;
	}

	@Override
	public TypeContainer visitBasicIdentify(ILPLParser.BasicIdentifyContext ctx) {
		
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
