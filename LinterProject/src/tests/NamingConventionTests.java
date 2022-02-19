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
import Linter.NamingConventionCheck;

public class NamingConventionTests {

	//Written by Sam Stieby
	
	@Test
	public void emptyClassName() {
		ClassDataObj abClass = new ClassDataObj("", 1024, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(2, result.size()); //Should result in bad result, name is blank thus a bad name.
	}
	
	@Test
	public void testGoodSingleClassName() {
		ClassDataObj abClass = new ClassDataObj("Game", 1024, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(1, result.size()); //Should result in good result, name is Pascal case thus a good name.
	}
	
	@Test
	public void testGoodMultipleClassName() {
		ClassDataObj abClass = new ClassDataObj("Game", 1024, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj chess = new ClassDataObj("Chess", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj checkers = new ClassDataObj("Game", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		data.add(chess);
		data.add(checkers);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(1, result.size()); //Should result in good result, names are Pascal case thus a good name.
	}
	
	@Test
	public void someGoodSomeBadClassNames() {
		ClassDataObj abClass = new ClassDataObj("Game", 1024, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj chess = new ClassDataObj("ChesS", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj checkers = new ClassDataObj("House", 1, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj eeee = new ClassDataObj("EEEEE", 1, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj notGoodName = new ClassDataObj("notGoodName", 1, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj threeFourThree = new ClassDataObj("343", 1, "yes", "Object", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		ClassDataObj yes = new ClassDataObj("Yes", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		data.add(chess);
		data.add(checkers);
		data.add(yes);
		data.add(notGoodName);
		data.add(threeFourThree);
		data.add(eeee);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(5, result.size()); //Should result in bad result for 4 names.
	}
	
	@Test
	public void testGoodFieldNames() {
		ArrayList<FieldDataObj> fields1 = new ArrayList<FieldDataObj>();
		fields1.add(new FieldDataObj("goodName", 1, "", ""));
		fields1.add(new FieldDataObj("betterName", 1, "", ""));
		fields1.add(new FieldDataObj("bestName", 1, "", ""));

		ClassDataObj abClass = new ClassDataObj("Game", 1024, "yes", "Object", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj chess = new ClassDataObj("Chess", 1, "yes", "Game", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj checkers = new ClassDataObj("Game", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		data.add(chess);
		data.add(checkers);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(1, result.size()); //Should result in good result, names are Camel case thus a good name.
	}
	
	@Test
	public void testBadFieldNames() {
		ArrayList<FieldDataObj> fields1 = new ArrayList<FieldDataObj>();
		fields1.add(new FieldDataObj("NotGoodName", 1, "", ""));
		fields1.add(new FieldDataObj("BadName", 1, "", ""));
		fields1.add(new FieldDataObj("Worst_Name", 1, "", ""));

		ClassDataObj abClass = new ClassDataObj("Game", 1024, "yes", "Object", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj chess = new ClassDataObj("Chess", 1, "yes", "Game", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj checkers = new ClassDataObj("Game", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		data.add(chess);
		data.add(checkers);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(7, result.size()); //Should result in bad result for 3 names in 2 classes.
	}
	
	@Test
	public void testGoodAndBadFieldNames() {
		ArrayList<FieldDataObj> fields1 = new ArrayList<FieldDataObj>();
		fields1.add(new FieldDataObj("goodName", 1, "", ""));
		fields1.add(new FieldDataObj("BadName", 1, "", ""));
		fields1.add(new FieldDataObj("Worst_Name", 1, "", ""));
		fields1.add(new FieldDataObj("decentNameIGuess", 1, "", ""));
		ClassDataObj abClass = new ClassDataObj("Game", 1024, "yes", "Object", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj chess = new ClassDataObj("Chess", 1, "yes", "Game", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj checkers = new ClassDataObj("Game", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		data.add(chess);
		data.add(checkers);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(5, result.size()); //Should result in bad result for 2 names in 2 classes.
	}
	
	@Test
	public void testGoodFieldAndClassNames() {
		ArrayList<FieldDataObj> fields1 = new ArrayList<FieldDataObj>();
		fields1.add(new FieldDataObj("goodName", 1, "", ""));
		fields1.add(new FieldDataObj("betterName", 1, "", ""));
		fields1.add(new FieldDataObj("bestName", 1, "", ""));
		fields1.add(new FieldDataObj("decentNameIGuess", 1, "", ""));
		ClassDataObj abClass = new ClassDataObj("Game", 1024, "yes", "Object", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj chess = new ClassDataObj("Chess", 1, "yes", "Game", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj checkers = new ClassDataObj("Game", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		data.add(chess);
		data.add(checkers);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(1, result.size()); //Should result in 0 added results, all good names.
	}
	
	@Test
	public void testBadFieldAndClassNames() {
		ArrayList<FieldDataObj> fields1 = new ArrayList<FieldDataObj>();
		fields1.add(new FieldDataObj("BadName", 1, "", ""));
		fields1.add(new FieldDataObj("NotGood", 1, "", ""));
		fields1.add(new FieldDataObj("Seriously_Bad333", 1, "", ""));
		fields1.add(new FieldDataObj("dsadfadsfdsa833298", 1, "", ""));
		ClassDataObj abClass = new ClassDataObj("game", 1024, "yes", "Object", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj chess = new ClassDataObj("c_hess", 1, "yes", "Game", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj checkers = new ClassDataObj("^^^^", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		data.add(chess);
		data.add(checkers);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(10, result.size()); //Should result in 9 bad results.
	}
	
	@Test
	public void testSomeGoodSomeBadFieldAndClassNames() {
		ArrayList<FieldDataObj> fields1 = new ArrayList<FieldDataObj>();
		fields1.add(new FieldDataObj("TotallyGoodName", 1, "", ""));
		fields1.add(new FieldDataObj("betterName", 1, "", ""));
		fields1.add(new FieldDataObj("best!Name", 1, "", ""));
		fields1.add(new FieldDataObj("decentNameIGuess...", 1, "", ""));
		ClassDataObj abClass = new ClassDataObj("Game", 1024, "yes", "Object", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj chess = new ClassDataObj("Che55", 1, "yes", "Game", new ArrayList<String>(),
				fields1, new ArrayList<MethodDataObj>());
		ClassDataObj checkers = new ClassDataObj("Game", 1, "yes", "Game", new ArrayList<String>(),
				new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
		List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		data.add(abClass);
		data.add(chess);
		data.add(checkers);
		Check c = new NamingConventionCheck(data);
		Linter linter = new Linter();
		linter.addToCheckList(c);
		List<String> result = linter.lint(data);
		assertEquals(8, result.size()); //Should result in 8 bad added results.
	}

}
