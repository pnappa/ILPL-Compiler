// Generated from ILPL.g by ANTLR 4.5.3
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link ILPLParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface ILPLVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link ILPLParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(ILPLParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDeclaration(ILPLParser.DeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#type_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType_declaration(ILPLParser.Type_declarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code BasicIdentify}
	 * labeled alternative in {@link ILPLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicIdentify(ILPLParser.BasicIdentifyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RecursiveIdentify}
	 * labeled alternative in {@link ILPLParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRecursiveIdentify(ILPLParser.RecursiveIdentifyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#var_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVar_declaration(ILPLParser.Var_declarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#function_declaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunction_declaration(ILPLParser.Function_declarationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ArgFun}
	 * labeled alternative in {@link ILPLParser#function_declaration2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgFun(ILPLParser.ArgFunContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NoArgFun}
	 * labeled alternative in {@link ILPLParser#function_declaration2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoArgFun(ILPLParser.NoArgFunContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#argument_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument_list(ILPLParser.Argument_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#argument_list2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument_list2(ILPLParser.Argument_list2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#argument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArgument(ILPLParser.ArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#bigbody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBigbody(ILPLParser.BigbodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#body}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBody(ILPLParser.BodyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ASSIGNTAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitASSIGNTAG(ILPLParser.ASSIGNTAGContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IFTAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIFTAG(ILPLParser.IFTAGContext ctx);
	/**
	 * Visit a parse tree produced by the {@code WHILETAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWHILETAG(ILPLParser.WHILETAGContext ctx);
	/**
	 * Visit a parse tree produced by the {@code RETURNTAG}
	 * labeled alternative in {@link ILPLParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRETURNTAG(ILPLParser.RETURNTAGContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ELSEIF}
	 * labeled alternative in {@link ILPLParser#if_split}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitELSEIF(ILPLParser.ELSEIFContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ENDIF}
	 * labeled alternative in {@link ILPLParser#if_split}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitENDIF(ILPLParser.ENDIFContext ctx);
	/**
	 * Visit a parse tree produced by the {@code IDENTEXP}
	 * labeled alternative in {@link ILPLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIDENTEXP(ILPLParser.IDENTEXPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code STRINGEXP}
	 * labeled alternative in {@link ILPLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSTRINGEXP(ILPLParser.STRINGEXPContext ctx);
	/**
	 * Visit a parse tree produced by the {@code NILEXP}
	 * labeled alternative in {@link ILPLParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNILEXP(ILPLParser.NILEXPContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#expression2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression2(ILPLParser.Expression2Context ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#expression3}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression3(ILPLParser.Expression3Context ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#expression_list}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list(ILPLParser.Expression_listContext ctx);
	/**
	 * Visit a parse tree produced by {@link ILPLParser#expression_list2}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression_list2(ILPLParser.Expression_list2Context ctx);
}