package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import DataSource.*;
import Linter.*;
import presentation.*;
//
//public ClassDataObj(String name, int access, String signature, 
//		String superClass, List<String> ints, List<FieldDataObj> f, List<MethodDataObj> m) {
import Linter.TemplatePattern;

public class MissingImplementationsOfAbstractTypesTest {

	@Test
	public void basicWorkingTest() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, null);
	 ClassDataObj imp = new ClassDataObj("Piece", 0, null, "Chess", null, null, null);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 data.add(imp);
	 Check c = new MissingImplementationsOfAbstractTypes(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 0);
    }
	
	@Test
	public void basic1AbstractTest() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, null);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new MissingImplementationsOfAbstractTypes(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 1);
     assertEquals(errs.get(0), "Abstract class Chess is unimplemented \n");
    }
	
	@Test
	public void multipleAbstractsOnly() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, null);
	 ClassDataObj abClass2 = new ClassDataObj("Checkers", 1024, null, "Object", null, null, null);
	 ClassDataObj abClass3 = new ClassDataObj("Risk", 1024, null, "Object", null, null, null);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 data.add(abClass2);
	 data.add(abClass3);
	 Check c = new MissingImplementationsOfAbstractTypes(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 3);
     assertEquals(errs.get(0), "Abstract class Chess is unimplemented \n");
     assertEquals(errs.get(1), "Abstract class Checkers is unimplemented \n");
     assertEquals(errs.get(2), "Abstract class Risk is unimplemented \n");
    }
	
	
	@Test
	public void multipleAbstractsAndInterfaces() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 512, null, "Object", null, null, null);
	 ClassDataObj abClass2 = new ClassDataObj("Checkers", 1024, null, "Object", null, null, null);
	 ClassDataObj abClass3 = new ClassDataObj("Risk", 1024, null, "Object", null, null, null);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 data.add(abClass2);
	 data.add(abClass3);
	 Check c = new MissingImplementationsOfAbstractTypes(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 2);
     assertEquals(errs.get(0), "Abstract class Checkers is unimplemented \n");
     assertEquals(errs.get(1), "Abstract class Risk is unimplemented \n");
    }
	
	@Test
	public void abstractsInterfacesAndClasses() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 512, null, "Object", null, null, null);
	 ClassDataObj abClass2 = new ClassDataObj("Checkers", 1024, null, "Object", null, null, null);
	 ClassDataObj abClass3 = new ClassDataObj("Risk", 1, null, "Object", null, null, null);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 data.add(abClass2);
	 data.add(abClass3);
	 Check c = new MissingImplementationsOfAbstractTypes(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 1);
     assertEquals(errs.get(0), "Abstract class Checkers is unimplemented \n");
    }
	
	@Test
	public void multipleAbstractsAndImplementsMixedBag() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, null);
	 ClassDataObj abClass2 = new ClassDataObj("Checkers", 1024, null, "Object", null, null, null);
	 ClassDataObj abClass3 = new ClassDataObj("Risk", 1, null, "Checkers", null, null, null);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 data.add(abClass2);
	 data.add(abClass3);
	 Check c = new MissingImplementationsOfAbstractTypes(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 1);
     assertEquals(errs.get(0), "Abstract class Chess is unimplemented \n");
    }
	
	@Test
	public void chainedAbstractsGood() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, null);
	 ClassDataObj abClass2 = new ClassDataObj("Checkers", 1024, null, "Chess", null, null, null);
	 ClassDataObj abClass3 = new ClassDataObj("Risk", 1, null, "Checkers", null, null, null);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 data.add(abClass2);
	 data.add(abClass3);
	 Check c = new MissingImplementationsOfAbstractTypes(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 0);
    }
	
	@Test
	public void chainedAbstractBad() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, null);
	 ClassDataObj abClass2 = new ClassDataObj("Checkers", 1024, null, "Chess", null, null, null);
	 ClassDataObj abClass3 = new ClassDataObj("Risk", 1, null, "Object", null, null, null);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 data.add(abClass2);
	 data.add(abClass3);
	 Check c = new MissingImplementationsOfAbstractTypes(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
	 assertEquals(errs.size(), 1);
     assertEquals(errs.get(0), "Abstract class Checkers is unimplemented \n");
    }
	
	
	
}
