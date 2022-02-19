package Linter;

import java.util.ArrayList;
import java.util.List;
import DataSource.*;

import org.objectweb.asm.Opcodes;


public class TemplatePattern extends Check{
	
	public TemplatePattern(List<ClassDataObj> d) {
		super(d);
	}

	@Override
	List<String> check() {
		List<ClassDataObj> abstractClasses = new ArrayList<ClassDataObj>();
	
		// figure out which classes are abstract
		for(ClassDataObj c: data) {
			if(c.isAbstract())
				abstractClasses.add(c);
		}
		
		//get all abstract methods and final methods
		for(ClassDataObj templateClass: abstractClasses) {
			List<MethodDataObj> methods = templateClass.getMethods();
			List<MethodDataObj> finalMethods = new ArrayList<MethodDataObj>();
			// find all final and abstract methods
			for(MethodDataObj m : methods)
			{
				if(m.isFinal())
					finalMethods.add(m);	
			}
		
		if(finalMethods.size() > 0) //there is a final method
		{
		//check if it defines a procedure/recipe	
			for(MethodDataObj fm : finalMethods) {
				Instruction in = fm.getInstructionData();
				int methodsCalled = in.getMethodCalls();
				int varsCalled = in.getVariableConstructions();
				if(methodsCalled>=2 && varsCalled == methodsCalled) //only methodsCalled
				{
					List<ClassDataObj> concretes = getTemplates(templateClass.getName());
					if(concretes.size()!=0){
						String s ="";
						for(ClassDataObj concrete: concretes)
						{
							s = s + concrete.getName() + ", ";
						}
						
						errors.add("The template pattern is seen here.\n\n"
								+ "TEMPLATE CLASS: "+ templateClass.getName() + "\n\n" +
								  "TEMPLATES: " + s.substring(0, s.length()-2) +" \n\n"
								);
					}}}
		}
		}
		return errors;
	}

	private List<ClassDataObj> getTemplates(String templateClass) {
	List<ClassDataObj> templatedClasses = new ArrayList<ClassDataObj>();
		for(ClassDataObj templated: data)
		{
			if(templated.getSuperClassName().equals(templateClass))
				templatedClasses.add(templated);
		}
		return templatedClasses;	
	}
}
		
		
		
		
		
		
		
