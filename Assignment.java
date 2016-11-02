
public class Assignment extends Statement {
	public String lhs;
	public Expression rhs;

	@Override
	public CompilerObj run() {
		this.parent.setVar(lhs, rhs.run(this.parent));
		return null;
	}
}