// Generated from ILPL.g by ANTLR 4.5.3
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ILPLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, STRING=7, QUOTE=8, COMMENT=9, 
		TYPE=10, LIST=11, VAR=12, FUN=13, ENDFUN=14, WHILE=15, DO=16, ENDWHILE=17, 
		IF=18, THEN=19, ELSE=20, ENDIF=21, RETURN=22, NIL=23, IDENT=24, WS=25;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "STRING", "QUOTE", "COMMENT", 
		"TYPE", "LIST", "VAR", "FUN", "ENDFUN", "WHILE", "DO", "ENDWHILE", "IF", 
		"THEN", "ELSE", "ENDIF", "RETURN", "NIL", "IDENT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "';'", "'('", "')'", "','", "'='", null, "'''", null, "'type'", 
		"'list'", "'var'", "'fun'", "'endfun'", "'while'", "'do'", "'endwhile'", 
		"'if'", "'then'", "'else'", "'endif'", "'return'", "'nil'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, "STRING", "QUOTE", "COMMENT", 
		"TYPE", "LIST", "VAR", "FUN", "ENDFUN", "WHILE", "DO", "ENDWHILE", "IF", 
		"THEN", "ELSE", "ENDIF", "RETURN", "NIL", "IDENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ILPLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ILPL.g"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\33\u00ab\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3"+
		"\b\7\bD\n\b\f\b\16\bG\13\b\3\b\3\b\3\t\3\t\3\n\3\n\7\nO\n\n\f\n\16\nR"+
		"\13\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3"+
		"\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\7\31\u00a3\n\31\f\31\16\31\u00a6"+
		"\13\31\3\32\3\32\3\32\3\32\4EP\2\33\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n"+
		"\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\3\2\5\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\u00ad"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\3\65\3\2\2\2\5\67\3\2\2\2\79\3\2\2\2\t;\3"+
		"\2\2\2\13=\3\2\2\2\r?\3\2\2\2\17A\3\2\2\2\21J\3\2\2\2\23L\3\2\2\2\25W"+
		"\3\2\2\2\27\\\3\2\2\2\31a\3\2\2\2\33e\3\2\2\2\35i\3\2\2\2\37p\3\2\2\2"+
		"!v\3\2\2\2#y\3\2\2\2%\u0082\3\2\2\2\'\u0085\3\2\2\2)\u008a\3\2\2\2+\u008f"+
		"\3\2\2\2-\u0095\3\2\2\2/\u009c\3\2\2\2\61\u00a0\3\2\2\2\63\u00a7\3\2\2"+
		"\2\65\66\7<\2\2\66\4\3\2\2\2\678\7=\2\28\6\3\2\2\29:\7*\2\2:\b\3\2\2\2"+
		";<\7+\2\2<\n\3\2\2\2=>\7.\2\2>\f\3\2\2\2?@\7?\2\2@\16\3\2\2\2AE\5\21\t"+
		"\2BD\13\2\2\2CB\3\2\2\2DG\3\2\2\2EF\3\2\2\2EC\3\2\2\2FH\3\2\2\2GE\3\2"+
		"\2\2HI\5\21\t\2I\20\3\2\2\2JK\7)\2\2K\22\3\2\2\2LP\7%\2\2MO\13\2\2\2N"+
		"M\3\2\2\2OR\3\2\2\2PQ\3\2\2\2PN\3\2\2\2QS\3\2\2\2RP\3\2\2\2ST\7\f\2\2"+
		"TU\3\2\2\2UV\b\n\2\2V\24\3\2\2\2WX\7v\2\2XY\7{\2\2YZ\7r\2\2Z[\7g\2\2["+
		"\26\3\2\2\2\\]\7n\2\2]^\7k\2\2^_\7u\2\2_`\7v\2\2`\30\3\2\2\2ab\7x\2\2"+
		"bc\7c\2\2cd\7t\2\2d\32\3\2\2\2ef\7h\2\2fg\7w\2\2gh\7p\2\2h\34\3\2\2\2"+
		"ij\7g\2\2jk\7p\2\2kl\7f\2\2lm\7h\2\2mn\7w\2\2no\7p\2\2o\36\3\2\2\2pq\7"+
		"y\2\2qr\7j\2\2rs\7k\2\2st\7n\2\2tu\7g\2\2u \3\2\2\2vw\7f\2\2wx\7q\2\2"+
		"x\"\3\2\2\2yz\7g\2\2z{\7p\2\2{|\7f\2\2|}\7y\2\2}~\7j\2\2~\177\7k\2\2\177"+
		"\u0080\7n\2\2\u0080\u0081\7g\2\2\u0081$\3\2\2\2\u0082\u0083\7k\2\2\u0083"+
		"\u0084\7h\2\2\u0084&\3\2\2\2\u0085\u0086\7v\2\2\u0086\u0087\7j\2\2\u0087"+
		"\u0088\7g\2\2\u0088\u0089\7p\2\2\u0089(\3\2\2\2\u008a\u008b\7g\2\2\u008b"+
		"\u008c\7n\2\2\u008c\u008d\7u\2\2\u008d\u008e\7g\2\2\u008e*\3\2\2\2\u008f"+
		"\u0090\7g\2\2\u0090\u0091\7p\2\2\u0091\u0092\7f\2\2\u0092\u0093\7k\2\2"+
		"\u0093\u0094\7h\2\2\u0094,\3\2\2\2\u0095\u0096\7t\2\2\u0096\u0097\7g\2"+
		"\2\u0097\u0098\7v\2\2\u0098\u0099\7w\2\2\u0099\u009a\7t\2\2\u009a\u009b"+
		"\7p\2\2\u009b.\3\2\2\2\u009c\u009d\7p\2\2\u009d\u009e\7k\2\2\u009e\u009f"+
		"\7n\2\2\u009f\60\3\2\2\2\u00a0\u00a4\t\2\2\2\u00a1\u00a3\t\3\2\2\u00a2"+
		"\u00a1\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2"+
		"\2\2\u00a5\62\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\t\4\2\2\u00a8\u00a9"+
		"\3\2\2\2\u00a9\u00aa\b\32\3\2\u00aa\64\3\2\2\2\6\2EP\u00a4\4\2\3\2\b\2"+
		"\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}