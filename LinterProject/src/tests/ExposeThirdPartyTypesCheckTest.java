package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.objectweb.asm.Type;

import DataSource.ClassDataObj;
import DataSource.FieldDataObj;
import DataSource.Instruction;
import DataSource.MethodDataObj;
import DataSource.ParameterDataObj;
import Linter.Check;
import Linter.ExposeThirdPartyTypeCheck;
import Linter.Linter;
import Linter.MissingImplementationsOfAbstractTypes;

// public MethodDataObj(String methodName, int access, String signature,
// String methodDesc, List<ParameterDataObj> paramsList, Instruction in) {

//public ClassDataObj(String n, int access, String signature, 
//String superClass, List<String> ints, List<FieldDataObj> f, List<MethodDataObj> m) {


//written by Kush Bhuwalka
public class ExposeThirdPartyTypesCheckTest {

	@Test
	public void basicEmpty() {
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new ExposeThirdPartyTypeCheck(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 0);
    }
	
	//Ljava/util/List;
	//Ljava/util/List<Ljava/lang/String;>;	
	
	@Test
	public void basic1FieldGood() {
	 List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
	 // public FieldDataObj(String name, int access, String signature,
	//	String fieldDesc) {
	 FieldDataObj n1 = new FieldDataObj("sum", 1, null, "Ljava/lang/Integer;");
	 fields.add(n1);
	 
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, fields, new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new ExposeThirdPartyTypeCheck(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 0);
    }
	
	@Test
	public void basicMultiplePrimitivesFieldGood() {
	 List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
	 // public FieldDataObj(String name, int access, String signature,
	//	String fieldDesc) {
	 FieldDataObj n1 = new FieldDataObj("sum", 1, null, "Ljava/lang/Integer;");
	 FieldDataObj n2 = new FieldDataObj("cat", 1, null, "Ljava/lang/Boolean;");
	 FieldDataObj n3 = new FieldDataObj("dog", 1, null, "Ljava/lang/Double;");
	 fields.add(n1);
	 fields.add(n2);
	 fields.add(n3);
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, fields, new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new ExposeThirdPartyTypeCheck(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 0);
    }
	
	//Ljava/util/List;
		//Ljava/util/List<Ljava/lang/String;>;	
	@Test
	public void basicComplexFieldGood() {
	 List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
	 // public FieldDataObj(String name, int access, String signature,
	//	String fieldDesc) {
	 FieldDataObj n1 = new FieldDataObj("list", 1, "Ljava/util/List<Ljava/lang/String;>;", "Ljava/util/List;");
	 FieldDataObj n2 = new FieldDataObj("sum", 1, null, "Ljava/lang/Boolean;");
	 FieldDataObj n3 = new FieldDataObj("sum", 1, null, "Ljava/lang/Double;");
	 fields.add(n1);
	 fields.add(n2);
	 fields.add(n3);
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, fields, new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new ExposeThirdPartyTypeCheck(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 0);
    }
	
	
	@Test
	public void basicFieldWithClass() {
	 List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
	 // public FieldDataObj(String name, int access, String signature,
	//	String fieldDesc) {
	 FieldDataObj n1 = new FieldDataObj("chess", 1, null, "LChess;");
	 FieldDataObj n2 = new FieldDataObj("checkers", 1, null, "LCheckers;");
	 FieldDataObj n3 = new FieldDataObj("count", 1, null, "Ljava/lang/Double;");
	 fields.add(n1);
	 fields.add(n2);
	 fields.add(n3);
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, fields, new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new ExposeThirdPartyTypeCheck(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 1);
     assertEquals(errs.get(0), "Field checkers in class Chess of type Checkers is exposed.");
    }
	
	
	//Ljava/util/List;
			//Ljava/util/List<Ljava/lang/String;>;	
	@Test
	public void complexFieldsTest() {
	 List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
	 // public FieldDataObj(String name, int access, String signature,
	//	String fieldDesc) {
	 FieldDataObj n1 = new FieldDataObj("chess", 1, null, "LChess;");
	 FieldDataObj n2 = new FieldDataObj("checkers", 1, null, "LCheckers;");
	 FieldDataObj n3 = new FieldDataObj("checkerzz", 2, null, "LCheckers");
	 FieldDataObj n4 = new FieldDataObj("list", 1, "Ljava/util/List<Checkers;>;", "Ljava/util/List;");
	 FieldDataObj n5 = new FieldDataObj("list2", 1, null, "Ljava/lang/Double;");
	 fields.add(n1);
	 fields.add(n2);
	 fields.add(n3);
	 fields.add(n4);
	 fields.add(n5);
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, fields, new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new ExposeThirdPartyTypeCheck(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 2);
     assertEquals(errs.get(0), "Field checkers in class Chess of type Checkers is exposed.");
     assertEquals(errs.get(1), "Field list in class Chess of type java.util.List is exposed.");
	}
	
	
	// public MethodDataObj(String methodName, int access, String signature,
	// String methodDesc, List<ParameterDataObj> paramsList, Instruction in) {
	
