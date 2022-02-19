package Linter;

import java.util.ArrayList;
import java.util.List;
import DataSource.*;
public class Linter {

	private List<Check> checkList;

	public Linter() {
		checkList = new ArrayList<Check>();
	}
	
	
	
	
	public List<String> lint(List<ClassDataObj> data) {
		List<String> toReturn = new ArrayList<String>();
		
			for (Check c : checkList) {
				toReturn.addAll(c.check());
				}
		return toReturn;
	}
	
	public List<String> lintAll(List<ClassDataObj> data) {
		populateCheckList(data);
		List<String> toReturn = new ArrayList<String>();
		
		for (Check c : checkList) {
			toReturn.addAll(c.check());
		}
		return toReturn;
	}

	public void addToCheckList(Check c) {
		checkList.add(c);
	}
	
	private void populateCheckList(List<ClassDataObj> data) {
		checkList.clear();
		checkList.add(new MissingImplementationsOfAbstractTypes(data));
		checkList.add(new ExposeThirdPartyTypeCheck(data));
		checkList.add(new TemplatePattern(data));
		//checkList.add(new NamingConventionCheck(data));
		//checkList.add(new TightCouplingCheck(data));
		//checkList.add(new AdapterPatternCheck(data));
	}
}
