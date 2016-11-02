import java.util.ArrayList;

//copy a static constant
public class Constant extends Expression {
	public CompilerObj constType;

	@Override
	public CompilerObj run(Scoper cScope) {
		return constType.duplikator();
	}

	@Override
	public ArrayList<String> findFunCalls() {
		return new ArrayList<String>();
	}
	
}
