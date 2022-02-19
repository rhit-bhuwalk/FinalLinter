package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

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
	
	@Test
	public void basicFieldsGood() {
	 List<FieldDataObj> fields = new ArrayList<FieldDataObj>();
	 // public FieldDataObj(String name, int access, String signature,
	//	String fieldDesc) {
	// FieldDataObj n1 = new FieldDataObj(sum, access, signature, fieldDesc)
	 
	 
	 
	 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, new ArrayList<FieldDataObj>(), new ArrayList<MethodDataObj>());
	 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
	 data.add(abClass);
	 Check c = new ExposeThirdPartyTypeCheck(data);
	 Linter l = new Linter();
	 l.addToCheckList(c);
	 List<String> errs = l.lint(data);
     assertEquals(errs.size(), 0);
    }
	
	
	
	
}
