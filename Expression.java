import java.util.ArrayList;

public abstract class Expression {
		Statement parentState;
		//when an expression gets run
		//it will always return a value - either from a funcall, or yield from variable
		public abstract CompilerObj run(Scoper cScope);
		
		public abstract ArrayList<String> findFunCalls();
	}
