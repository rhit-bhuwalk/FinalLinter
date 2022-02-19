package Linter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DataSource.ClassDataObj;
import DataSource.FieldDataObj;

public class StrategyPatternCheck extends Check {

	// Written by Sam Stieby
	
	private List<FieldDataObj> fields;
	private Set<String> absOrIntNames;
	private List<FieldDataObj> absOrIntFields;

	public StrategyPatternCheck(List<ClassDataObj> d) {
		super(d);
		setup();
	}

	@Override
	List<String> check() {
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("Checking for Strategy Pattern...");
		for (ClassDataObj c : data) {
			for (FieldDataObj f : absOrIntFields) {
				if (c.getFields().contains(f) && !c.isAbstract() && !absOrIntNames.contains(c.getName())) { // concrete
																											// class is
																											// composed
																											// of an
																											// object
																											// with
																											// interface
																											// or
																											// abstract
																											// type
					ans.add("Strategy pattern is being used by abstracting "
							+ f.getFieldDesc().substring(f.getFieldDesc().lastIndexOf("/") + 1,
									f.getFieldDesc().length() - 1)
							+ " behavior out of class: " + c.getName().substring(c.getName().lastIndexOf("/") + 1));
				}
			}
		}
		return ans;
	}

	private void setup() {
		fields = new ArrayList<FieldDataObj>();
		absOrIntNames = new HashSet<String>();
		absOrIntFields = new ArrayList<FieldDataObj>();
		for (ClassDataObj c : data) {
			for (FieldDataObj f : c.getFields()) {
				fields.add(f);
			}
			if (c.isAbstract()) {
				absOrIntNames.add(c.getName());
			}
			absOrIntNames.addAll(c.getInterfaces());

			for (FieldDataObj f : fields) {
				for (String n : absOrIntNames) {
					if (f.getFieldDesc().contains(n)) {
						absOrIntFields.add(f);
					}
				}

			}

		}
	}
}
