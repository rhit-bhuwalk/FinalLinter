package Linter;

import java.util.ArrayList;
import java.util.List;

import DataSource.ClassDataObj;
import DataSource.FieldDataObj;
import DataSource.MethodDataObj;
import DataSource.ParameterDataObj;

public class NamingConventionCheck extends Check {

	//Written by Sam Stieby
	
	List<String> classNames;
	List<String> fieldNames;
	List<String> methodNames;
	List<String> paramNames;
	List<String> constantNames;

	public NamingConventionCheck(List<ClassDataObj> d) {
		super(d);
		populateNameLists();
	}

	@Override
	List<String> check() {
		List<String> ans = new ArrayList<String>();
		ans.add("Checking Naming Convention...");
		for (String c : classNames) {
			if (!isPascalCase(c)) {
				ans.add("Bad class name: " + c + " does not conform to Pascal case naming convention.");
			}
		}
		for (String f : fieldNames) {
			if (!isCamelCase(f)) {
				ans.add("Bad field name: " + f + " does not conform to Camel case naming convention.");
			}
		}
		for (String c : constantNames) {
			if (!isGoodConstantName(c)) {
				ans.add("Bad constant name: " + c);
			}
		}
		for (String m : methodNames) {
			if (m.equals("<init>")) {
				continue;
			}
			if (!isCamelCase(m)) {
				ans.add("Bad method name: " + m + " does not conform to Camel case naming convention.");
			}
		}
		for (String p : paramNames) {
			if (!isCamelCase(p)) {
				ans.add("Bad parameter name: " + p + " does not conform to Camel case naming convention.");
			}
		}
		resetLists();
		return ans;
	}

	private boolean isCamelCase(String in) {
		return in.matches("[a-z]+[a-z|0-9]*([A-Z][a-z|0-9]*)*");
	}
	
	private boolean isPascalCase(String in) {
		return in.matches("^[A-Z][a-z]+(?:[A-Z][a-z]+)*$");
	}
	
	private boolean isGoodConstantName(String in) { // I am unsure of what the actual name for this convention is.
		return in.matches("^[A-Z]+(?:_[A-Z]+)*$");
	}
	
	private void populateNameLists() {
		this.classNames = new ArrayList<String>();
		this.fieldNames = new ArrayList<String>();
		this.constantNames = new ArrayList<String>();
		this.methodNames = new ArrayList<String>();
		this.paramNames = new ArrayList<String>();

		for (ClassDataObj c : data) {
			classNames.add(c.getName().substring(c.getName().lastIndexOf("/") + 1));
			for (MethodDataObj m : c.getMethods()) {
				methodNames.add(m.getName());
				for (ParameterDataObj p : m.getParamList()) {
					paramNames.add(p.getParamName());
				}
			}
			for (FieldDataObj f : c.getFields()) {
				if (f.isFinal() && f.isStatic()) {
					this.constantNames.add(f.getName());
				}
				fieldNames.add(f.getName());
			}
		}		
	}
	
	private void resetLists() { // To be able to reuse for other classes in one session
		constantNames.removeAll(constantNames);
		fieldNames.removeAll(fieldNames);
		methodNames.removeAll(methodNames);
		classNames.removeAll(classNames);
		paramNames.removeAll(paramNames);		
	}
}
