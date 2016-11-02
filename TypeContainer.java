

public class TypeContainer {
	
	public String baseType = null;
	public int numLists = 0;
	
	public boolean isUnknown = false;
	
	public TypeContainer(int numberOfRecursiveLists, String baseType) {
		if (!baseType.equals("string")) {
			//set the basetype to reference something iff not a concrete type
			this.baseType = baseType;
		}
		this.numLists = numberOfRecursiveLists;
	}
	
	public TypeContainer(int numberOfRecursiveLists, String baseType, boolean isUnknown) {
		this.baseType = baseType;
		this.numLists = numberOfRecursiveLists;
		this.isUnknown = isUnknown;
	}
	
	public String getConcreteType() {
		//simply return the baseType if it exists or "string"
		return baseType != null ? baseType : "string";
	}
	
}
