import java.util.ArrayList;
import java.util.HashMap;

//the compiler that visits each node and creates classes as needed

public class Compiler extends ILPLBaseVisitor<Expression> {

	public static HashMap<String, CompilerObj> globalVals = new HashMap<>();
	public static HashMap<String, FunContainer> funcResolve;
	
	public static HashMap<String, FunctionBlock> allfuns = new HashMap<>();
	private Scoper cScoper = null;

	public Compiler(ScopeContainer cScope, HashMap<String, FunContainer> funcResolve, HashMap<String, TypeContainer> typeColluder) {
		Compiler.funcResolve = funcResolve;
	}
	
	@Override
	public Expression visitVar_declaration(ILPLParser.Var_declarationContext ctx) {
		if (cScoper != null) {
			cScoper.redefinedTypes.add(ctx.IDENT().getText());
		} else {
			globalVals.put(ctx.IDENT().getText(), null);
		}
		visitChildren(ctx);
		return null;
	}
	
	@Override
	public Expression visitFunction_declaration(ILPLParser.Function_declarationContext ctx) {
		FunctionBlock cBlock = new FunctionBlock();
		cScoper = cBlock;
		visitChildren(ctx);
		allfuns.put(ctx.IDENT().getText(), cBlock);
		cScoper = null;
		return null;
	}
	
	@Override 
	public Expression visitASSIGNTAG(ILPLParser.ASSIGNTAGContext ctx) {
		Assignment current = new Assignment();
		cScoper.containedStatements.add(current);
		current.lhs = ctx.IDENT().getText();
		current.rhs = visit(ctx.expression());
		current.parent = cScoper;
		return null;
	}
	
	@Override 
	public Expression visitIFTAG(ILPLParser.IFTAGContext ctx) {
		IfStatement ifBlock = new IfStatement();
		cScoper.containedStatements.add(ifBlock);
		
		CondScope ifScope = new CondScope();
		ifBlock.cs = ifScope;

		ifScope.condExp = visit(ctx.expression());
		ifScope.condExp.parentState = ifBlock;
		ifScope.parent = cScoper;
		
		CondScope elseScope = new CondScope();
		ifBlock.elseScope = elseScope;
		elseScope.parent = cScoper;
		ifBlock.parent = cScoper;
		
		cScoper = ifScope;
		
		//add the body to the scoper
		visit(ctx.bigbody());
		
		//ditto for the else if exists
		cScoper = elseScope;
		visit(ctx.if_split());
		
		cScoper = ifBlock.parent;
		return null;
	}
	
	@Override
	public Expression visitWHILETAG(ILPLParser.WHILETAGContext ctx) {
		WhileStat whileBlock = new WhileStat();
		cScoper.containedStatements.add(whileBlock);
		
		CondScope whileScope = new CondScope();
		whileBlock.cs = whileScope;
		whileScope.condExp = visit(ctx.expression());
		whileScope.condExp.parentState = whileBlock;
		
		whileScope.parent = cScoper;
		whileBlock.parent = cScoper;
		
		cScoper = whileScope;
		visit(ctx.bigbody());
		cScoper = cScoper.parent;
		
		return null;
	}
	
	@Override
	public Expression visitRETURNTAG(ILPLParser.RETURNTAGContext ctx) {
		ReturnStat rs = new ReturnStat();
		rs.parent = cScoper;
		rs.returnExp = visit(ctx.expression());
		rs.returnExp.parentState = rs;
		cScoper.containedStatements.add(rs);
		
		return null;
	}
	
	@Override
	public Expression visitIDENTEXP(ILPLParser.IDENTEXPContext ctx) {
		//basic identifier (aka variable)
		if (ctx.expression2().children == null || ctx.expression2().children.isEmpty()) {
			Identifier var = new Identifier();
			var.ident = ctx.IDENT().getText();
			return var;
		} else {
			//funcall
			FunCall fc = new FunCall();
			fc.funcName = ctx.IDENT().getText();
			
			ArrayList<Expression> fa = new ArrayList<>();
			//get every returned expression
			if (ctx.expression2().expression3().children != null && ctx.expression2().expression3().children.size() == 2) {
				ILPLParser.Expression_listContext elc = ctx.expression2().expression3().expression_list(); 
				fa.add(visit(elc.expression()));
				
				ILPLParser.Expression_list2Context list2 = elc.expression_list2();
				
				while (list2.children != null && list2.children.size() == 3) {
					
					fa.add(visit(list2.expression()));
					list2 = list2.expression_list2();
				}
			}
			
			fc.funcArgs = fa;
			return fc;
		}
	}
	
	@Override
	public Expression visitSTRINGEXP(ILPLParser.STRINGEXPContext ctx) {
		Constant c = new Constant();
		CompilerObj str = new CompilerObj();
		str.isList = false;
		//make it a string with a value
		str.strVal = ctx.getText();
		c.constType = str;
		
		return c;	
	}
	
	@Override
	public Expression visitNILEXP(ILPLParser.NILEXPContext ctx) {
		Constant c = new Constant();
		
		//generate permutable nil type 
		CompilerObj nilT = new CompilerObj();
		nilT.isList = true;
		nilT.nilInst = true;
		nilT.listVal = new ArrayList<>();
		nilT.listVal.add(null);
		c.constType = nilT;
		
		return c;
	}
}