	@Test
	public void complexMethodsTest() {
	 List<MethodDataObj> methods = new ArrayList<MethodDataObj>();
	//()Ljava/util/List<Ljava/lang/Integer;>;
		//()Ljava/util/List;
	 MethodDataObj n1 = new MethodDataObj("chess", 1, null, "()LChess;", null, null);
	 MethodDataObj n2 = new MethodDataObj("checkers", 1, null, "()LCheckers;", null, null);
	 MethodDataObj n3 = new MethodDataObj("checkerzz", 2, null, "()LCheckers", null, null);
	 MethodDataObj n4 = new MethodDataObj("list", 1, "()Ljava/util/List<LCheckers;>;", "()Ljava.util.List;", null, null);
	 MethodDataObj n5 = new MethodDataObj("list2", 1, null, "()Ljava.lang.Double;", null, null);
	 MethodDataObj n6 = new MethodDataObj("list3", 1, null, "()V;", null, null);
	 methods.add(n1);
	 methods.add(n2);
	 methods.add(n3);
	 methods.add(n4);
	 methods.add(n5);
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null,  new ArrayList<FieldDataObj>(), methods);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new ExposeThirdPartyTypeCheck(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 2);
     assertEquals(errs.get(0), "Method checkers in class Chess of type Checkers is exposed.");
     assertEquals(errs.get(1), "Method list in class Chess of type java.util.List is exposed.");
	}
	
	
	@Test
	public void complexFieldsAndMethodsTest() {
	 List<MethodDataObj> methods = new ArrayList<MethodDataObj>();
	//()Ljava/util/List<Ljava/lang/Integer;>;
		//()Ljava/util/List;
	 MethodDataObj n1 = new MethodDataObj("chess", 1, null, "()LChess;", null, null);
	 MethodDataObj n2 = new MethodDataObj("checkers", 1, null, "()LCheckers;", null, null);
	 MethodDataObj n3 = new MethodDataObj("checkerzz", 2, null, "()LCheckers", null, null);
	 MethodDataObj n4 = new MethodDataObj("list", 1, "()Ljava/util/List<LCheckers;>;", "()Ljava.util.List;", null, null);
	 MethodDataObj n5 = new MethodDataObj("list2", 1, null, "()Ljava.lang.Double;", null, null);
	 MethodDataObj n6 = new MethodDataObj("list3", 1, null, "()V;", null, null);
	 List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
	 FieldDataObj m1 = new FieldDataObj("chess", 1, null, "LChess;");
	 FieldDataObj m2 = new FieldDataObj("checkers", 1, null, "LCheckers;");
	 FieldDataObj m3 = new FieldDataObj("checkerzz", 2, null, "LCheckers");
	 FieldDataObj m4 = new FieldDataObj("list", 1, "Ljava/util/List<Checkers;>;", "Ljava/util/List;");
	 FieldDataObj m5 = new FieldDataObj("list2", 1, null, "Ljava/lang/Double;");
	 fields.add(m1);
	 fields.add(m2);
	 fields.add(m3);
	 fields.add(m4);
	 fields.add(m5);
	 methods.add(n1);
	 methods.add(n2);
	 methods.add(n3);
	 methods.add(n4);
	 methods.add(n5);
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null,  new ArrayList<FieldDataObj>(), methods);
	 ClassDataObj abClass2 = new ClassDataObj("Board", 1024, null, "Object", null,  fields, methods);
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 data.add(abClass2);
	 Check c = new ExposeThirdPartyTypeCheck(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 6);
     assertEquals(errs.get(0), "Method checkers in class Chess of type Checkers is exposed.");
     assertEquals(errs.get(1), "Method list in class Chess of type java.util.List is exposed.");
     assertEquals(errs.get(2), "Field checkers in class Board of type Checkers is exposed.");
     assertEquals(errs.get(3), "Field list in class Board of type java.util.List is exposed.");
     assertEquals(errs.get(4), "Method checkers in class Board of type Checkers is exposed.");
     assertEquals(errs.get(5), "Method list in class Board of type java.util.List is exposed.");
	}
	
	
	
	
}
