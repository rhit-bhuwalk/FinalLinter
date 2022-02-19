//Coded by Richard Costa

package Linter;

import java.util.ArrayList;
import java.util.List;

import DataSource.ClassDataObj;
import DataSource.MethodDataObj;

public class EqualsHashCheck extends Check{

	public EqualsHashCheck(List<ClassDataObj> d) {
		super(d);
	}

	@Override
	List<String> check() {
		List<String> sb = new ArrayList<String>();
		for (ClassDataObj target : data) {
			int hash = 0;
			int eql = 0;
			//determine if hashCode and/or equals are implemented
			if(target.getMethods()!=null) {
			for (MethodDataObj a : target.getMethods()) {
				if(a.getName()=="hashCode" && (a.getParamList()==null || a.getParamList().size()==0)) {
					hash++;
				}else if(a.getName()=="equals" && (a.getParamList()==null || a.getParamList().size()==0)) {
					eql++;
				}
			}
			}
			// add any offenders to the list
			if (hash==0 && eql!=0) {
				sb.add("The "+target.getName()+" class defines hashCode() but does not define equals().");
			}else if(hash!=0 && eql==0) {
				sb.add("The "+target.getName()+" class defines equals() but does not define hashCode().");
			}

		}
		return sb;
	}
	
}
