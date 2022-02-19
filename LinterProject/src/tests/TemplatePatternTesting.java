package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import DataSource.ClassDataObj;
import DataSource.Instruction;
import DataSource.MethodDataObj;
import Linter.Check;
import Linter.Linter;
import Linter.MissingImplementationsOfAbstractTypes;
import Linter.TemplatePattern;

public class TemplatePatternTesting {

	@Test
	public void basicTemplateTest() {
		
		//public ClassDataObj(String name, int access, String signature, 
//		String superClass, List<String> ints, List<FieldDataObj> f, List<MethodDataObj> m) {
		 List<MethodDataObj> methods = new ArrayList<MethodDataObj>();
			//()Ljava/util/List<Ljava/lang/Integer;>;
				//()Ljava/util/List;
			 MethodDataObj n1 = new MethodDataObj("template", 16, null, "()V;", null, new Instruction(3,0,3));
			 MethodDataObj n2 = new MethodDataObj("checkers", 1, null, "()LCheckers;", null, null);
			 methods.add(n1);
			 methods.add(n2);
		 
		ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, methods);
		 ClassDataObj imp = new ClassDataObj("Piece", 0, null, "Chess", null, null, null);
		 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		 data.add(abClass);
		 data.add(imp);
		 Check c = new TemplatePattern(data);
		 Linter l = new Linter();
		 l.addToCheckList(c);
		 List<String> notes = l.lint(data);
	     assertEquals(notes.size(), 1);
	     assertEquals(notes.get(0), "The template pattern is seen here.\n" + 
	     		"\n" + 
	     		"TEMPLATE CLASS: Chess\n" + 
	     		"\n" + 
	     		"TEMPLATES: Piece \n\n");
	    }
	
	
	@Test
	public void multipleTemplateTest() {
		
		//public ClassDataObj(String name, int access, String signature, 
//		String superClass, List<String> ints, List<FieldDataObj> f, List<MethodDataObj> m) {
		 List<MethodDataObj> methods = new ArrayList<MethodDataObj>();
			//()Ljava/util/List<Ljava/lang/Integer;>;
				//()Ljava/util/List;
			 MethodDataObj n1 = new MethodDataObj("template", 16, null, "()V;", null, new Instruction(3,0,3));
			 MethodDataObj n2 = new MethodDataObj("checkers", 1, null, "()LCheckers;", null, null);
			 methods.add(n1);
			 methods.add(n2);
		 
		ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, methods);
		 ClassDataObj imp = new ClassDataObj("Piece", 0, null, "Chess", null, null, null);
		 ClassDataObj imp2 = new ClassDataObj("Board", 0, null, "Chess", null, null, null);
		 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		 data.add(abClass);
		 data.add(imp);
		 data.add(imp2);
		 Check c = new TemplatePattern(data);
		 Linter l = new Linter();
		 l.addToCheckList(c);
		 List<String> notes = l.lint(data);
	     assertEquals(notes.size(), 1);
	     assertEquals(notes.get(0), "The template pattern is seen here.\n" + 
	     		"\n" + 
	     		"TEMPLATE CLASS: Chess\n" + 
	     		"\n" + 
	     		"TEMPLATES: Piece, Board \n\n");
	    }
	
	@Test
	public void badTemplateTest1() {
		
		//public ClassDataObj(String name, int access, String signature, 
//		String superClass, List<String> ints, List<FieldDataObj> f, List<MethodDataObj> m) {
		 List<MethodDataObj> methods = new ArrayList<MethodDataObj>();
			//()Ljava/util/List<Ljava/lang/Integer;>;
				//()Ljava/util/List;
			 MethodDataObj n1 = new MethodDataObj("template", 1, null, "()V;", null, new Instruction(3,0,3));
			 MethodDataObj n2 = new MethodDataObj("checkers", 1, null, "()LCheckers;", null, null);
			 methods.add(n1);
			 methods.add(n2);
		 
		ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, methods);
		 ClassDataObj imp = new ClassDataObj("Piece", 0, null, "Chess", null, null, null);
		 ClassDataObj imp2 = new ClassDataObj("Board", 0, null, "Chess", null, null, null);
		 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		 data.add(abClass);
		 data.add(imp);
		 data.add(imp2);
		 Check c = new TemplatePattern(data);
		 Linter l = new Linter();
		 l.addToCheckList(c);
		 List<String> notes = l.lint(data);
	     assertEquals(notes.size(), 0);
	}
	
	@Test
	public void badTemplateTest2() {
		
		//public ClassDataObj(String name, int access, String signature, 
//		String superClass, List<String> ints, List<FieldDataObj> f, List<MethodDataObj> m) {
		 List<MethodDataObj> methods = new ArrayList<MethodDataObj>();
			//()Ljava/util/List<Ljava/lang/Integer;>;
				//()Ljava/util/List;
			 MethodDataObj n1 = new MethodDataObj("template", 16, null, "()V;", null, new Instruction(3,0,3));
			 MethodDataObj n2 = new MethodDataObj("template2", 16, null, "()V;", null, new Instruction(3,0,3));
			 MethodDataObj n3 = new MethodDataObj("checkers", 1, null, "()LCheckers;", null, null);
			 methods.add(n1);
			 methods.add(n2);
			 methods.add(n3);
		 
		 ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, methods);
		 ClassDataObj abClass2 = new ClassDataObj("Checkers", 1024, null, "Object", null, null, methods);
		 ClassDataObj imp =  new ClassDataObj("Piece", 0, null, "Chess", null, null, null);
		 ClassDataObj imp2 = new ClassDataObj("Board", 0, null, "Chess", null, null, null);
		 ClassDataObj imp3 = new ClassDataObj("Square", 0, null, "Checkers", null, null, null);
		 ClassDataObj imp4 = new ClassDataObj("Coin", 0, null, "Checkers", null, null, null);
		 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		 data.add(abClass);
		 data.add(imp);
		 data.add(imp2);
		 data.add(imp3);
		 data.add(imp4);
		 Check c = new TemplatePattern(data);
		 Linter l = new Linter();
		 l.addToCheckList(c);
		 List<String> notes = l.lint(data);
	     assertEquals(notes.size(), 2);
	}
	
	
	@Test
	public void multipleTemplatesTest2() {
		
		//public ClassDataObj(String name, int access, String signature, 
//		String superClass, List<String> ints, List<FieldDataObj> f, List<MethodDataObj> m) {
		 List<MethodDataObj> methods = new ArrayList<MethodDataObj>();
			//()Ljava/util/List<Ljava/lang/Integer;>;
				//()Ljava/util/List;
			 MethodDataObj n1 = new MethodDataObj("template", 1, null, "()V;", null, new Instruction(3,2,5));
			 MethodDataObj n2 = new MethodDataObj("checkers", 1, null, "()LCheckers;", null, null);
			 methods.add(n1);
			 methods.add(n2);
		 
		ClassDataObj abClass = new ClassDataObj("Chess", 1024, null, "Object", null, null, methods);
		 ClassDataObj imp = new ClassDataObj("Piece", 0, null, "Chess", null, null, null);
		 ClassDataObj imp2 = new ClassDataObj("Board", 0, null, "Chess", null, null, null);
		 List<ClassDataObj> data = new ArrayList<ClassDataObj>();
		 data.add(abClass);
		 data.add(imp);
		 data.add(imp2);
		 Check c = new TemplatePattern(data);
		 Linter l = new Linter();
		 l.addToCheckList(c);
		 List<String> notes = l.lint(data);
	     assertEquals(notes.size(), 0);
	}
	}

