package Linter;

import java.util.ArrayList;
import java.util.List;
import DataSource.*;
/* Written by Bruno Christensen 
 * With assistance from Richard Costa
 */
public class AdapterPatternCheck extends Check {

	public AdapterPatternCheck(List<ClassDataObj> d) {
		super(d);
		// TODO Auto-generated constructor stub
	}

	@Override
	List<String> check() {
		List<String> sb = new ArrayList<String>();
		// Assemble a list of user-defined object names for later reference.
		List<String> l = new ArrayList<String>();
		for (ClassDataObj a : data) {
			l.add(a.getName());
		}
		for (ClassDataObj adapter : data) {
			if (adapter.getInterfaces().size() == 1 && adapter.getFields().size() == 1) {
				String interfaceName = adapter.getInterfaces().get(0);
				for (ClassDataObj client : data) {
					for (FieldDataObj f : client.getFields()) {
						if (f.getFieldDesc().length() > 1 && f.getFieldDesc().contains(interfaceName)) {
							sb.add("Adapter Pattern Found:\n" + "Client: " + client.getName() + "\n"
									+ "Target: " + interfaceName.replace('/', '.') + "\n" + "Adatper: " + adapter.getName() + "\n"
									+ "Adaptee: " + adapter.getFields().get(0).getFieldDesc());
						}
					}
				}
			}
		}
		return sb;
	}
}
