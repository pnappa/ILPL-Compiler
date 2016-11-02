// Generated from ILPL.g by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link ILPLParser}.
 */
public interface ILPLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link ILPLParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(ILPLParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(ILPLParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(ILPLParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(ILPLParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void enterType_declaration(ILPLParser.Type_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#type_declaration}.
	 * @param ctx the parse tree
	 */
	void exitType_declaration(ILPLParser.Type_declarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code BasicIdentify}
	 * labeled alternative in {@link ILPLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterBasicIdentify(ILPLParser.BasicIdentifyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code BasicIdentify}
	 * labeled alternative in {@link ILPLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitBasicIdentify(ILPLParser.BasicIdentifyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RecursiveIdentify}
	 * labeled alternative in {@link ILPLParser#type}.
	 * @param ctx the parse tree
	 */
	void enterRecursiveIdentify(ILPLParser.RecursiveIdentifyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RecursiveIdentify}
	 * labeled alternative in {@link ILPLParser#type}.
	 * @param ctx the parse tree
	 */
	void exitRecursiveIdentify(ILPLParser.RecursiveIdentifyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void enterVar_declaration(ILPLParser.Var_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#var_declaration}.
	 * @param ctx the parse tree
	 */
	void exitVar_declaration(ILPLParser.Var_declarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void enterFunction_declaration(ILPLParser.Function_declarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#function_declaration}.
	 * @param ctx the parse tree
	 */
	void exitFunction_declaration(ILPLParser.Function_declarationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ArgFun}
	 * labeled alternative in {@link ILPLParser#function_declaration2}.
	 * @param ctx the parse tree
	 */
	void enterArgFun(ILPLParser.ArgFunContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ArgFun}
	 * labeled alternative in {@link ILPLParser#function_declaration2}.
	 * @param ctx the parse tree
	 */
	void exitArgFun(ILPLParser.ArgFunContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NoArgFun}
	 * labeled alternative in {@link ILPLParser#function_declaration2}.
	 * @param ctx the parse tree
	 */
	void enterNoArgFun(ILPLParser.NoArgFunContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NoArgFun}
	 * labeled alternative in {@link ILPLParser#function_declaration2}.
	 * @param ctx the parse tree
	 */
	void exitNoArgFun(ILPLParser.NoArgFunContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void enterArgument_list(ILPLParser.Argument_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#argument_list}.
	 * @param ctx the parse tree
	 */
	void exitArgument_list(ILPLParser.Argument_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#argument_list2}.
	 * @param ctx the parse tree
	 */
	void enterArgument_list2(ILPLParser.Argument_list2Context ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#argument_list2}.
	 * @param ctx the parse tree
	 */
	void exitArgument_list2(ILPLParser.Argument_list2Context ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#argument}.
	 * @param ctx the parse tree
	 */
	void enterArgument(ILPLParser.ArgumentContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#argument}.
	 * @param ctx the parse tree
	 */
	void exitArgument(ILPLParser.ArgumentContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#bigbody}.
	 * @param ctx the parse tree
	 */
	void enterBigbody(ILPLParser.BigbodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#bigbody}.
	 * @param ctx the parse tree
	 */
	void exitBigbody(ILPLParser.BigbodyContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#body}.
	 * @param ctx the parse tree
	 */
	void enterBody(ILPLParser.BodyContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#body}.
	 * @param ctx the parse tree
	 */
	void exitBody(ILPLParser.BodyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ASSIGNTAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterASSIGNTAG(ILPLParser.ASSIGNTAGContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ASSIGNTAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitASSIGNTAG(ILPLParser.ASSIGNTAGContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IFTAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterIFTAG(ILPLParser.IFTAGContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IFTAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitIFTAG(ILPLParser.IFTAGContext ctx);
	/**
	 * Enter a parse tree produced by the {@code WHILETAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterWHILETAG(ILPLParser.WHILETAGContext ctx);
	/**
	 * Exit a parse tree produced by the {@code WHILETAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitWHILETAG(ILPLParser.WHILETAGContext ctx);
	/**
	 * Enter a parse tree produced by the {@code RETURNTAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterRETURNTAG(ILPLParser.RETURNTAGContext ctx);
	/**
	 * Exit a parse tree produced by the {@code RETURNTAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitRETURNTAG(ILPLParser.RETURNTAGContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ELSEIF}
	 * labeled alternative in {@link ILPLParser#if_split}.
	 * @param ctx the parse tree
	 */
	void enterELSEIF(ILPLParser.ELSEIFContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ELSEIF}
	 * labeled alternative in {@link ILPLParser#if_split}.
	 * @param ctx the parse tree
	 */
	void exitELSEIF(ILPLParser.ELSEIFContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ENDIF}
	 * labeled alternative in {@link ILPLParser#if_split}.
	 * @param ctx the parse tree
	 */
	void enterENDIF(ILPLParser.ENDIFContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ENDIF}
	 * labeled alternative in {@link ILPLParser#if_split}.
	 * @param ctx the parse tree
	 */
	void exitENDIF(ILPLParser.ENDIFContext ctx);
	/**
	 * Enter a parse tree produced by the {@code IDENTEXP}
	 * labeled alternative in {@link ILPLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterIDENTEXP(ILPLParser.IDENTEXPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code IDENTEXP}
	 * labeled alternative in {@link ILPLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitIDENTEXP(ILPLParser.IDENTEXPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code STRINGEXP}
	 * labeled alternative in {@link ILPLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterSTRINGEXP(ILPLParser.STRINGEXPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code STRINGEXP}
	 * labeled alternative in {@link ILPLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitSTRINGEXP(ILPLParser.STRINGEXPContext ctx);
	/**
	 * Enter a parse tree produced by the {@code NILEXP}
	 * labeled alternative in {@link ILPLParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNILEXP(ILPLParser.NILEXPContext ctx);
	/**
	 * Exit a parse tree produced by the {@code NILEXP}
	 * labeled alternative in {@link ILPLParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNILEXP(ILPLParser.NILEXPContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#expression2}.
	 * @param ctx the parse tree
	 */
	void enterExpression2(ILPLParser.Expression2Context ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#expression2}.
	 * @param ctx the parse tree
	 */
	void exitExpression2(ILPLParser.Expression2Context ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#expression3}.
	 * @param ctx the parse tree
	 */
	void enterExpression3(ILPLParser.Expression3Context ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#expression3}.
	 * @param ctx the parse tree
	 */
	void exitExpression3(ILPLParser.Expression3Context ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list(ILPLParser.Expression_listContext ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#expression_list}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list(ILPLParser.Expression_listContext ctx);
	/**
	 * Enter a parse tree produced by {@link ILPLParser#expression_list2}.
	 * @param ctx the parse tree
	 */
	void enterExpression_list2(ILPLParser.Expression_list2Context ctx);
	/**
	 * Exit a parse tree produced by {@link ILPLParser#expression_list2}.
	 * @param ctx the parse tree
	 */
	void exitExpression_list2(ILPLParser.Expression_list2Context ctx);
}