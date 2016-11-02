import java.util.ArrayList;

//object to represent to the objects that expressions invoke
public class CompilerObj {
	//if isList, the list of IntObjs is HOT
	//if not, the string is HOT
	public boolean isList;
	
	public String strVal = null;
	public ArrayList<CompilerObj> listVal = null;

	//simply allows boolean types to be returned from builtin fns
	boolean retVal;

	boolean nilInst = false;

	//set the values of this, such that the values match the supplied one
	//and we don't change the reference of super(this)
	public void inPlaceSet(CompilerObj newVal) {

		//simply duplicate newVal, and set the internals of this to be that
		CompilerObj dup = newVal.duplikator();
		this.isList 	= dup.isList;
		this.strVal 	= dup.strVal;
		this.listVal 	= dup.listVal;
		this.nilInst 	= dup.nilInst;
		this.retVal 	= dup.retVal;
	}

	//perform deep copy of the object and return it
	public CompilerObj duplikator() {

		CompilerObj newObj = new CompilerObj();
		newObj.isList = this.isList;
		newObj.nilInst = this.nilInst;

		if (this.isList) {
			//copy the array
			ArrayList<CompilerObj> newList = new ArrayList<>();
			for (CompilerObj old : this.listVal) {
				if (old == null) {
					newList.add(null);
				} else {
					newList.add(old.duplikator());
				}
			}
			newObj.listVal = newList;
		} else {
			//copy the string val
			newObj.strVal = new String(this.strVal);
		}
		return newObj;
	}

	public boolean isEquivalent(CompilerObj b) {
		//TODO:

		//check if both nil types
		if (this.nilInst && b.nilInst) return true;
		//if only one is
		if (this.nilInst || b.nilInst) return false;

		if (b.isList != this.isList) return false;

		if (!isList) {
			return this.strVal.equals(b.strVal);
		} else {
			if (b.listVal.size() != this.listVal.size()) return false;

			for (int i = 0; i < this.listVal.size(); ++i) {			

				CompilerObj lhs = this.listVal.get(i);
				CompilerObj rhs = b.listVal.get(i);

				//if both are null
				if (lhs == null && rhs == null) continue;
				//if one of them is null (and the other is a nil type)
				if (lhs == null) {
					if (!rhs.nilInst) return false;
				} else if (rhs == null) {
					if (!lhs.nilInst) return false;
				}
				//if they're not recursively equivalent
				if (!lhs.isEquivalent(rhs)) return false;
			}

			return true;
		}
	}
}
