package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import DataSource.ClassDataObj;
import DataSource.FieldDataObj;
import DataSource.MethodDataObj;
import Linter.Check;
import Linter.Linter;
import Linter.ProgramToInterfaceCheck;

public class ProgramToInterfaceTests {

	@Test
	public void emptyClassName() {
		ClassDataObj abClass = new ClassDataObj("", 1024, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		Check c = new ProgramToInterfaceCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(2, result.size()); // Should result in no added result, can't violate a principle on no code.
	}

	@Test
	public void oneNotProgrammedToInterface() {
		List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
		fields.add(new FieldDataObj("goodName", 1, "", "Concrete"));
		ClassDataObj main = new ClassDataObj("main", 1024, "yes", "Object", new ArrayList<String>(), fields,
				new ArrayList<MethodDataObj>());
		ClassDataObj abClass = new ClassDataObj("Abstraction", 1024, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj concClass = new ClassDataObj("Concrete", 1024, "yes", "Abstraction", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(main);
		data.add(abClass);
		data.add(concClass);
		Check c = new ProgramToInterfaceCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(3, result.size()); // Should result in one added result, programmed against implementation concrete
										// instead of abstraction.
	}

	@Test
	public void oneProgrammedToInterface() {
		List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
		fields.add(new FieldDataObj("goodName", 1, "", "Abstraction"));
		ClassDataObj main = new ClassDataObj("main", 1024, "yes", "Object", new ArrayList<String>(), fields,
				new ArrayList<MethodDataObj>());
		ClassDataObj abClass = new ClassDataObj("Abstraction", 1024, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj concClass = new ClassDataObj("Concrete", 1024, "yes", "Abstraction", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(main);
		data.add(abClass);
		data.add(concClass);
		Check c = new ProgramToInterfaceCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(3, result.size()); // Should result in no added result, programmed against interface abstraction and not concrete
	}
	
	@Test
	public void twoProgrammedToInterface() {
		List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
		fields.add(new FieldDataObj("goodName", 1, "", "Abstraction"));
		fields.add(new FieldDataObj("gooderName", 1, "", "Abstraction"));
		ClassDataObj main = new ClassDataObj("main", 1024, "yes", "Object", new ArrayList<String>(), fields,
				new ArrayList<MethodDataObj>());
		ClassDataObj other = new ClassDataObj("other", 1024, "yes", "Object", new ArrayList<String>(), fields,
				new ArrayList<MethodDataObj>());
		ClassDataObj abClass = new ClassDataObj("Abstraction", 1024, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj concClass = new ClassDataObj("Concrete", 1024, "yes", "Abstraction", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(main);
		data.add(other);
		data.add(abClass);
		data.add(concClass);
		Check c = new ProgramToInterfaceCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(3, result.size()); // Should result in no added result, programmed against interface abstraction and not concrete
	}
}
