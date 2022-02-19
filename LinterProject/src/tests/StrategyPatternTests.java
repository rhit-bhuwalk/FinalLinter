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
import Linter.StrategyPatternCheck;

public class StrategyPatternTests {

	//Written by Sam Stieby
	
	@Test
	public void basicEmptyConstructor() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, "yes", "Object", new ArrayList<String>(), new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new StrategyPatternCheck(data);
	 Linter linter = new Linter();
	 linter.addToCheckList(c);
	 List<String> result = linter.lint(data);
     assertEquals(1, result.size());
    }
	
	@Test
	public void testNoPattern() {
	 ClassDataObj abClass = new ClassDataObj("Duck", 1024, "yes", "Game", new ArrayList<String>(), new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 ClassDataObj concClass = new ClassDataObj("Mallard", 1, "yes", "Duck", new ArrayList<String>(), new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 ClassDataObj otherConcClass = new ClassDataObj("Wood", 1, "yes", "Duck", new ArrayList<String>(), new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 data.add(concClass);
	 data.add(otherConcClass);
	 Check c = new StrategyPatternCheck(data);
	 Linter linter = new Linter();
	 linter.addToCheckList(c);
	 List<String> result = linter.lint(data);
     assertEquals(1, result.size());
    }
	
	@Test
	public void testGoodPattern() {
	List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
	List<String> interfaces = new ArrayList<String>();
	fields.add(new FieldDataObj("flyB", 1, "yes", "FlyBehaviorr"));
	interfaces.add("FlyBehavior");
	 ClassDataObj main = new ClassDataObj("Main", 1, "yes", "Object", new ArrayList<String>(), fields, new ArrayList<MethodDataObj>());
	 ClassDataObj flyBehavior = new ClassDataObj("FlyBehaviorr", 512, "yes", "Object", new ArrayList<String>(), new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 ClassDataObj concClass = new ClassDataObj("MallardBehavior", 1, "yes", "Duck", interfaces, new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 ClassDataObj otherConcClass = new ClassDataObj("WoodBehavior", 1, "yes", "Duck", interfaces, new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(main);
	 data.add(flyBehavior);
	 data.add(concClass);
	 data.add(otherConcClass);
	 Check c = new StrategyPatternCheck(data);
	 Linter linter = new Linter();
	 linter.addToCheckList(c);
	 List<String> result = linter.lint(data);
     assertEquals(3, result.size()); //
    }

	@Test
	public void testBadPattern() {
	List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
	List<String> interfaces = new ArrayList<String>();
	fields.add(new FieldDataObj("flyB", 1, "yes", "MallardBehavior"));
	interfaces.add("FlyBehavior");
	 ClassDataObj main = new ClassDataObj("Main", 1, "yes", "Object", new ArrayList<String>(), fields, new ArrayList<MethodDataObj>());
	 ClassDataObj flyBehavior = new ClassDataObj("FlyBehaviorr", 512, "yes", "Object", new ArrayList<String>(), new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 ClassDataObj concClass = new ClassDataObj("MallardBehavior", 1, "yes", "Duck", interfaces, new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 ClassDataObj otherConcClass = new ClassDataObj("WoodBehavior", 1, "yes", "Duck", interfaces, new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(main);
	 data.add(flyBehavior);
	 data.add(concClass);
	 data.add(otherConcClass);
	 Check c = new StrategyPatternCheck(data);
	 Linter linter = new Linter();
	 linter.addToCheckList(c);
	 List<String> result = linter.lint(data);
	 System.out.println(result.toString());
     assertEquals(1, result.size()); //should not detect strategy pattern as the type is determined at compile-time, not run-time.
    }
}
