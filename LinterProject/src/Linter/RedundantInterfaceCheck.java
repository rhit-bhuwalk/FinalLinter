package Linter;

import java.util.ArrayList;
import java.util.List;

import org.objectweb.asm.Opcodes;

import DataSource.ClassDataObj;
import DataSource.FieldDataObj;


// Written by Bruno Christensen 
public class RedundantInterfaceCheck extends Check {

	public RedundantInterfaceCheck(List<ClassDataObj> d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	@Override
	List<String> check() {
		List<String> sb = new ArrayList<String>();
		sb.add("Potential redundant interface found:");
		for(ClassDataObj intf: data) {
			if((intf.getAccess() & Opcodes.ACC_INTERFACE) != 0) {
				int count = 0;
				List<String> s = new ArrayList<String>();
				for(ClassDataObj c: data) {
					if(c.getInterfaces().contains(intf.getName())) {
						s.add(c.getName());
						count++;
					}
					for(FieldDataObj f: c.getFields()) {
						
					}
				}
				
				
				if(count == 1) {
					sb.add(intf.getName() + " is only implemented by 1 class: " + s.toString() + ".");
				} else if (count == 0) {
					sb.add(intf.getName() + " is not implemented by any class. Consider removing.");
				}
			}
		}
		return sb;
	}

}
