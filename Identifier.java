import java.util.ArrayList;

//copy variable
public class Identifier extends Expression {
	public String ident;

	@Override
	public CompilerObj run(Scoper cScope) {
		return cScope.getVar(ident);
	}
	
	@Override
	public ArrayList<String> findFunCalls() {
		return new ArrayList<String>();
	}
}
