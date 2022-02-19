package Linter;

import java.util.ArrayList;
import java.util.List;
import DataSource.*;

public abstract class Check {
	protected List<ClassDataObj> data;
	protected List<String> errors;

	public Check(List<ClassDataObj> d) {
		errors = new ArrayList<String>();
		data = d;
	}

	abstract List<String> check();
}
