//package tests;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.junit.jupiter.api.Test;
//
//import DataSource.*;
//import Linter.*;
//import presentation.*;
////
////public ClassDataObj(String name, int access, String signature, 
////		String superClass, List<String> ints, List<FieldDataObj> f, List<MethodDataObj> m) {
//import Linter.TemplatePattern;
//
//public class MissingImplementationsOfAbstractTypesTest {
//
//	@Test
//	public void basicTest() {
//	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, null, null, null, null);
//	 ClassDataObj imp = new ClassDataObj("Piece", 0, null, "Chess", null, null, null);
//	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
//	 data.add(abClass);
//	 data.add(imp);
//	 new Linter(new TemplatePattern(data)).lint(data);
//	 Check c = new TemplatePattern(data);
//	 
//	}
