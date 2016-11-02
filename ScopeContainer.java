import java.util.ArrayList;
import java.util.HashMap;

public class ScopeContainer {

	//the parent of this scope
	public ScopeContainer parent = null;
	
	//identifiers and their respective types
	public HashMap<String, TypeContainer> variableTypes = new HashMap<String, TypeContainer>();
	public ArrayList<ScopeContainer> subScopes = new ArrayList<ScopeContainer>();

	int uniqId = 0;
	
	public ScopeContainer() {
	}
	
	//get the associated type for this name (here or in a parent scope)
	public TypeContainer get(String name) {

		if (variableTypes.containsKey(name)) {
			return variableTypes.get(name);
		} else if (parent == null) {
			return null;
		} else {
			return parent.get(name); 
		}
	}
	
}
