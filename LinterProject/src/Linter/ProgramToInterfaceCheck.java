package Linter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DataSource.ClassDataObj;
import DataSource.FieldDataObj;
import DataSource.MethodDataObj;

public class ProgramToInterfaceCheck extends Check {

	//Written by Sam Stieby
	
	Set<String> iNames;
	Set<String> absNames;
	List<String> classNames;
	Set<String> extendNames;
	Set<String> implementNames;

	public ProgramToInterfaceCheck(List<ClassDataObj> d) {
		super(d);
		setup();
	}

	// if method or field is not an abstract or interface type (that has a super
	// class or interface to implement), not programming to interface

//	 In simple or concrete words, you should always be using interface type on 
//	 variables, return types of a method or argument type of methods in Java-like 
//	 using SuperClass or Parent class type to store object rather than using SubClass.

	@Override
	List<String> check() {
		ArrayList<String> ans = new ArrayList<String>();
		ans.add("Running programming to interface design principle check...");
		for (ClassDataObj c : data) {
			for (MethodDataObj m : c.getMethods()) {
				if (implementNames.contains(m.getDesc()) || extendNames.contains(m.getDesc())) {
					ans.add("Method " + m.getName() + " is programmed to implementation " + m.getReturnType()
							+ " in class " + c.getName());
				}
			}
			for (FieldDataObj f : c.getFields()) {
				if (implementNames.contains(f.getReturnType()) || extendNames.contains(f.getReturnType())) {
					ans.add("Field " + f.getName() + " is programmed to implementation " + f.getReturnType()
					+ " in class " + c.getName());
				}
			}
		}
		if (ans.size() == 1) {
			ans.add("Check found no violations of the principle.");
		}
		return ans;
	}

	private void setup() {
		iNames = new HashSet<String>();
		absNames = new HashSet<String>();
		classNames = new ArrayList<String>();
		extendNames = new HashSet<String>();
		implementNames = new HashSet<String>();
		HashSet<String> iNamesToRemove = new HashSet<String>();
		HashSet<String> absNamesToRemove = new HashSet<String>();

		for (ClassDataObj c : data) {
			iNames.addAll(c.getInterfaces());
			absNames.add(c.getSuperClassName());
			classNames.add(c.getName());
		}
		for (String i : iNames) {
			if (!classNames.contains(i)) {
				iNamesToRemove.add(i);
			}
		}
		for (String a : absNames) {
			if (!classNames.contains(a)) {
				absNamesToRemove.add(a);
			}
		}
		iNames.removeAll(iNamesToRemove);
		absNames.removeAll(absNamesToRemove);

		for (ClassDataObj c : data) {
			for (String i : iNames) {
				if (c.getInterfaces().contains(i)) {
					implementNames.add(c.getName());
				}
			}
			if (absNames.contains(c.getSuperClassName())) {
				extendNames.add(c.getName());
			}
		}
	}
}
