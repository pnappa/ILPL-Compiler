
//for ifs/elses/whiles
public class CondScope extends Scoper {
	Expression condExp;

	public CondScope() {
		this.thisScope = CompiledTypes.ScopeType.CONDITIONAL;
	}
}

